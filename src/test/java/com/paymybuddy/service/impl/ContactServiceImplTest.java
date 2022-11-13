package com.paymybuddy.service.impl;

import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private UserRepository userRepository;


    @Mock
    private SecurityContext context;

    @Mock
    private Authentication authentication;


    @Test()
    public void shouldThrowExceptionWhenUserIsInDatabase() throws Exception {
        //given
        User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0, null, null);
        Bank bank = new Bank(1 , "CIC");
        Contact contact = new Contact(1, any(), any());

        try {
            when(contactService.createContact(new Contact())).thenThrow(new Exception("This email exist in database"));
            contactService.createContact(contact);
        } catch (Exception e) {

            assertTrue(e instanceof Exception);
        }
    }


    @Test
    public void createContactTest() throws Exception {

        //given
        User user = new User(9,"hicham", "jager", null, "1234", 1000.0, null, null);
        Bank bank = new Bank(1 , "CIC");

        Contact contact = new Contact();
        contact.setUser(user);
        when(contactRepository.save(contact)).thenReturn(contact);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //when
        Contact createdContact = contactService.createContact(contact);

        //then
        assertEquals(createdContact.getUser().getFirstName(), "hicham");
        assertEquals(createdContact.getUser().getLastName(), "jager");

    }

    @Test
    public void saveFriendTest() throws Exception {

        //given
        User user = new User(9,"hicham", "jager", null, "1234", 1000.0, null, null);
        Bank bank = new Bank(1 , "CIC");

        Contact contact = new Contact();
        contact.setUser(user);
        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("");
        when(userRepository.findByEmail(any())).thenReturn(user);


        //when
        contactService.saveFriend(new ContactDto(), context);

        //then


    }
}