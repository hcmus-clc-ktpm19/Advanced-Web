package org.hcmus.sakila.service;

import org.hcmus.sakila.model.entity.RefreshToken;

public interface RefreshTokenService {
  RefreshToken createRefreshToken(String accountId);

  int deleteByUserId(String accountId);
}
