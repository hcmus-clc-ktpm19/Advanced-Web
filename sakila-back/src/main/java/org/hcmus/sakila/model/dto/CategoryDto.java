package org.hcmus.sakila.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hcmus.sakila.model.entity.Category;

/**
 * A DTO for the {@link Category} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

  @Schema(hidden = true)
  private Long categoryId;
  @Schema(description = "Category's name", example = "Food")
  private String name;
  @Schema(hidden = true)
  private LocalDateTime lastUpdate;
}