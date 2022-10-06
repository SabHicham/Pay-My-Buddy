package com.paymybuddy.mapper;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper implements Mapper<Account, AccountDto> {

    @Autowired
    public UserMapper  userMapper;
    @Autowired
    public BankMapper bankMapper;

    @Override
    public Account toEntity(AccountDto dto) {
        return new Account(dto.getId(), userMapper.toEntity(dto.getUser()), bankMapper.toEntity(dto.getBank()), dto.getIban(), dto.getSold());
    }

    @Override
    public AccountDto toDTO(Account entity) {
        return null;
    }
}
