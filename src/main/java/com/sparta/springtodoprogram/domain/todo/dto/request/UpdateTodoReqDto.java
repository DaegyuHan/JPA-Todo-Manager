package com.sparta.springtodoprogram.domain.todo.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateTodoReqDto {
    private Long userId;
    private String todoTitle;
    private String todoContent;
    private List<Long> assignedUserIds;
}
