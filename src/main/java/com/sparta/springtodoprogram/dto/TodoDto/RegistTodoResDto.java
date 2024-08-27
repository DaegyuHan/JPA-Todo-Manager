package com.sparta.springtodoprogram.dto;

import com.sparta.springtodoprogram.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegistTodoResDto {
    private long userId;
    private String userName;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime createdAt;

    public RegistTodoResDto(Todo todo) {
        this.userId = todo.getTodoId();
        this.userName = todo.getUserName();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.createdAt = todo.getCreatedAt();
    }
}
