package com.sparta.springtodoprogram.service;

import com.sparta.springtodoprogram.dto.CommentDto.InquiryCommentResDto;
import com.sparta.springtodoprogram.dto.CommentDto.RegistCommentReqDto;
import com.sparta.springtodoprogram.dto.CommentDto.RegistCommentResDto;
import com.sparta.springtodoprogram.entity.Comment;
import com.sparta.springtodoprogram.entity.Todo;

import java.util.List;

public interface CommentService {
    // 댓글 등록
    RegistCommentResDto createComment(Long todoId, RegistCommentReqDto requestDto);
    // 댓글 단건 조회
    List<InquiryCommentResDto> getComment(Long todoId, Long commentId);
}
