package com.sparta.springtodoprogram.dto.userDto;

import com.sparta.springtodoprogram.entity.User;
import com.sparta.springtodoprogram.entity.UserRole;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TotalUserResDto {
    private long userId;
    private String userName;
    private String userEmail;
    private String userRole;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String token;

    public TotalUserResDto(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }

    public TotalUserResDto(User user, String token, UserRole userRole) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userRole = String.valueOf(userRole);
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
        this.token = token;
    }
}
