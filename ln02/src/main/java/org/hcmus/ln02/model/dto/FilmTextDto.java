package org.hcmus.ln02.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link org.hcmus.ln02.model.entity.FilmText} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmTextDto implements Serializable {
  private Long filmId;
  private String title;
  private String description;
}