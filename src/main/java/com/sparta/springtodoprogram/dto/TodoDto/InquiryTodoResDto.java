package com.sparta.springtodoprogram.dto.TodoDto;

import com.sparta.springtodoprogram.dto.UserDto.TodoAssignedUserDto;
import com.sparta.springtodoprogram.entity.Management;
import com.sparta.springtodoprogram.entity.Todo;
import com.sparta.springtodoprogram.entity.User;
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
    private List<TodoAssignedUserDto> assignedUsers;

    public InquiryTodoResDto(Todo todo, List<TodoAssignedUserDto> todoAssignedDtoList) {
        this.userId = todo.getUserId();
        this.todoTitle = todo.getTodoTitle();
        this.todoContent = todo.getTodoContent();
        this.modifiedAt = todo.getModifiedAt();
        this.commentCount = (long) todo.getCommentCount();
        this. assignedUsers = todoAssignedDtoList;
    }

}
