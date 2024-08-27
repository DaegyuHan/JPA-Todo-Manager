package com.sparta.springtodoprogram.service;

import com.sparta.springtodoprogram.dto.TodoDto.RegistTodoReqDto;
import com.sparta.springtodoprogram.dto.TodoDto.RegistTodoResDto;
import com.sparta.springtodoprogram.dto.TodoDto.UpdateTodoReqDto;
import com.sparta.springtodoprogram.dto.TodoDto.UpdateTodoResDto;
import com.sparta.springtodoprogram.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    // 일정 등록
    RegistTodoResDto createTodo(RegistTodoReqDto requestDto);
    // 일정 단건 조회
    List<Todo> getTodo(Long id);
    // 일정 수정
    UpdateTodoResDto updateTodo(Long id, UpdateTodoReqDto requestDto);


}
