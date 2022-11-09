package com.paymybuddy.service;

import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact) throws Exception;


    void saveFriend(ContactDto contactDto);

    void saveFriend(ContactDto contactDto, SecurityContext securityContext);
}
