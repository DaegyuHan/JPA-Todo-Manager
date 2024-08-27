package com.sparta.springtodoprogram.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoResDto {
    private long userId;
    private String userName;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime modifiedAt;
}
