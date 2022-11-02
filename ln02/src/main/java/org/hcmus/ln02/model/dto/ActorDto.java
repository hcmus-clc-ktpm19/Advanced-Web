package org.hcmus.ln02.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActorDto {

  @Schema(description = "Actor's id", example = "1")
  private Long id;

  @Schema(description = "Actor's first name", example = "Tom")
  private String firstName;

  @Schema(description = "Actor's last name", example = "Cruise")
  private String lastName;
}
