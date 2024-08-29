package com.sparta.springtodoprogram.service.user;

import com.sparta.springtodoprogram.config.PasswordEncoder;
import com.sparta.springtodoprogram.dto.userDto.LoginUserReqDto;
import com.sparta.springtodoprogram.dto.userDto.LoginUserResDto;
import com.sparta.springtodoprogram.dto.userDto.ToTalUserReqDto;
import com.sparta.springtodoprogram.dto.userDto.TotalUserResDto;
import com.sparta.springtodoprogram.entity.User;
import com.sparta.springtodoprogram.entity.UserRole;
import com.sparta.springtodoprogram.exception.AuthException;
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

    // 유저 등록 ( 회원가입 )
    @Override
    @Transactional
    public TotalUserResDto createUser(ToTalUserReqDto requestDto, HttpServletResponse res) {


        String userName = requestDto.getUserName();
        // 비밀번호 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUserName(userName);
        if (checkUsername.isPresent()) {
            throw new AuthException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String userEmail = requestDto.getUserEmail();
        Optional<User> checkEmail = userRepository.findByUserEmail(userEmail);
        if (checkEmail.isPresent()) {
            throw new AuthException("중복된 Email 입니다.");
        }


        // RequestDto -> Entity
        // Token 은 제외하고 전달
        User user = User.addUser(userName, userEmail, password, convertToUserRole(requestDto.getUserRole()));
        // DB 저장
        User saveuser = userRepository.save(user);


        // JWT 생성 및 저장
        String token = jwtUtil.createToken(
                saveuser.getId(),
                saveuser.getUserName(),
                saveuser.getUserEmail(),
               saveuser.getUserRole());


        // Entity -> ResponseDto
        // Token 출력을 위해 Dto 와 함께 전달
        TotalUserResDto totalUserResDto = new TotalUserResDto(user, token, convertToUserRole(requestDto.getUserRole()));

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
                () -> new AuthException("가입되지 않은 유저입니다..")
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getId(), user.getUserName(), user.getUserEmail(), user.getUserRole());

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

    private UserRole convertToUserRole(String userRoleString) {
        try {
            return UserRole.valueOf(userRoleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user role provided.");
        }
    }
}
