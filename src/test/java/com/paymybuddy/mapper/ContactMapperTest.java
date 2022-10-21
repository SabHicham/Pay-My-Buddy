package com.paymybuddy.mapper;

import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ContactMapperTest {

    private ContactMapper contactMapper;

    @BeforeEach
    public void setUp(){

        contactMapper = new ContactMapper();
    }
    @Test
    public void shouldMapContactToDto() {
        //given
        Contact contact = new Contact(1, new User(), new User());

        //when
        ContactDto contactDto = contactMapper.toDTO(contact);

        //then

        assertNotNull(contactDto);
        assertEquals(contactDto.getId(), 1);


    }

    @Test
    public void shouldMapContactToEntity() {
        //given
        ContactDto contactDto = new ContactDto();

        //when
        Contact contact = contactMapper.toEntity(contactDto);

        //then
        assertNull(contact);

    }

    private void asserNull(Contact contact) {
    }

}