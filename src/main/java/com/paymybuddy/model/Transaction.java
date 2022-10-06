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
    @Transient
    private String emitterEmail;
    @Transient
    private String receiverEmail;


    @ManyToOne()
    @JoinColumn(name = "emitter", referencedColumnName = "id")
    private User emitter;
    @ManyToOne()
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    private User receiver;

    public Transaction(){

    }

}