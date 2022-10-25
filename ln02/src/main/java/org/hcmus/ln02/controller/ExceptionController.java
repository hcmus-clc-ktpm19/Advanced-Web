package org.hcmus.ln02.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("timestamp", String.valueOf(LocalDateTime.now()));
    response.put("status", "400");
    response.put("error", "Bad Request");
    response.put("message", ex.getBindingResult().getFieldError().getDefaultMessage());
    response.put("path", "/api/v1/films");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleExceptions(Exception ex) {
    Map<String, String> response = new HashMap<>();
    response.put("timestamp", String.valueOf(LocalDateTime.now()));
    response.put("status", "500");
    response.put("error", "Internal Server Error");
    response.put("message", ex.getMessage());
    response.put("path", "/api/v1/films");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
