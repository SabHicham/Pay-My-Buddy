package com.paymybuddy.mapper;

import com.paymybuddy.dto.BankDto;
import com.paymybuddy.model.Bank;
import org.springframework.stereotype.Service;

@Service
public class BankMapper implements Mapper<Bank, BankDto> {

    @Override
    public Bank toEntity(BankDto dto) {
        return new Bank(dto.getId(), dto.getName());
    }

    @Override
    public BankDto toDTO(Bank entity) {
        return null;
    }
}
