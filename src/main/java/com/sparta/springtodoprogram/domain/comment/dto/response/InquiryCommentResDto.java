package com.sparta.springtodoprogram.domain.comment.dto.response;

import com.sparta.springtodoprogram.domain.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InquiryCommentResDto {
    private String commentContent;
    private String userName;
    private LocalDateTime createdAt;

    public InquiryCommentResDto(Comment comment) {
        this.commentContent = comment.getCommentContent();
        this.userName = comment.getUserName();
        this.createdAt = comment.getCreatedAt();
    }
}
