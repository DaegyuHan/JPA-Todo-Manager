package com.sparta.springtodoprogram.service.user;

import com.sparta.springtodoprogram.dto.UserDto.ToTalUserReqDto;
import com.sparta.springtodoprogram.dto.UserDto.TotalUserResDto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {

    // 유저 등록
    TotalUserResDto createUser(ToTalUserReqDto requestDto, HttpServletResponse res);
    // 유저 단건 조회
    TotalUserResDto getUser(Long id);
    // 유저 전체 조회
    List<TotalUserResDto> getUsers();
    // 유저 수정
    TotalUserResDto updateUser(Long id, ToTalUserReqDto requestDto);
    // 유저 삭제
    void deleteUser(Long id);
}
