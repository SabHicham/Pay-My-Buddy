package com.paymybuddy.mapper;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    public void setUp(){

        userMapper = new UserMapper();
    }
    @Test
    public void shouldMapUserToDto() {
        //given
        User user = new User(9, "hicham", "AZZEDDINE", "hicham@email.com", "1234", 10000.0, "frhicham", new HashSet<>() );

        //when
        UserDto userDto = userMapper.toDTO(user);

        //then

        assertNotNull(userDto);
        assertEquals(userDto.getFirstName(),"hicham");
        assertEquals(userDto.getLastName(), "AZZEDDINE");
        assertEquals(userDto.getEmail(), "hicham@email.com");
        assertEquals(userDto.getPassword(), "1234");
    }

    @Test
    public void shouldMapUserToEntity() {
        //given
        UserDto userDto = new UserDto(9, "hicham", "AZZEDDINE", "hicham@email.com", "1234", 10000.0, "CIC", new HashSet<>() );

        //when
        User user = userMapper.toEntity(userDto);

        //then
        assertNotNull(user);
        assertEquals(user.getFirstName(), "hicham");
        assertEquals(user.getLastName(), "AZZEDDINE");
        assertEquals(user.getEmail(), "hicham@email.com");
        assertEquals(user.getPassword(), "1234");

    }

}