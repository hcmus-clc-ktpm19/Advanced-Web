package org.hcmus.ln02.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "film_text")
public class FilmText {

  @Id
  @Column(name = "film_id", columnDefinition = "SMALLINT")
  private Long filmId;

  @Column(name = "title", columnDefinition = "VARCHAR(255)", nullable = false)
  @NotBlank
  private String title;

  @Column(name = "description", columnDefinition = "TEXT")
  @NotBlank
  private String description;
}
