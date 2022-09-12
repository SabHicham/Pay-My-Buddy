package com.paymybuddy.repository;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Page<Transaction> findByEmitter(User user, Pageable pageable);
}
