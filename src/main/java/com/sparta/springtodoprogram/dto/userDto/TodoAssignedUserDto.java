package com.sparta.springtodoprogram.dto.userDto;

import com.sparta.springtodoprogram.entity.Management;
import lombok.Getter;

@Getter
public class TodoAssignedUserDto {
    private long userId;
    private String userName;
    private String userEmail;

    public TodoAssignedUserDto(Management management) {
        this.userId = management.getId();
        this.userName = management.getUser().getUserName();
        this.userEmail = management.getUser().getUserEmail();
    }
}
