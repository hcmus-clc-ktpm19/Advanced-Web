package org.hcmus.ln02.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "actor")
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "actor_id")
  private Long id;

  @Column(name = "first_name", columnDefinition = "VARCHAR(45)")
  private String firstName;

  @Column(name = "last_name", columnDefinition = "VARCHAR(45)")
  private String lastName;

  @UpdateTimestamp
  @Column(name = "last_update", columnDefinition = "TIMESTAMP")
  private LocalDateTime lastUpdate;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Actor)) {
      return false;
    }
    Actor actor = (Actor) o;
    return Objects.equals(id, actor.id) && Objects.equals(firstName,
        actor.firstName) && Objects.equals(lastName, actor.lastName)
        && Objects.equals(lastUpdate, actor.lastUpdate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, lastUpdate);
  }
}
