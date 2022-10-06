package com.paymybuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionDto {
    private int id;
    private String description;
    private double amount;
    private String emitterEmail;
    private String receiverEmail;
    private UserDto emitter;
    private UserDto receiver;


    public TransactionDto() {
    }
}
