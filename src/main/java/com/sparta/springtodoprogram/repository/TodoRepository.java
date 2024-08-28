package com.sparta.springtodoprogram.repository;

import com.sparta.springtodoprogram.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface TodoRepository  extends JpaRepository<Todo, Long> {
    // 일정 단건 조회
    Optional<Todo> findById(Long todoId);

    // 해당 일정이 있는지 조회
    default Todo findByIdOrElseThrow(Long todoId) {
        return findById(todoId).orElseThrow(() -> new NoSuchElementException("일정을 찾을 수 없습니다."));
    }

    // 일정 전체 조회 시에 내림차순 정렬
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
