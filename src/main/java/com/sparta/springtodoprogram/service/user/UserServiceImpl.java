package com.sparta.springtodoprogram.service.user;

import com.sparta.springtodoprogram.config.PasswordEncoder;
import com.sparta.springtodoprogram.dto.userDto.LoginUserReqDto;
import com.sparta.springtodoprogram.dto.userDto.LoginUserResDto;
import com.sparta.springtodoprogram.dto.userDto.ToTalUserReqDto;
import com.sparta.springtodoprogram.dto.userDto.TotalUserResDto;
import com.sparta.springtodoprogram.entity.User;
import com.sparta.springtodoprogram.entity.UserRoleEnum;
import com.sparta.springtodoprogram.jwt.JwtUtil;
import com.sparta.springtodoprogram.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 유저 등록
    @Override
    @Transactional
    public TotalUserResDto createUser(ToTalUserReqDto requestDto, HttpServletResponse res) {


        String userName = requestDto.getUserName();
        // 비밀번호 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUserName(userName);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String userEmail = requestDto.getUserEmail();
        Optional<User> checkEmail = userRepository.findByUserEmail(userEmail);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // RequestDto -> Entity
        // Token 은 제외하고 전달
        User user = User.addUser(userName, userEmail, password);
        // DB 저장
        userRepository.save(user);
        // JWT 생성
        String token = jwtUtil.createToken(user.getUserEmail(), UserRoleEnum.USER);
        // JWT 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);
        // Entity -> ResponseDto
        // Token 출력을 위해 Dto 와 함께 전달
        TotalUserResDto totalUserResDto = new TotalUserResDto(user, token);

        return totalUserResDto;
    }

    // 유저 로그인
    @Override
    @Transactional
    public LoginUserResDto loginUser(LoginUserReqDto requestDto, HttpServletResponse res) {
        String userEmail = requestDto.getUserEmail();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일을 확인해주세요.")
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUserName(), UserRoleEnum.USER);
        jwtUtil.addJwtToCookie(token, res);

        return new LoginUserResDto(user, token);
    }


    // 유저 단건 조회
    @Override
    @Transactional
    public TotalUserResDto getUser(Long id) {
        // 해당 유저 조회
        User user = userRepository.findByIdOrElseThrow(id);

        return new TotalUserResDto(user);
    }

    // 유저 전체 조회
    @Override
    @Transactional
    public List<TotalUserResDto> getUsers() {
        return userRepository.findAll().stream().map(TotalUserResDto::new).toList();
    }

    // 유저 수정
    @Override
    @Transactional
    public TotalUserResDto updateUser(Long id, ToTalUserReqDto requestDto) {
        // 해당 유저 조회
        User user = userRepository.findByIdOrElseThrow(id);
        // 유저 수정
        user.update(requestDto);
        return new TotalUserResDto(user);
    }

    // 유저 삭제
    @Override
    @Transactional
    public void deleteUser(Long id) {
        // 해당 유저 조회
        User user = userRepository.findByIdOrElseThrow(id);
        // 유저 삭제
        userRepository.delete(user);
    }
}
