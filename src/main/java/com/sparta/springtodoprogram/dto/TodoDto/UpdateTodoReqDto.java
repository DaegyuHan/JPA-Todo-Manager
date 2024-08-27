package com.sparta.springtodoprogram.dto;

import lombok.Getter;

@Getter
public class UpdateTodoReqDto {
    private String userName;
    private String todoTitle;
    private String todoContent;
}
