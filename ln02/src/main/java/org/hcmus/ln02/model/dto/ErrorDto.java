package org.hcmus.ln02.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDto {

  @NotNull
  private LocalDateTime timestamp;

  @NotNull
  private HttpStatus status;

  private String error;

  @Size(max = 200)
  @NotEmpty
  private String message;

  private String path;
}
