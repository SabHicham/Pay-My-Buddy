package com.paymybuddy.service;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;


import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction) throws Exception;


    void transfetMoneyFromBank(int amount);

    void transfetMoneyToBank(int amount);

    List<Transaction> findByEmitter(User user);


}
