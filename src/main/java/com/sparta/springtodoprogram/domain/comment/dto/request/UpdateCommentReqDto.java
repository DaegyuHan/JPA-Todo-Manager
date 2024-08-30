package com.sparta.springtodoprogram.domain.comment.dto.request;

import lombok.Getter;

@Getter
public class UpdateCommentReqDto {
    private String commentContent;
    private Long userId;
}
