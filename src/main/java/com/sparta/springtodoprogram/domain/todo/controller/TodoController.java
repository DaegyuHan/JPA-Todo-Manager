package com.sparta.springtodoprogram.domain.todo.controller;

import com.sparta.springtodoprogram.domain.todo.dto.request.RegistTodoReqDto;
import com.sparta.springtodoprogram.domain.todo.dto.request.UpdateTodoReqDto;
import com.sparta.springtodoprogram.domain.todo.dto.response.InquiryTodoResDto;
import com.sparta.springtodoprogram.domain.todo.dto.response.InquiryTodosResDto;
import com.sparta.springtodoprogram.domain.todo.dto.response.RegistTodoResDto;
import com.sparta.springtodoprogram.domain.todo.dto.response.UpdateTodoResDto;
import com.sparta.springtodoprogram.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {


    private final TodoService todoService;

    // 일정 등록
    @PostMapping("/todo")
    public RegistTodoResDto createTodo(@RequestBody RegistTodoReqDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    // 일정 단건 조회
    @GetMapping("/todo/{id}")
    public InquiryTodoResDto getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }

    // 일정 전체 조회 ( 페이지네이션 )
    @GetMapping("/todos")
    public List<InquiryTodosResDto> getTodos(@RequestParam(value = "page") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        // 응답 보내기
        return todoService.getTodos(page-1, size);
    }

    // 일정 수정
    @PutMapping("/todo/{id}")
    public UpdateTodoResDto updateTodo(@PathVariable Long id, @RequestBody UpdateTodoReqDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    // 일정 삭제
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

}
