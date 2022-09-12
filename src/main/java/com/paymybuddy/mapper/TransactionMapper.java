package com.paymybuddy.mapper;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper implements Mapper<Transaction, TransactionDto> {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Transaction toEntity(TransactionDto dto) {
        return new Transaction(dto.getId(), dto.getDescription(), dto.getAmount(),accountMapper.toEntity(dto.getEmitter()), accountMapper.toEntity(dto.getReceiver()));
    }

    @Override
    public TransactionDto toDTO(Transaction entity) {
        return null;
    }
}
