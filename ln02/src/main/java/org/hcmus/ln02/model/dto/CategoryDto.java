package org.hcmus.ln02.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hcmus.ln02.model.entity.Category;

/**
 * A DTO for the {@link Category} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {

  private Long categoryId;
  private String name;
  private LocalDateTime lastUpdate;
}