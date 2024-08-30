package com.sparta.springtodoprogram.domain.todo.dto.request;

import lombok.Getter;

@Getter
public class RegistTodoReqDto {
    private Long userId;
    private String todoTitle;
    private String todoContent;
}
