package com.sparta.springtodoprogram.dto.userDto;

import com.sparta.springtodoprogram.entity.User;
import lombok.Getter;

@Getter
public class LoginUserResDto {
    private long id;
    private String userEmail;
    private String token;

    public LoginUserResDto(User user, String token) {
        this.id = user.getId();
        this.userEmail = user.getUserEmail();
        this.token = token;
    }
}
