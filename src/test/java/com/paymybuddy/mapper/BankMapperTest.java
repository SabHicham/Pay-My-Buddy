package com.paymybuddy.mapper;

import com.paymybuddy.dto.BankDto;
import com.paymybuddy.model.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BankMapperTest {

    private BankMapper bankMapper;

    @BeforeEach
    public void setUp(){

        bankMapper = new BankMapper();
    }
    /*@Test
    public void shouldMapBankToDto() {
        //given
        Bank bank = new Bank(1,  "CIC");

        //when
        BankDto bankDto = bankMapper.toDTO(bank);

        //then

        assertNotNull(bankDto);
        assertEquals(bankDto.getId(),1);
        assertEquals(bankDto.getName(), "CIC");

    }*/

    @Test
    public void shouldMapBankToEntity() {
        //given
        BankDto bankDto = new BankDto(1,  "CIC");

        //when
        Bank bank = bankMapper.toEntity(bankDto);

        //then
        assertNotNull(bank);
        assertEquals(bank.getId(), 1);
        assertEquals(bank.getName(), "CIC");

    }

}