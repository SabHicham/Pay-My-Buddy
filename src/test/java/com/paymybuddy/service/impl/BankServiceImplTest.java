package com.paymybuddy.service.impl;


import com.paymybuddy.model.Bank;
import com.paymybuddy.repository.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
public class BankServiceImplTest {

    @InjectMocks
    private BankServiceImpl bankService;

    @Mock
    private BankRepository bankRepository;

    @Test
    public void testModifyBankShouldModifyBankInformationsForExistingBank() {

        //given
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("CIC");


        when(bankRepository.findByName(bank.getName())).thenReturn(bank);
        when(bankRepository.save(bank)).thenReturn(bank);

        //when
        Bank createdBank = bankService.createAccount(bank);

        //then
        assertEquals(createdBank.getName(),"CIC");

    }

    @Test
    public void testCreateBankShouldReturnNewBank() {

        //given
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("CIC");

        when(bankRepository.findByName(bank.getName())).thenReturn(null);
        when(bankRepository.save(bank)).thenReturn(bank);

        //when
        Bank createdBank = bankService.createAccount(bank);

        //then
        assertEquals(createdBank.getName(), "CIC");

    }
    @Test
    public void findAllBankTest() {

        //given
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("CIC");

        when(bankRepository.findAll()).thenReturn(Arrays.asList(bank));

        //when
        List<Bank> banks = bankService.findAll();

        //then
        assertEquals(banks.get(0).getName(), "CIC");

    }
}