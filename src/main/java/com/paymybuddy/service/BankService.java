package com.paymybuddy.service;

import com.paymybuddy.model.Bank;

import java.util.List;

public interface BankService {
    Bank createAccount(Bank bank);
    List<Bank> findAll();

}

