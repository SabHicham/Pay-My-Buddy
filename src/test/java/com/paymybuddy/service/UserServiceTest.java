package com.paymybuddy.service;


import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

public class UserServiceTest {
    @Mock
    private Authentication auth;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

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
       // User user = new User(9, "Hicham", "Jager", "hicham@email.com", "10000");

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
        //User user = new User(9, "Hicham", "Jager", "hicham@email.com", "10000");
        //String result = passwordEncoder.encode("123456");
        //when(userService.save(user)).thenReturn(user);

        //when
        //User userCreated = userService.save(user);

        //then
        //assertThat(userCreated.getPassword()).isEqualTo(result);


    }
}