package com.paymybuddy.mapper;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper implements Mapper<User, UserDto>{

    @Override
    public User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(), dto.getSold());
    }

    @Override
    public UserDto toDTO(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getSold(), "");
    }
}
