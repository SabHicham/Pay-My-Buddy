package com.paymybuddy.service;

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
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

public class ContactServiceTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Test()
    public void shouldThrowExceptionWhenUserIsInDatabase() throws Exception {
        //given
       // User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
       // Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
       // Contact contact = new Contact(1, "doe", "johndoe@gmail.com", "bb123456789", user, bank);

        try {
            when(contactService.createContact(new Contact())).thenThrow(new Exception("This email exist in database"));
       //     contactService.createContact(contact);
        } catch (Exception e) {

            assertThat(e instanceof Exception).isEqualTo(true);
        }
    }

    @Test
    public void ShouldReturnListOfContacts() {
        //given
       // User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
       // Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
       // Contact contact = new Contact(1, "doe", "johndoe@gmail.com", "bb123456789", user, bank);
        //List<Contact> contacts = Collections.singletonList(contact);
       // when(contactRepository.findByuserId(user.getId())).thenReturn(contacts);

        //when
        //List<Contact> listofContacts = contactService.ListOfContacts(user);

        //then
        //assertThat(listofContacts).isEqualTo(contacts);

    }

    @Test
    public void testCreateAccountShouldReturnAccountForNewAccount() throws Exception {

        //given
        //User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        //Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");

        Contact contact = new Contact();
        contact.setLastName("doe");
        contact.setEmail("john.doe@gmail.com");
        //contact.setUser(user);
        contact.setIban("bb123456789");
       // contact.setBank(bank);
        when(contactRepository.save(contact)).thenReturn(contact);

        //when
        Contact createdContact = contactService.createContact(contact);

        //then
        assertThat(createdContact.getBank().getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(createdContact.getIban()).isEqualTo("bb123456789");
        assertThat(createdContact.getUser().getLastName()).isEqualTo("doe");
        assertThat(createdContact.getLastName()).isEqualTo("doe");
        assertThat(createdContact.getEmail()).isEqualTo("john.doe@gmail.com");
    }
}