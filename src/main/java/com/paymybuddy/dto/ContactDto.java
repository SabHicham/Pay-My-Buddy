package com.paymybuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ContactDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    private UserDto user;
    private BankDto bank;

    public ContactDto() {
    }
}
