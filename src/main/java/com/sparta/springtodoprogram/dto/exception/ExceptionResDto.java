package com.sparta.springtodoprogram.dto.exception;

import lombok.Getter;

@Getter
public class ExceptionResDto {
    private int status;
    private String message;

    public ExceptionResDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
