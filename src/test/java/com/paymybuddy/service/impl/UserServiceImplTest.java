package com.paymybuddy.service.impl;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.mapper.UserMapper;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private SecurityContext context;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private Authentication authentication;



    @Test
    public void createUserTest(){
        //given
        User user = new User(9, "Hicham", "AZZEDDINE", "hicham@email.com", "1234", 10000.0);
        when(userRepository.findByEmail(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        //when
        User createUser = userService.createUser(new User());

        //then
        assertNotNull(createUser);
    }
    @Test
    public void createUserTest2(){
        //given
        User user = new User(9, "Hicham", "AZZEDDINE", "hicham@email.com", "1234", 10000.0);
        when(userRepository.findByEmail(any())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(user);
        //when
        User createUser = userService.createUser(new User());

        //then
        assertNotNull(createUser);
    }

    @Test
    public void findUserTest(){
        //given
        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("");
        when(userRepository.findByEmail(any())).thenReturn(new User());

        //when
        User findUser = userService.findUser(context);
        //then
        assertNotNull(findUser);
    }

    @Test
    public void shouldThrowExceptionWhenUserEmailOrPasswordAreInvalid() throws Exception {
        //given
        User user = new User(9, "Hicham", "AZZEDDINE", "hicham@email.com", "1234", 10000.0);

        try {
            when(userService.loadUserByUsername(any())).thenThrow(new UsernameNotFoundException("Invalid username or password."));
            //    when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

            //when
            //     userService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException e) {

            //then
            assertTrue(e instanceof UsernameNotFoundException);
        }
    }

    @Test
    public void emptyRawPasswordDoesNotMatchPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("password");
        assertFalse(encoder.matches("", result));
    }

    @Test
    public void $2yMatches() {
        // $2y is default version
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("password");
        assertFalse(result.equals("password"));
        assertTrue(encoder.matches("password", result));
    }


    @Test
    public void shouldReturnUserWhenUserSave() {

        //given
        when(userMapper.toEntity(any())).thenReturn(new User());
        when(passwordEncoder.encode(any())).thenReturn( "");
        when(userRepository.save(any())).thenReturn(new User());

        //when
        User userCreated = userService.save(new UserDto());

        //then
        assertNotNull(userCreated);


    }
    @Test
    public void loadUserByUsernameReturnTest() {

        //given

        when(userRepository.findByEmail(any())).thenReturn(new User(1, "a", "a", "a", "a", 10.0));

        //when
        userService.loadUserByUsername("a");


        //then


    }
}