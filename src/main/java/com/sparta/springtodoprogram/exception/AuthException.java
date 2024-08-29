package com.sparta.springtodoprogram.exception;

public class AuthException extends RuntimeException{
    public AuthException (String messsage) {
        super(messsage);
    }
}
