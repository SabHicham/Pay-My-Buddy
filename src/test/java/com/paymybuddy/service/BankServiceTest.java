package com.paymybuddy.service;


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
public class BankServiceTest {

    @InjectMocks
    private BankServiceImpl bankService;

    @Mock
    private BankRepository bankRepository;

    @Test
    public void testModifyBankShouldModifyBankInformationsForExistingBank() {

        //given
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("LCL Melun Nord");
       // bank.setAddress("12 rue des oiseaux");
        //bank.setZip("77000");
        //bank.setCity("Melun");

        when(bankRepository.findByName(bank.getName())).thenReturn(bank);
        when(bankRepository.save(bank)).thenReturn(bank);

        //when
        Bank createdBank = bankService.createAccount(bank);

        //then
        assertThat(createdBank.getName()).isEqualTo("LCL Melun Nord");
       // assertThat(createdBank.getAddress()).isEqualTo("12 rue des oiseaux");
        //assertThat(createdBank.getZip()).isEqualTo("77000");
        //assertThat(createdBank.getCity()).isEqualTo("Melun");
    }

    @Test
    public void testCreateBankShouldReturnNewBank() {

        //given
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("LCL Melun Nord");
       // bank.setAddress("12 rue des oiseaux");
       // bank.setZip("77000");
       // bank.setCity("Melun");

        when(bankRepository.findByName(bank.getName())).thenReturn(null);
        when(bankRepository.save(bank)).thenReturn(bank);

        //when
        Bank createdBank = bankService.createAccount(bank);

        //then
        assertThat(createdBank.getName()).isEqualTo("LCL Melun Nord");
       // assertThat(createdBank.getAddress()).isEqualTo("12 rue des oiseaux");
       // assertThat(createdBank.getZip()).isEqualTo("77000");
       // assertThat(createdBank.getCity()).isEqualTo("Melun");
    }
}