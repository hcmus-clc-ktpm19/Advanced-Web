package org.hcmus.sakila.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDateTime;
import org.hcmus.sakila.exception.NotFoundException;
import org.hcmus.sakila.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController extends AbstractApplicationController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
    ErrorDto response = applicationMapper.toErrorDto(
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST,
        "Bad Request",
        ex.getBindingResult().getFieldError().getDefaultMessage(),
        "/api/v1/films"
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ApiResponse(responseCode = "202", description = "Resource not found")
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDto> handleActorNotFound(NotFoundException ex) {
    ErrorDto response = applicationMapper.toErrorDto(
        LocalDateTime.now(),
        HttpStatus.ACCEPTED,
        "Not Found",
        ex.getMessage(),
        null
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ApiResponse(responseCode = "500", description = "Unexpected exception")
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorDto> handleExceptions(Exception ex) {
    ErrorDto response = applicationMapper.toErrorDto(
        LocalDateTime.now(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Internal Server Error",
        ex.getMessage(),
        null
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
