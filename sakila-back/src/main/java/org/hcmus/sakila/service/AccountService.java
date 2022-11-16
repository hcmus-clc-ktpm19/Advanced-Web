package org.hcmus.sakila.service;


import org.hcmus.sakila.model.dto.AccountDto;
import org.hcmus.sakila.model.entity.Account;

public interface AccountService {

  Account findAccountByUsername(String username);

  Long saveAccount(AccountDto accountDto);

  void saveAccount(Account account);

}
