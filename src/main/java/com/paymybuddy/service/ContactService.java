package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact) throws Exception;

    List<Contact> listOfContacts(User user);
}
