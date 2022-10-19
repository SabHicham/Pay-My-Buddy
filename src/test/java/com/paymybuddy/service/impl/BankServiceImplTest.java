package com.paymybuddy.service.impl;


import com.paymybuddy.model.Bank;
import com.paymybuddy.repository.BankRepository;
import com.paymybuddy.service.impl.BankServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
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
        assertThat(createdBank.getName()).isEqualTo("CIC");

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
        assertThat(createdBank.getName()).isEqualTo("CIC");

    }
}