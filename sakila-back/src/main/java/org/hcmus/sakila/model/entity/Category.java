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
@Table(name = "category")
@NoArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", columnDefinition = "TINYINT UNSIGNED")
  private Long categoryId;

  @Column(name = "name", columnDefinition = "VARCHAR(25)")
  private String name;

  @Column(name = "last_update", columnDefinition = "TIMESTAMP")
  @UpdateTimestamp
  private LocalDateTime lastUpdate;
}