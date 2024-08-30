package com.sparta.springtodoprogram.domain.todo.dto.response;

import com.sparta.springtodoprogram.domain.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UpdateTodoResDto {
    private long Id;
    private long userId;
    private String todoTitle;
    private String todoContent;
    private List<Long> assignedUserIds;
    private LocalDateTime modifiedAt;

    public UpdateTodoResDto(Todo todo) {
        this.Id = todo.getId();
        this.userId = todo.getUserId();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.assignedUserIds = todo.getManagementList().stream().map(management
                -> management.getUser().getId()).toList() ;
        this.modifiedAt = todo.getModifiedAt();

    }
}
