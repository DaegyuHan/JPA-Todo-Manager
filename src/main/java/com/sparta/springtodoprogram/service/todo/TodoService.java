package com.sparta.springtodoprogram.service.todo;

import com.sparta.springtodoprogram.dto.TodoDto.*;

import java.util.List;

public interface TodoService {
    // 일정 등록
    RegistTodoResDto createTodo(RegistTodoReqDto requestDto);
    // 일정 단건 조회
    InquiryTodoResDto getTodo(Long id);
    // 일정 수정
    UpdateTodoResDto updateTodo(Long id, UpdateTodoReqDto requestDto);

    // 일정 전체 조회 ( 페이지네이션 )
    List<InquiryTodosResDto> getTodos(int page, int size);

    void deleteTodo(Long id);
}
