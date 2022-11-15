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
import lombok.ToString;
import org.hcmus.ln02.model.enums.Role;
import org.hibernate.annotations.UpdateTimestamp;


@Data
@Entity
@Table(name = "Account")
@ToString(exclude = {"password"})
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private Role role;

  @UpdateTimestamp
  @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updatedAt", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Account)) {
      return false;
    }
    Account account = (Account) o;
    return username.equals(account.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }
}
