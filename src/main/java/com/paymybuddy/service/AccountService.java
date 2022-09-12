package com.paymybuddy.service;



import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> listOfAccounts(User user);

    List<Account> listOfUserAccounts();
}
