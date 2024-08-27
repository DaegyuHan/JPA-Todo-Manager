package com.sparta.springtodoprogram.service;

import com.sparta.springtodoprogram.dto.TodoDto.RegistTodoReqDto;
import com.sparta.springtodoprogram.dto.TodoDto.RegistTodoResDto;
import com.sparta.springtodoprogram.dto.TodoDto.UpdateTodoReqDto;
import com.sparta.springtodoprogram.dto.TodoDto.UpdateTodoResDto;
import com.sparta.springtodoprogram.entity.Todo;
import com.sparta.springtodoprogram.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    // 일정 등록
    @Override
    public RegistTodoResDto createTodo(RegistTodoReqDto requestDto) {
        // RequestDto -> Entity
        Todo todo = new Todo(requestDto);
        // DB 저장
        todoRepository.save(todo);
        // Entity -> ResponseDto
        RegistTodoResDto registTodoResDto = new RegistTodoResDto(todo);

        return registTodoResDto;
    }

    // 일정 단건 조회
    @Override
    public List<Todo> getTodo(Long id) {
        return todoRepository.findByTodoId(id);
    }

    // 일정 수정
    @Override
    @Transactional
    public UpdateTodoResDto updateTodo(Long id, UpdateTodoReqDto requestDto) {
        // 해당 일정이 DB 에 존재하는지 확인
        Todo todo = findTodo(id);
        // 일정 수정
        todo.update(requestDto);
        return null;
    }

    private Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }
}
