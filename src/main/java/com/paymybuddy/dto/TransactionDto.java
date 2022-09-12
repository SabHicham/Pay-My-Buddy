package com.paymybuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionDto {
    private int id;
    private String description;
    private double amount;
    private AccountDto emitter;
    private AccountDto receiver;
}
