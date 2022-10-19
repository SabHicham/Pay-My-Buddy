package com.paymybuddy.service.impl;


import com.paymybuddy.dto.UserDto;
import com.paymybuddy.mapper.UserMapper;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.UserService;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private Authentication auth;
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    /*@BeforeEach
    private void setUp() {
        userService = new UserServiceImpl();
        userService.userRepository=userRepository;
        userService.userMapper=userMapper;
        userService.passwordEncoder=passwordEncoder;
    }*/

    @Test
    public void testPassword() {
        when(auth.getCredentials()).thenReturn("mockedPassword");
        SecurityContextHolder.getContext().setAuthentication(auth);
        //Access  getCredentials() which should return the mocked password
        SecurityContextHolder.getContext().getAuthentication().getCredentials();
        SecurityContextHolder.clearContext();
    }

    @Test
    public void shouldThrowExceptionWhenUserEmailOrPasswordAreInvalid() throws Exception {
        //given
        User user = new User(9, "Hicham", "AZZEDDINE", "hicham@email.com", "1234", 10000.0);

        try {
            when(userService.loadUserByUsername(anyString())).thenThrow(new UsernameNotFoundException("Invalid username or password."));
            //    when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

            //when
            //     userService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException e) {

            //then
            assertThat(e instanceof UsernameNotFoundException).isEqualTo(true);
        }
    }

    @Test
    public void emptyRawPasswordDoesNotMatchPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("password");
        assertThat(encoder.matches("", result)).isFalse();
    }

    @Test
    public void $2yMatches() {
        // $2y is default version
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("password");
        assertThat(result.equals("password")).isFalse();
        assertThat(encoder.matches("password", result)).isTrue();
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
}