package com.sparta.springtodoprogram.service.todo;

import com.sparta.springtodoprogram.dto.todoDto.*;

import com.sparta.springtodoprogram.dto.userDto.AssignedUserDto;
import com.sparta.springtodoprogram.entity.Management;
import com.sparta.springtodoprogram.entity.Todo;
import com.sparta.springtodoprogram.entity.User;
import com.sparta.springtodoprogram.repository.ManagementRepository;
import com.sparta.springtodoprogram.repository.TodoRepository;
import com.sparta.springtodoprogram.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ManagementRepository managementRepository;
    private final TodoWeatherService todoWeatherService;

    // 일정 등록
    @Override
    @Transactional
    public RegistTodoResDto createTodo(RegistTodoReqDto requestDto) {

        String todayWeateher = todoWeatherService.getTodayWeather();

        // RequestDto -> Entity
        Todo todo = new Todo(requestDto, todayWeateher);
        // DB 저장
        todoRepository.save(todo);
        // Entity -> ResponseDto
        RegistTodoResDto registTodoResDto = new RegistTodoResDto(todo, todayWeateher);

        return registTodoResDto;
    }

    // 일정 단건 조회
    @Override
    @Transactional
    public InquiryTodoResDto getTodo(Long id) {
        // 해당 일정이 DB 에 존재하는지 확인
        Todo todo = todoRepository.findByIdOrElseThrow(id);


        // 1. 해당 todoId 와 연결되어있는 Management table 에 접근
        List<Management> managementList = todo.getManagementList();
        // 2. 반복문 돌면서 managementList 안에 management 요소들에 접근

        List<AssignedUserDto> todoAssignedDtoList = managementList.stream().map(AssignedUserDto::new).toList();

        InquiryTodoResDto inquiryTodoResDto = new InquiryTodoResDto(todo, todoAssignedDtoList);
        return inquiryTodoResDto;
    }

    // 일정 전체 조회 ( 페이지네이션 )
    @Override
    @Transactional
    public List<InquiryTodosResDto> getTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Todo> todoList = todoRepository.findAllByOrderByModifiedAtDesc(pageable);

        List<InquiryTodosResDto> inquiryTodosResDtoList = todoList.map(InquiryTodosResDto::new).getContent();

        return inquiryTodosResDtoList;
    }

    // 일정 수정
    @Override
    @Transactional
    public UpdateTodoResDto updateTodo(Long id, UpdateTodoReqDto requestDto) {
        // 해당 일정이 DB 에 존재하는지 확인
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        // 지정한 담당자들이 유저테이블에 있는지 확인
        for (Long assignedUserId : requestDto.getAssignedUserIds()) {
            User user = userRepository.findById(assignedUserId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다"));
            // Management Entity 생성
            Management management = new Management(user, todo);
            // Todo 에 management 연관 설정
            todo.addManagement(management);
            // management 저장
            managementRepository.save(management);
        }

        // 일정 수정
        todo.update(requestDto);
        return new UpdateTodoResDto(todo);
    }

    // 일정 삭제
    @Override
    @Transactional
    public void deleteTodo(Long id) {
        // 해당 일정이 있는지 조회
        Todo todo = todoRepository.findByIdOrElseThrow(id);

        // 일정 삭제
        todoRepository.delete(todo);
    }
}
