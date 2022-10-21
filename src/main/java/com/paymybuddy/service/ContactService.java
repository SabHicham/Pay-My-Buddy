package com.paymybuddy.service;

import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact) throws Exception;

    List<ContactDto> listOfContacts(User user);

    void saveFriend(ContactDto contactDto);
}
