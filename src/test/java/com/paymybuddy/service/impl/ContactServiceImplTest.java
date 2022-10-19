package com.paymybuddy.service.impl;

import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.model.Bank;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.service.impl.ContactServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Test()
    public void shouldThrowExceptionWhenUserIsInDatabase() throws Exception {
        //given
        User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0);
        Bank bank = new Bank(1 , "CIC");
        Contact contact = new Contact(1, any(), any());

        try {
            when(contactService.createContact(new Contact())).thenThrow(new Exception("This email exist in database"));
            contactService.createContact(contact);
        } catch (Exception e) {

            assertThat(e instanceof Exception).isEqualTo(true);
        }
    }

    @Test
    public void ShouldReturnListOfContacts() {
        //given
        User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0);
        Bank bank = new Bank(1, "CIC");
        Contact contact = new Contact(1, any(), any());
        List<Contact> contacts = Collections.singletonList(contact);
        when(contactRepository.findByuserId(user.getId())).thenReturn(contacts);

        //when
        List<ContactDto> listofContacts = contactService.listOfContacts(user);

        //then
        assertThat(listofContacts).isEqualTo(contacts);

    }

    @Test
    public void testCreateAccountShouldReturnAccountForNewAccount() throws Exception {

        //given
        User user = new User(9,"hicham", "jager", "hicham@email.com", "1234", 1000.0);
        Bank bank = new Bank(1 , "CIC");

        Contact contact = new Contact();
        contact.setUser(any());
        contact.setUser(any());
        contact.setUser(user);
        contact.setUser(any());
        contact.setFriend(any());
        when(contactRepository.save(contact)).thenReturn(contact);

        //when
        Contact createdContact = contactService.createContact(contact);

        //then
        assertThat(createdContact).isEqualTo(contact);
        //assertThat(createdContact).isEqualTo("bb123456789");
        //assertThat(createdContact.getUser().getLastName()).isEqualTo("doe");
        //assertThat(createdContact.getLastName()).isEqualTo("doe");
        //assertThat(createdContact.getEmail()).isEqualTo("john.doe@gmail.com");
    }
}