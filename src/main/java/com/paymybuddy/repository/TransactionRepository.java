package com.paymybuddy.repository;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Page<Transaction> findByEmitter(User user, Pageable pageable);

    List<Transaction> findByEmitter(User user);
}
