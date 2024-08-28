package com.sparta.springtodoprogram.dto.CommentDto;

import com.sparta.springtodoprogram.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegistCommentResDto {
    private String commentContent;
    private String userName;
    private LocalDateTime createdAt;

    public RegistCommentResDto(Comment comment) {
        this.commentContent = comment.getCommentContent();
        this.userName = comment.getUserName();
        this.createdAt = comment.getCreatedAt();
    }
}
