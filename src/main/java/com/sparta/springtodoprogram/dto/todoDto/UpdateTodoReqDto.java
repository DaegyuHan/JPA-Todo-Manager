package com.sparta.springtodoprogram.dto.todoDto;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateTodoReqDto {
    private Long userId;
    private String todoTitle;
    private String todoContent;
    private List<Long> assignedUserIds;
}
