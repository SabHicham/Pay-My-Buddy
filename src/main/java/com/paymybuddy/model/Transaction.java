package com.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private double amount;


    @ManyToOne()
    @JoinColumn(name = "emitter", referencedColumnName = "id")
    private Account emitter;
    @ManyToOne()
    @JoinColumn(name = "received", referencedColumnName = "id")
    private Account receiver;


}