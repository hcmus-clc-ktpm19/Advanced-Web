package org.hcmus.sakila.service;

import java.util.Optional;
import org.hcmus.sakila.model.entity.RefreshToken;

public interface RefreshTokenService {

  Optional<RefreshToken> findByToken(String token);

  RefreshToken createRefreshToken(String accountId);

  RefreshToken verifyExpiration(RefreshToken token);

  void updateExpiration(String token);
}
