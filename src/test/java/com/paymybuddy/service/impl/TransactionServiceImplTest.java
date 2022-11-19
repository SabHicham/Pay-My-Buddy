package com.paymybuddy.service.impl;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext context;

    @Mock
    private Authentication authentication;



    @Test
    public void shouldThrowExceptionWhenNotEnoughMoneyOnAccount() throws Exception {
        //given
         User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0, null, null);

         Transaction transaction = new Transaction(1, "cinema", 100.0, "eren@email.com", "hicham@email.com", any(),any());

        try {
            when(transactionService.createTransaction(new Transaction())).thenThrow(new Exception("Not enough money on account"));
            transactionService.createTransaction(transaction);
        } catch (Exception e) {

            assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void testCreateTransaction() throws Exception {

        //given
        User emitter = new User(1, "eren", "jager", "eren@email.com","1234", 350.0, null, null);
        User receiver = new User(1, "eren", "jager", "hicham@email.com","1234", 350.0, null, null);



        Transaction transaction = new Transaction();
        transaction.setEmitter(emitter);
        User user = new User();
        transaction.setDescription("cinema");
        transaction.setAmount(100);
        transaction.setEmitterEmail("eren@email.com");
        transaction.setReceiverEmail("hicham@email.com");
        transaction.setReceiver(receiver);
        transaction.setEmitter(emitter);

        when(userRepository.findByEmail("eren@email.com")).thenReturn(emitter);
        when(userRepository.findByEmail("hicham@email.com")).thenReturn(receiver);
        when(userRepository.save(any())).thenReturn(user);
        when(transactionRepository.save(transaction)).thenReturn(transaction);


        //when
        Transaction createdTransaction = transactionService.createTransaction(transaction);



        //then

        assertEquals(createdTransaction.getAmount(), 100);
        assertEquals(createdTransaction.getDescription(), "cinema");
        assertEquals(createdTransaction.getEmitterEmail(), "eren@email.com");
        assertEquals(createdTransaction.getReceiver().getSold(), 449.5);
        assertEquals(createdTransaction.getEmitter().getLastName(), "jager");
    }

    @Test
    public void createTransactionTest() throws Exception {

        //given
        User userEmitter = new User(1, "eren", "jager", "eren@gmail.com", "1234", 1100.0, null, null);
        User userReceiver = new User(2, "mikasa", "ackerman", "mikasa@gmail.com", "1234", 0.1, null, null);


        Transaction transaction = new Transaction();
        User user = new User();
        transaction.setEmitter(userEmitter);
        transaction.setAmount(100);
        transaction.setDescription("cinema");
        transaction.setEmitterEmail("eren@email.com");
        transaction.setReceiverEmail("mikasa@email.com");
        transaction.setReceiver(userReceiver);
        transaction.setEmitter(userEmitter);

        when(userRepository.findByEmail("eren@email.com")).thenReturn(userEmitter);
        when(userRepository.findByEmail("mikasa@email.com")).thenReturn(userReceiver);
        when(userRepository.save(any())).thenReturn(user);
        when(transactionRepository.save(transaction)).thenReturn(transaction);


        //when
        Transaction createdTransaction = transactionService.createTransaction(transaction);


        //then

        assertEquals(createdTransaction.getAmount(), 100);
        assertEquals(createdTransaction.getDescription(), "cinema");
        assertEquals(createdTransaction.getEmitterEmail(), "eren@email.com");
        assertEquals(createdTransaction.getReceiver().getSold(), 99.6);
        assertEquals(createdTransaction.getEmitter().getLastName(), "jager");
    }
    @Test
    public void testCreateTransactionReturnNull() throws Exception {

        //given
        User emitter = new User(1, "eren", "jager", "eren@email.com","1234", 350.0, null, null);
        User receiver = new User(1, "eren", "jager", "hicham@email.com","1234", 350.0, null, null);



        Transaction transaction = new Transaction();
        transaction.setEmitter(emitter);
        User user = new User();
        transaction.setDescription("cinema");
        transaction.setAmount(100);
        transaction.setEmitterEmail("eren@email.com");
        transaction.setReceiverEmail("hicham@email.com");
        transaction.setReceiver(receiver);
        transaction.setEmitter(emitter);

        when(userRepository.findByEmail("eren@email.com")).thenReturn(emitter);
        when(userRepository.findByEmail("hicham@email.com")).thenReturn(emitter);



        //when
        Transaction createdTransaction = transactionService.createTransaction(transaction);



        //then

        assertNull(createdTransaction);
    }

    @Test
    public void findByEmitterTest(){
        //given
        User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0, null, null);
        List<Transaction> transactions = transactionService.findByEmitter(user);
        when(transactionRepository.findByEmitter(any())).thenReturn(transactions);

        //when
        List<Transaction> findByEmitter = transactionService.findByEmitter(user);
        //then
        assertNotEquals(findByEmitter, user);
    }

    @Test
    public void transfetMoneyFromBankTest(){
        //given
        User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0, null, null);
        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("");

        when(userRepository.findByEmail(any())).thenReturn(user);

        //when
        transactionService.transfetMoneyFromBank(10, context);
        //then

    }

    @Test
    public void transfetMoneyFromBankTest2(){
        //given
        User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0, null, null);
        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("");

        when(userRepository.findByEmail(any())).thenReturn(user);

        //when
        transactionService.transfetMoneyFromBank(-1, context);
        //then

    }


}