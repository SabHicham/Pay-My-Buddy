package com.paymybuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Data
public class AccountDto {
    private int id;
    private UserDto user;
    private BankDto bank;
    private String iban;
    private double sold;


    private boolean userAccount;
    public AccountDto() {
    }
}