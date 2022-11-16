package org.hcmus.sakila.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.exception.NotFoundException;
import org.hcmus.sakila.model.dto.AccountDto;
import org.hcmus.sakila.model.entity.Account;
import org.hcmus.sakila.repository.AccountRepository;
import org.hcmus.sakila.service.AccountService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {

  private final AccountRepository accountRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account user = accountRepository.findAccountByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Account not found with username: " + username));

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
    return new User(user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public Account findAccountByUsername(String username) {
    return accountRepository.findAccountByUsername(username)
        .orElseThrow(() -> new NotFoundException("Account not found" + username) {
        });
  }

  @Override
  public Long saveAccount(AccountDto accountDto) {
    if (accountRepository.existsByUsername(accountDto.getUsername())) {
      throw new RuntimeException("Account with username already existed" + accountDto.getUsername());
    }

    Account account = new Account();
    account.setUsername(accountDto.getUsername());
    account.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
    account.setRole(accountDto.getRole());

    return accountRepository.save(account).getId();
  }

  @Override
  public void saveAccount(Account account) {
    accountRepository.save(account);
  }
}
