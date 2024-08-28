package com.sparta.springtodoprogram.controller;

import com.sparta.springtodoprogram.dto.userDto.LoginUserReqDto;
import com.sparta.springtodoprogram.dto.userDto.LoginUserResDto;
import com.sparta.springtodoprogram.dto.userDto.ToTalUserReqDto;
import com.sparta.springtodoprogram.dto.userDto.TotalUserResDto;
import com.sparta.springtodoprogram.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 등록 ( 회원가입 )
    @PostMapping("/user/signup")
    public TotalUserResDto createUser(@RequestBody  ToTalUserReqDto requestDto, HttpServletResponse res) {
        return userService.createUser(requestDto, res);
    }

    // 유저 로그인
    @PostMapping("/user/login")
    public ResponseEntity<LoginUserResDto> loginUser(@RequestBody LoginUserReqDto requestDto, HttpServletResponse res) {
        return ResponseEntity.ok(userService.loginUser(requestDto, res));
    }

    // 유저 단건 조회
    @GetMapping("/user/{id}")
    public TotalUserResDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // 유저 전체 조회
    @GetMapping("/users")
    public List<TotalUserResDto> getUsers() {
        return userService.getUsers();
    }

    // 유저 수정
    @PutMapping("/user/{id}")
    public TotalUserResDto updateUser(@PathVariable Long id, @RequestBody ToTalUserReqDto requestDto) {
        return userService.updateUser(id, requestDto);
    }

    // 유저 삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
