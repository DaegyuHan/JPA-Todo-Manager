package com.sparta.springtodoprogram.service.comment;

import com.sparta.springtodoprogram.dto.CommentDto.*;

import java.util.List;

public interface CommentService {
    // 댓글 등록
    RegistCommentResDto createComment(Long todoId, RegistCommentReqDto requestDto);
    // 댓글 단건 조회
    List<InquiryCommentResDto> getComment(Long todoId, Long commentId);
    // 댓글 전체 조회
    List<InquiryCommentResDto> getComments(Long todoId);
    // 댓글 수정
    UpdateCommentResDto updateComment(Long todoId, Long commentId, UpdateCommentReqDto requestDto);
    // 댓글 삭제
    void deleteComment(Long todoId, Long commentId);
}
