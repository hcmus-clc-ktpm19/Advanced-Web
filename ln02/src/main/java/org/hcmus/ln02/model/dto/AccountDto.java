package org.hcmus.ln02.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hcmus.ln02.model.enums.Role;

@Data
public class AccountDto {

  private Long id;

  @NotEmpty(message = "Username is required")
  @NotBlank(message = "Username must not be blank")
  private String username;

  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters and contain at least one letter, one number and must not contain spaces")
  private String password;

  @NotNull(message = "Role is required")
  private Role role;
}
