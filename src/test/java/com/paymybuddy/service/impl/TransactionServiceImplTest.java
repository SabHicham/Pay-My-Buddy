package com.paymybuddy.service.impl;

import com.paymybuddy.model.Account;
import com.paymybuddy.model.Bank;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.impl.TransactionServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    public void shouldThrowExceptionWhenNotEnoughMoneyOnAccount() throws Exception {
        //given
         User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0);
         Bank bank = new Bank(1 , "CIC");
         Transaction transaction = new Transaction(1, "cinema", 100.0, "eren@email.com", "hicham@email.com", any(),any());

        try {
            when(transactionService.createTransaction(new Transaction())).thenThrow(new Exception("Not enough money on account"));
            transactionService.createTransaction(transaction);
        } catch (Exception e) {

            assertThat(e instanceof Exception).isEqualTo(true);
        }
    }

    @Test
    public void testCreateTransactionShouldReturnTransactionForNewTransactionAndUpdateSoldForReceiverIsEqualEmitter() throws Exception {

        //given
        User emitter = new User(1, "eren", "jager", "eren@email.com","1234", 123456.0);
        Bank bank = new Bank(0,  "CIC");


        Transaction transaction = new Transaction();
        transaction.setEmitter(emitter);
        User user = new User();
        transaction.setAmount(100);
        transaction.setEmitterEmail("eren@email.com");
        transaction.setReceiver(user);
        transaction.setEmitter(user);

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //when
        Transaction createdTransaction = transactionService.createTransaction(transaction);



        //then
        assertThat((user.getSold())).isEqualTo(350);
        assertThat(createdTransaction.getAmount()).isEqualTo(100);
        assertThat(createdTransaction.getDescription()).isEqualTo("cinema");
        assertThat(createdTransaction.getEmitterEmail()).isEqualTo("eren@email.com");
        assertThat(createdTransaction.getReceiver().getSold()).isEqualTo(100.0);
        assertThat(createdTransaction.getEmitter().getLastName()).isEqualTo("jager");
    }

    @Test
    public void testCreateTransactionShouldReturnTransactionForNewTransactionForReceiverNotEqualEmitter() throws Exception {

        //given
        User userEmitter = new User(1, "eren", "jager", "eren@gmail.com", "1234", 0.1);
        User userReceiver = new User(2, "mikasa", "ackerman", "mikasa@gmail.com", "1234", 0.1);
        Bank bank = new Bank(1,"CIC");

        Transaction transaction = new Transaction();
        User user = new User();
        transaction.setEmitter(userEmitter);
        transaction.setAmount(100);
        transaction.setDescription("cinema");
        transaction.setEmitterEmail("eren@email.com");
        transaction.setReceiver(userReceiver);
        transaction.setEmitter(userEmitter);

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //when
        Transaction createdTransaction = transactionService.createTransaction(transaction);


        //then
        assertThat((user.getSold())).isEqualTo(150);
        assertThat(createdTransaction.getAmount()).isEqualTo(100);
        assertThat(createdTransaction.getDescription()).isEqualTo("cinema");
        assertThat(createdTransaction.getEmitterEmail()).isEqualTo("eren@email.com");
        assertThat(createdTransaction.getReceiver().getLastName()).isEqualTo("jager");
        assertThat(createdTransaction.getEmitter().getLastName()).isEqualTo("eren");
    }
}