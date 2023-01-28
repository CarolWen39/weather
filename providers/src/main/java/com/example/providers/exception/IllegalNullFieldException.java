package com.example.providers.exception;

public class IllegalNullFieldException extends RuntimeException{
    public IllegalNullFieldException(String msg) {
        super(msg);
    }
}
