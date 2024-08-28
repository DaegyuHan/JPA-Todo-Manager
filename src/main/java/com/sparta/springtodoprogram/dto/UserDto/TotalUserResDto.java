package com.sparta.springtodoprogram.dto.UserDto;

import com.sparta.springtodoprogram.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TotalUserResDto {
    private Long userId;
    private String userName;
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TotalUserResDto(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
