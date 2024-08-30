package com.sparta.springtodoprogram.domain.user.dto.request;

import lombok.Getter;

@Getter
public class ToTalUserReqDto {
    private String userName;
    private String userEmail;
    private String password;
    private String userRole;
}
