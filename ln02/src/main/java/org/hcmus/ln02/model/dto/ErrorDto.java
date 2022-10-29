package org.hcmus.ln02.model.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDto {
  private LocalDateTime timestamp;
  private HttpStatus status;
  private String error;
  private String message;
  private String path;
}
