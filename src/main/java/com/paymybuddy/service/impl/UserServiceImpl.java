package com.paymybuddy.service.impl;


import com.paymybuddy.dto.UserDto;
import com.paymybuddy.mapper.UserMapper;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.BankRepository;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser() {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userMail);
    }

    @Override
    public User createUser(User user) {
        User userEmail = userRepository.findByEmail(user.getEmail());
        if (userEmail == null) {
            return userRepository.save(user);
        } else {
            userEmail.setEmail(user.getEmail());
            return userRepository.save(userEmail);
        }
    }








    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public User save(UserDto registrationDto) {
        User user = userMapper.toEntity(registrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
