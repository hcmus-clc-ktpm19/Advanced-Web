package org.hcmus.sakila.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.exception.TokenRefreshException;
import org.hcmus.sakila.model.entity.Account;
import org.hcmus.sakila.model.entity.RefreshToken;
import org.hcmus.sakila.repository.AccountRepository;
import org.hcmus.sakila.repository.RefreshTokenRepository;
import org.hcmus.sakila.service.RefreshTokenService;
import org.hcmus.sakila.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@org.springframework.transaction.annotation.Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
  @Value("${jwt.RefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  JwtUtil jwtUtil;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(String username) {
    RefreshToken refreshToken = new RefreshToken();
    Optional<Account> account = accountRepository.findAccountByUsername(username);
    if(account.isPresent()) {
      refreshToken.setAccount(account.get());
      refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
      refreshToken.setToken(UUID.randomUUID().toString());
      return refreshTokenRepository.save(refreshToken);
    } else {
      return null;
    }
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }
    return token;
  }

  public void updateExpiration(String token) {
    refreshTokenRepository.findByToken(token).ifPresent(refreshToken -> {
      refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
      refreshTokenRepository.save(refreshToken);
    });
  }

}
