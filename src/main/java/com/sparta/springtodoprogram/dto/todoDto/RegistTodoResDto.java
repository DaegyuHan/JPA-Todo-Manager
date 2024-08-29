package com.sparta.springtodoprogram.dto.todoDto;

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
    private String weather;

    public RegistTodoResDto(Todo todo, String todoWeatherService) {
        this.Id = todo.getId();
        this.userId = todo.getUserId();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.createdAt = todo.getCreatedAt();
        this.weather = String.valueOf(todoWeatherService);
    }
}
