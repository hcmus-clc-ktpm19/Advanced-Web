package org.hcmus.sakila.repository;

import java.util.Optional;
import org.hcmus.sakila.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

  boolean existsByUsername(String username);

  Optional<Account> findAccountByUsername(String username);
}
