package com.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String iban;
    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bank;
}
