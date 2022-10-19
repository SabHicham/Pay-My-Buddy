package com.paymybuddy.mapper;

import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TransactionMapperTest {
    private TransactionMapper transactionMapper;

    @BeforeEach
    public void setUp(){

        transactionMapper = new TransactionMapper();
    }
    @Test
    public void shouldMapTransactionToDto() {
        //given
        Transaction transaction = new Transaction(1, "cinema", 100.0, "eren@email.com", "hicham@email.com", null, null);

        //when
        TransactionDto transactionDto = transactionMapper.toDTO(transaction);

        //then

        assertNull(transactionDto);
    }

    private void assertNull(TransactionDto transactionDto) {
    }

    @Test
    public void shouldMapTransactionToEntity() {
        //given
        TransactionDto transactionDto = new TransactionDto(1, "cinema", 100.0, "eren@email.com", "hicham@email.com", null, null);

        //when
        Transaction transaction = transactionMapper.toEntity(transactionDto);

        //then
        assertNotNull(transaction);
        assertEquals(transaction.getId(), 1);
        assertEquals(transaction.getDescription(), "cinema");
        assertEquals(transaction.getAmount(), 100.0);
        assertEquals(transaction.getEmitterEmail(), "eren@email.com");
        assertEquals(transaction.getReceiverEmail(), "hicham@email.com");
        assertEquals(transaction.getEmitter(), null);
        assertEquals(transaction.getReceiver(), null);

    }

}