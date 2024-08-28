package com.sparta.springtodoprogram.dto.todoDto;

import lombok.Getter;

@Getter
public class RegistTodoReqDto {
    private Long userId;
    private String todoTitle;
    private String todoContent;
}
