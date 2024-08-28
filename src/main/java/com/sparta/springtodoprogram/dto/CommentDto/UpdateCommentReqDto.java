package com.sparta.springtodoprogram.dto.CommentDto;

import lombok.Getter;

@Getter
public class UpdateCommentReqDto {
    private String commentContent;
    private String userName;
}
