package com.sparta.springtodoprogram.domain.comment.dto.request;

import lombok.Getter;

@Getter
public class RegistCommentReqDto {
    private String commentContent;
    private Long userId;
}
