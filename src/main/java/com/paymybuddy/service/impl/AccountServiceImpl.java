package com.paymybuddy.service.impl;

import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        Account accountIban = accountRepository.findByIban(account.getIban());
        if (accountIban == null) {
            return accountRepository.save(account);
        } else {
            accountIban.setBank(account.getBank());
            return accountRepository.save(accountIban);
        }
    }

    @Override
    public List<Account> listOfAccounts(User user) {
        return null;
    }

    @Override
    public List<Account> listOfUserAccounts() {
        return null;
    }
}
