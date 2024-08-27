package com.sparta.springtodoprogram.dto;

import com.sparta.springtodoprogram.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InquiryTodoResDto {
    private String userName;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime modifiedAt;

    public InquiryTodoResDto(Todo todo) {
        this.userName = todo.getUserName();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.modifiedAt = todo.getModifiedAt();
    }
}
