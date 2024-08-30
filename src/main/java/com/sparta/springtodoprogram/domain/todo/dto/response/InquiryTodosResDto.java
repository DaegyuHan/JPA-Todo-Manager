package com.sparta.springtodoprogram.domain.todo.dto.response;

import com.sparta.springtodoprogram.domain.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InquiryTodosResDto {
    private Long userId;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime modifiedAt;
    private Long commentCount;

    public InquiryTodosResDto(Todo todo) {
        this.userId = todo.getUserId();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.modifiedAt = todo.getModifiedAt();
        this.commentCount = (long) todo.getCommentCount();
    }
}
