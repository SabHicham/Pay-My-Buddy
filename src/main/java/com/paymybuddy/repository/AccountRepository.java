package com.paymybuddy.repository;

import com.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUserId(int id);

    Account findByIban(String iban);

}
