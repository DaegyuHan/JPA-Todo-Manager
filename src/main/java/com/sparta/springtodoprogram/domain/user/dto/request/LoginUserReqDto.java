package com.sparta.springtodoprogram.domain.user.dto.request;

import lombok.Getter;

@Getter
public class LoginUserReqDto {
    private String userEmail;
    private  String password;
}
