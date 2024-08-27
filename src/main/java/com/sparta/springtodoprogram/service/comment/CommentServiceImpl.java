package com.sparta.springtodoprogram.service;

import com.sparta.springtodoprogram.dto.CommentDto.InquiryCommentResDto;
import com.sparta.springtodoprogram.dto.CommentDto.RegistCommentReqDto;
import com.sparta.springtodoprogram.dto.CommentDto.RegistCommentResDto;
import com.sparta.springtodoprogram.entity.Comment;
import com.sparta.springtodoprogram.entity.Todo;
import com.sparta.springtodoprogram.repository.CommentRepository;
import com.sparta.springtodoprogram.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private  final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    // 댓글 등록
    @Override
    public RegistCommentResDto createComment(Long todoId, RegistCommentReqDto requestDto) {
        // 해당 일정이 있는지 조회
        Todo todo = checkTodo(todoId);
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
    public List<InquiryCommentResDto> getComment(Long todoId, Long commentId) {
        // 해당 일정이 있는지 조회
        checkTodo(todoId);
        List<Comment> comments = commentRepository.findByCommentId(commentId);
        return comments.stream()
                .map(InquiryCommentResDto::new)
                .collect(Collectors.toList());
    }

    // 해당 일정이 있는지 조회
    private Todo checkTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
    }
}
