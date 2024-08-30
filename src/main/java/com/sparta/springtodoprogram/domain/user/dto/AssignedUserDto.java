package com.sparta.springtodoprogram.domain.user.dto;

import com.sparta.springtodoprogram.config.management.Management;
import lombok.Getter;

@Getter
public class AssignedUserDto {
    private long userId;
    private String userName;
    private String userEmail;

    public AssignedUserDto(Management management) {
        this.userId = management.getId();
        this.userName = management.getUser().getUserName();
        this.userEmail = management.getUser().getUserEmail();
    }
}
