package org.hcmus.sakila.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  * This class is used to transfer data from client to server
  * and vice versa.
  * @NotNull: value must be not null, but it can be empty
  * @NotEmpty: value must be not null and its size must be greater than 0
  * @NotBlank: value must be not null and its size must be greater than 0 after trim
 */
@Data
@NoArgsConstructor
public class FilmDto {
  private Long filmId;

  @Schema(description = "Film's title", example = "The Fast and the Furious")
  @NotBlank(message = "Title must not be blank")
  private String title;

  @Schema(description = "Film's description", example = "A film about street racing")
  private String description;

  @Schema(description = "Film's release year", example = "2001")
  private Integer releaseYear;

  @Schema(description = "Film's language id", example = "1")
  @NotNull(message = "Language id must not be null")
  private Long languageId;

  @Schema(description = "Film's original language id", example = "1")
  private Long originalLanguageId;

  @Schema(description = "Film's rental duration", example = "3")
  @NotNull(message = "Rental duration must not be null")
  private Long rentalDuration;

  @Schema(description = "Film's rental rate", example = "4.99")
  @NotNull(message = "Rental rate must not be null")
  private Double rentalRate;

  @Schema(description = "Film's length", example = "100")
  private Long length;

  @Schema(description = "Film's replacement cost", example = "19.99")
  @NotNull(message = "Replacement cost must not be null")
  private Double replacementCost;

  @Schema(description = "Film's rating", example = "PG")
  private String rating;

  @Schema(description = "Film's special features", example = "Trailers,Commentaries")
  private String specialFeatures;
}
