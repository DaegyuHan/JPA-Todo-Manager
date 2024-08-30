package com.sparta.springtodoprogram.domain.comment.service;

import com.sparta.springtodoprogram.domain.comment.dto.request.RegistCommentReqDto;
import com.sparta.springtodoprogram.domain.comment.dto.request.UpdateCommentReqDto;
import com.sparta.springtodoprogram.domain.comment.dto.response.InquiryCommentResDto;
import com.sparta.springtodoprogram.domain.comment.dto.response.RegistCommentResDto;
import com.sparta.springtodoprogram.domain.comment.dto.response.UpdateCommentResDto;
import com.sparta.springtodoprogram.domain.comment.entity.Comment;
import com.sparta.springtodoprogram.domain.todo.entity.Todo;
import com.sparta.springtodoprogram.domain.comment.repository.CommentRepository;
import com.sparta.springtodoprogram.domain.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private  final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    // 댓글 등록
    @Override
    public RegistCommentResDto createComment(Long todoId, RegistCommentReqDto requestDto) {
        // 해당 일정이 있는지 조회
        Todo todo = todoRepository.findByIdOrElseThrow(todoId);
        // RequestDto -> Entity
        Comment comment = new Comment(requestDto);
        // Todo에 comment 연관 설정
        todo.addComment(comment);
        // DB 저장
        commentRepository.save(comment);
        // Entity -> ResponseDto
        RegistCommentResDto registCommentResDto = new RegistCommentResDto(comment);

        return registCommentResDto;
    }

    // 댓글 단건 조회
    @Override
    public List<InquiryCommentResDto> getComment(Long todoId, Long commentId) {
        // 해당 일정이 있는지 조회
        todoRepository.findByIdOrElseThrow(todoId);

        return commentRepository.findById(commentId).stream()
                .map(InquiryCommentResDto::new)
                .toList();
    }

    // 댓글 전체 조회
    @Override
    public List<InquiryCommentResDto> getComments(Long todoId) {
        //해당 일정이 있는지 조회
        todoRepository.findByIdOrElseThrow(todoId);
        return commentRepository.findByTodo_IdOrderByModifiedAtDesc(todoId).stream()
                .map(InquiryCommentResDto::new)
                .toList();
    }

    // 댓글 수정
    @Override
    @Transactional
    public UpdateCommentResDto updateComment(Long todoId, Long commentId, UpdateCommentReqDto requestDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));

        if (comment.getTodo() != null && Objects.equals(comment.getTodo().getId(), todoId)) {
            comment.update(requestDto);
        } else {
            throw new IllegalArgumentException("원하는 댓글이 없습니다.");
        }

        return null;
    }

    @Override
    @Transactional
    public void deleteComment(Long todoId, Long commentId) {
        // 해당 댓글이 있는지 조회
        Comment comment = commentRepository.findByIdAndTodoIdOrElseThrow(todoId, commentId);

        // 댓글 삭제
        commentRepository.delete(comment);
    }



}
