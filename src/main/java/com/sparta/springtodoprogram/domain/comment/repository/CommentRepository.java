package com.sparta.springtodoprogram.domain.comment.repository;

import com.sparta.springtodoprogram.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 단건 조회
    Optional<Comment> findById(Long commentId);

    // 수정일 내림차순 전체 조회
    List<Comment> findByTodo_IdOrderByModifiedAtDesc(Long todoId);

    // 해당 댓글이 있는지 확인
    Optional<Comment> findByTodo_IdAndId(Long todoId, Long commentId);

    default Comment findByIdAndTodoIdOrElseThrow(Long todoId, Long commentId) {
        return findByTodo_IdAndId(todoId, commentId).orElseThrow(() -> new NoSuchElementException("댓글을 찾을 수 없습니다."));
    }
}
