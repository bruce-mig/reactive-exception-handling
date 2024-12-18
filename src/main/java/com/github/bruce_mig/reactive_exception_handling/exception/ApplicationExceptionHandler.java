package com.github.bruce_mig.reactive_exception_handling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(BookAIException.class)
    public ResponseEntity<?> handleBookAPIException(BookAIException bookAIException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", bookAIException.getMessage());
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
}
