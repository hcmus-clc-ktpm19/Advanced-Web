package org.hcmus.sakila.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

  @NotBlank
  private String username;

  @NotBlank
  private String password;
}
