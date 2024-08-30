package com.sparta.springtodoprogram.domain.user.dto.response;

import com.sparta.springtodoprogram.domain.user.entity.User;
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
