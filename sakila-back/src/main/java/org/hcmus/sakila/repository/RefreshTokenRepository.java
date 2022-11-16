package org.hcmus.sakila.repository;

import java.util.Optional;
import org.hcmus.sakila.model.entity.Account;
import org.hcmus.sakila.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  @Modifying
  int deleteByAccount(Account account);
}
