package com.sparta.springtodoprogram.exception;

import com.sparta.springtodoprogram.dto.exception.ExceptionResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ResponseStatusException 예외 처리
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResDto> handleResponseStatusException(ResponseStatusException ex) {
        ExceptionResDto exceptionResDto = new ExceptionResDto(ex.getStatusCode().value(), ex.getReason());
        return ResponseEntity
                .status(ex.getStatusCode())  // 예외에서 상태 코드 가져오기
                .body(exceptionResDto);        // 예외에서 메시지 가져오기
    }

    // IllegalArgumentException 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}