package org.hcmus.sakila.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRefreshRequest {
  @NotBlank
  private String refreshToken;
}
