package com.sparta.springtodoprogram.controller;

import com.sparta.springtodoprogram.dto.UserDto.ToTalUserReqDto;
import com.sparta.springtodoprogram.dto.UserDto.TotalUserResDto;
import com.sparta.springtodoprogram.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 등록
    @PostMapping("/user")
    public TotalUserResDto createUser(@RequestBody  ToTalUserReqDto requestDto) {
        return userService.createUser(requestDto);
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
