package com.paymybuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ContactDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;


    public ContactDto() {
    }
}
