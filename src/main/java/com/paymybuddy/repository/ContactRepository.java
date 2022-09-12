package com.paymybuddy.repository;

import com.paymybuddy.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    public Contact findById(int id);

    List<Contact> findByuserId(int id);

    Contact findByEmail(String email);
}
