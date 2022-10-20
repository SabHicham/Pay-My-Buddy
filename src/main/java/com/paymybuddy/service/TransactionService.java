package com.paymybuddy.service;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction) throws Exception;

    Transaction transferMoneyToBank(Transaction transaction, Authentication authentication);

    void transfetMoneyFromBank(int amount);

    List<Transaction> findByEmitter(User user);


}
