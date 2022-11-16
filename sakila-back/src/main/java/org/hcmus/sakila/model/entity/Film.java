package org.hcmus.sakila.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "film")
@NoArgsConstructor
public class Film {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "film_id", columnDefinition = "SMALLINT(5) UNSIGNED", nullable = false)
  private Long filmId;

  @Column(name = "title", columnDefinition = "VARCHAR(255)", nullable = false)
  private String title;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "release_year", columnDefinition = "YEAR(4)")
  private Integer releaseYear;

  @Column(name = "language_id", columnDefinition = "TINYINT(3) UNSIGNED", nullable = false)
  private Long languageId;

  @Column(name = "original_language_id", columnDefinition = "TINYINT(3) UNSIGNED")
  private Long originalLanguageId;

  @Column(name = "rental_duration", columnDefinition = "TINYINT(3) UNSIGNED", nullable = false)
  private Long rentalDuration = 3L;

  @Column(name = "rental_rate", columnDefinition = "DECIMAL(4,2)", nullable = false)
  private Double rentalRate = 4.99;

  @Column(name = "length", columnDefinition = "SMALLINT(5) UNSIGNED")
  private Long length;

  @Column(name = "replacement_cost", columnDefinition = "DECIMAL(5,2)", nullable = false)
  private Double replacementCost = 19.99;

  @Column(name = "rating", columnDefinition = "ENUM('G','PG','PG-13','R','NC-17')")
  private String rating;

  @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
  private String specialFeatures;

  @UpdateTimestamp
  @Column(name = "last_update", columnDefinition = "TIMESTAMP", nullable = false)
  private LocalDateTime lastUpdate;
}
