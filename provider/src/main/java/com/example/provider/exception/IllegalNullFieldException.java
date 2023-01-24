package com.example.provider.exception;

public class IllegalNullFieldException extends RuntimeException{
    public IllegalNullFieldException(String msg) {
        super(msg);
    }
}
