package org.hcmus.ln02.service;


import org.hcmus.ln02.model.dto.AccountDto;
import org.hcmus.ln02.model.entity.Account;

public interface AccountService {

  Account findAccountByUsername(String username);

  Long saveAccount(AccountDto accountDto);

  void saveAccount(Account account);

}
