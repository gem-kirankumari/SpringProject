package com.example.WebService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    private ResponseEntity<?> exceptionHandle(BindException ex) {

        Map<String, String> exceptionList = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError -> {
            exceptionList.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(exceptionList, HttpStatus.BAD_REQUEST);
    }
}
