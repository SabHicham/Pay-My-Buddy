package com.paymybuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double sold;
    private String bank;
    private Set<UserDto> friends;



    public UserDto() {
    }


}
