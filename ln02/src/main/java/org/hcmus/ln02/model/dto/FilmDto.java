package org.hcmus.ln02.model.dto;

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

  @NotBlank(message = "Title must not be blank")
  private String title;

  private String description;

  private Integer releaseYear;

  @NotNull(message = "Language id must not be null")
  private Long languageId;

  private Long originalLanguageId;

  @NotNull(message = "Rental duration must not be null")
  private Long rentalDuration;

  @NotNull(message = "Rental rate must not be null")
  private Double rentalRate;

  private Long length;

  @NotNull(message = "Replacement cost must not be null")
  private Double replacementCost;

  private String rating;

  private String specialFeatures;
}
