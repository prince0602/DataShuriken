package com.example.SupaDataShuriken.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> getErrorResponse(HttpStatus status, String message) {
        // TODO Auto-generated method stub
        return Map.of("error", Map.of("code", status.value(), "message", message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });
        errorResponse.put("error", Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", errors));
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ImproperRequestException.class)
    public ResponseEntity<Map<String, Object>> handleImproperRequestException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

}
