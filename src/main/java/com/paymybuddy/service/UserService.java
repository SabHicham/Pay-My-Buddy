package com.paymybuddy.service;

import com.paymybuddy.model.User;
import com.paymybuddy.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
