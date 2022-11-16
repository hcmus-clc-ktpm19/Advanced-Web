package org.hcmus.sakila.repository;

import java.util.Optional;
import org.hcmus.sakila.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);
}
