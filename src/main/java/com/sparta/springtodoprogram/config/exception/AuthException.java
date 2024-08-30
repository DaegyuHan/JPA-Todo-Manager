package com.sparta.springtodoprogram.config.exception;

public class AuthException extends RuntimeException{
    public AuthException (String messsage) {
        super(messsage);
    }
}
