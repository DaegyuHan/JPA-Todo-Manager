package com.sparta.springtodoprogram.dto.exceptionDto;

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
