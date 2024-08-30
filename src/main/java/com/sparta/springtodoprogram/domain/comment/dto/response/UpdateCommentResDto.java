package com.sparta.springtodoprogram.domain.comment.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCommentResDto {
    private String commentContent;
    private String userName;
    private LocalDateTime modifiedAt;
}
