package com.paymybuddy.service;

import com.paymybuddy.model.Bank;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction) throws Exception;

    List<Transaction> findAll();
    Page<Transaction> pagination(User user, int pageNo, int pageSize);
}
