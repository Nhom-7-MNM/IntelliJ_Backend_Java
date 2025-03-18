package com.example.healthfitness.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<String> handlingRuntimeException(RuntimeException exception) {

        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
