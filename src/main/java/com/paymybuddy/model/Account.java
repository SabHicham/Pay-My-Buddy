package com.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bank;
    private String iban;
    private double sold;
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean userAccount;
}
