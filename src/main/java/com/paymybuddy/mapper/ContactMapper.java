package com.paymybuddy.mapper;

import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.model.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactMapper implements Mapper<Contact, ContactDto> {
    @Override
    public Contact toEntity(ContactDto dto) {
        return null;
    }

    @Override
    public ContactDto toDTO(Contact entity) {
        return new ContactDto(entity.getId(), entity.getFriend().getFirstName(), entity.getFriend().getLastName(), entity.getFriend().getEmail());
    }
}
