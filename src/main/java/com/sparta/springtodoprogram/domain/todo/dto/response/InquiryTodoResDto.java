package com.sparta.springtodoprogram.domain.todo.dto.response;

import com.sparta.springtodoprogram.domain.user.dto.AssignedUserDto;
import com.sparta.springtodoprogram.domain.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class InquiryTodoResDto {
    private Long userId;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime modifiedAt;
    private Long commentCount;
    private List<AssignedUserDto> assignedUsers;

    public InquiryTodoResDto(Todo todo, List<AssignedUserDto> todoAssignedDtoList) {
        this.userId = todo.getUserId();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.modifiedAt = todo.getModifiedAt();
        this.commentCount = (long) todo.getCommentCount();
        this. assignedUsers = todoAssignedDtoList;
    }

}
