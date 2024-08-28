package com.sparta.springtodoprogram.dto.TodoDto;

import lombok.Getter;

@Getter
public class RegistTodoReqDto {
    private Long userId;
    private String todoTitle;
    private String todoContent;
}
