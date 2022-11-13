package com.paymybuddy.service;

import com.paymybuddy.model.User;
import com.paymybuddy.dto.UserDto;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {
    User save(UserDto registrationDto);

    User findUser();

    User findUser(SecurityContext securityContext);

    User createUser(User user);

    User updateIbanUser(String iban);


}
