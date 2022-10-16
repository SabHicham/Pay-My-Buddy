package com.paymybuddy.service.impl;


import com.paymybuddy.dto.ContactDto;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.mapper.ContactMapper;
import com.paymybuddy.model.Contact;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ContactDto> listOfContacts(User user) {
        List<Contact> contacts = contactRepository.findByuserId(user.getId());
        return contacts.stream().map(contactMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Contact createContact(Contact contact) throws Exception {
        Optional<User> user = userRepository.findById(contact.getId());
        if(user.isEmpty()){
            throw new Exception("this user doen't exist in database");
        }
        if (user.get().getEmail() == null) {
            return contactRepository.save(contact);
        } else {
            throw new Exception("This email exist in database");
        }
    }

    @Override
    public void saveFriend(ContactDto contactDto) {
        User userConnected = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User userToSave = userRepository.findByEmail(contactDto.getEmail());
        //check if userDto exist
        //check if userDto already friend
        if (Objects.nonNull(userToSave)) {
            Contact contact = new Contact();
            contact.setUser(userConnected);
            contact.setFriend(userToSave);
            contactRepository.save(contact);

        }


    }
}