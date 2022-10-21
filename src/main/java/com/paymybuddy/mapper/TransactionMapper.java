package com.paymybuddy.mapper;

import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper implements Mapper<Transaction, TransactionDto> {


    @Autowired
    private UserMapper userMapper;

    @Override
    public Transaction toEntity(TransactionDto dto) {
        return new Transaction(dto.getId(), dto.getDescription(), dto.getAmount(),dto.getEmitterEmail(),dto.getReceiverEmail(), dto.getEmitter()!=null?userMapper.toEntity(dto.getEmitter()):null, dto.getReceiver()!=null?userMapper.toEntity(dto.getReceiver()):null);
    }

    @Override
    public TransactionDto toDTO(Transaction entity) {
        return null;
    }
}
