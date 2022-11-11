package org.hcmus.sakila.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDto {

  @Schema(description = "Error's timestamp", example = "2021-05-01T00:00:00")
  @NotNull
  private LocalDateTime timestamp;

  @Schema(description = "Error's status", example = "500")
  @NotNull
  private HttpStatus status;

  @Schema(description = "Error's error", example = "Internal Server Error")
  private String error;

  @Schema(description = "Error's message", example = "Unexpected exception")
  @Size(max = 200)
  @NotEmpty
  private String message;

  @Schema(description = "Error's path", example = "/api/v1/films")
  private String path;
}
