package com.paymybuddy.repository;

import com.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUserId(int id);

    Account findByIban(String iban);

    List<Account> findByUserAccountIsTrue();
}
