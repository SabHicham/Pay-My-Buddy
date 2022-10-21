package com.paymybuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BankDto {
    private int id;
    private String name;
    public BankDto() {
    }
}