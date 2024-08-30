package com.sparta.springtodoprogram.domain.comment.controller;

import com.sparta.springtodoprogram.domain.comment.dto.request.RegistCommentReqDto;
import com.sparta.springtodoprogram.domain.comment.dto.request.UpdateCommentReqDto;
import com.sparta.springtodoprogram.domain.comment.dto.response.InquiryCommentResDto;
import com.sparta.springtodoprogram.domain.comment.dto.response.RegistCommentResDto;
import com.sparta.springtodoprogram.domain.comment.dto.response.UpdateCommentResDto;
import com.sparta.springtodoprogram.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo/{todoId}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/comment")
    public RegistCommentResDto createComment(@PathVariable Long todoId, @RequestBody RegistCommentReqDto requestDto) {
        return commentService.createComment(todoId, requestDto);
    }

    //댓글 단건 조회
    @GetMapping("/comment/{commentId}")
    public List<InquiryCommentResDto> getComment(@PathVariable Long todoId, @PathVariable Long commentId) {
        return commentService.getComment(todoId, commentId);
    }

    // 댓글 전체 조회
    @GetMapping("/comments")
    public List<InquiryCommentResDto> getComments(@PathVariable Long todoId) {
        return commentService.getComments(todoId);
    }

    // 댓글 수정
    @PutMapping("/comment/{commentId}")
    public UpdateCommentResDto updateComment(@PathVariable Long todoId,
                                             @PathVariable Long commentId,
                                             @RequestBody UpdateCommentReqDto requestDto) {
        return commentService.updateComment(todoId, commentId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long todoId, @PathVariable Long commentId) {
        commentService.deleteComment(todoId, commentId);
        return ResponseEntity.noContent().build();
    }

}
