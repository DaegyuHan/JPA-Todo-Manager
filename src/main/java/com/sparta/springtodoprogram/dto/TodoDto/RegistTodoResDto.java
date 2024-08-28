package com.sparta.springtodoprogram.dto.TodoDto;

import com.sparta.springtodoprogram.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegistTodoResDto {
    private long Id;
    private long userId;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime createdAt;

    public RegistTodoResDto(Todo todo) {
        this.Id = todo.getId();
        this.userId = todo.getUserId();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.createdAt = todo.getCreatedAt();
    }
}
