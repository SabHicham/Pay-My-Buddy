package com.paymybuddy.service;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import org.springframework.security.core.context.SecurityContext;


import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction) throws Exception;


    void transfetMoneyFromBank(int amount);

    void transfetMoneyToBank(int amount);

    void transfetMoneyFromBank(int amount, SecurityContext securityContext);

    void transfetMoneyToBank(int amount, SecurityContext securityContext);

    List<Transaction> findByEmitter(User user);


}
