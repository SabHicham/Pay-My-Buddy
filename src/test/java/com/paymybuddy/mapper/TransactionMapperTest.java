package com.paymybuddy.mapper;

import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransactionMapperTest {

    @InjectMocks
    private TransactionMapper transactionMapper;
    @Mock
    private UserMapper userMapper;

    @Test
    public void shouldMapTransactionToDto() {
        //given
        Transaction transaction = new Transaction(1, "cinema", 100.0, "eren@email.com", "hicham@email.com", null, null);

        //when
        TransactionDto transactionDto = transactionMapper.toDTO(transaction);

        //then

        assertNull(transactionDto);
    }



    @Test
    public void shouldMapTransactionToEntity() {
        //given
        TransactionDto transactionDto = new TransactionDto(1, "cinema", 100.0, "eren@email.com", "hicham@email.com", new UserDto(), new UserDto());
        when(userMapper.toEntity(any())).thenReturn(new User());
        //when
        Transaction transaction = transactionMapper.toEntity(transactionDto);

        //then
        assertNotNull(transaction);
        assertEquals(transaction.getId(), 1);
        assertEquals(transaction.getDescription(), "cinema");
        assertEquals(transaction.getAmount(), 100.0);
        assertEquals(transaction.getEmitterEmail(), "eren@email.com");
        assertEquals(transaction.getReceiverEmail(), "hicham@email.com");
        assertNotNull(transaction.getEmitter());
        assertNotNull(transaction.getReceiver());


    }

}