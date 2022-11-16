package org.hcmus.sakila.service.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.common.Constants;
import org.hcmus.sakila.model.entity.Account;
import org.hcmus.sakila.model.entity.RefreshToken;
import org.hcmus.sakila.repository.AccountRepository;
import org.hcmus.sakila.repository.RefreshTokenRepository;
import org.hcmus.sakila.service.RefreshTokenService;
import org.hcmus.sakila.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@org.springframework.transaction.annotation.Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

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
      refreshToken.setToken(jwtUtil.generateRefreshToken(username, Constants.REFRESH_TOKEN_URL));
      return refreshTokenRepository.save(refreshToken);
    } else {
      return null;
    }
  }


  @Transactional
  public int deleteByUserId(String accountId) {
    return refreshTokenRepository.deleteByAccount(accountRepository.findById(accountId).get());
  }

}
