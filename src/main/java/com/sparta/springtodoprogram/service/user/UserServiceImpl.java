package com.sparta.springtodoprogram.service.user;

import com.sparta.springtodoprogram.dto.UserDto.ToTalUserReqDto;
import com.sparta.springtodoprogram.dto.UserDto.TotalUserResDto;
import com.sparta.springtodoprogram.entity.User;
import com.sparta.springtodoprogram.repository.TodoRepository;
import com.sparta.springtodoprogram.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 유저 등록
    @Override
    @Transactional
    public TotalUserResDto createUser(ToTalUserReqDto requestDto) {
        // RequestDto -> Entity
        User user = new User(requestDto);
        // DB 저장
        userRepository.save(user);
        // Entity -> ResponseDto
        TotalUserResDto totalUserResDto = new TotalUserResDto(user);

        return totalUserResDto;
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
