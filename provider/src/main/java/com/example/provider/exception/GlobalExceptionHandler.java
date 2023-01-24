package com.example.provider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalNullFieldException.class)
    public ResponseEntity<?> handleIllegalNullException() {
        return new ResponseEntity<>("Not-null Field Null", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> handleNotFoundException() {
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }
}
