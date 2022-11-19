package org.hcmus.sakila.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link org.hcmus.sakila.model.entity.FilmText} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmTextDto implements Serializable {

  @Schema(description = "Film's id", example = "1")
  @NotNull
  private Long filmId;

  @Schema(description = "Film's title", example = "ACADEMY DINOSAUR")
  private String title;

  @Schema(description = "Film's description", example = "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies")
  private String description;
}