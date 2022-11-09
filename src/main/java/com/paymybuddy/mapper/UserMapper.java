package com.paymybuddy.mapper;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.Bank;
import com.paymybuddy.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;


@Service
public class UserMapper implements Mapper<User, UserDto>{

    @Override
    public User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(), dto.getSold(), dto.getFriends()!=null? dto.getFriends().stream().map(this::toEntity).collect(Collectors.toSet()):new HashSet<>(), null);
    }

    @Override
    public UserDto toDTO(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getSold(), user.getBank().getName(), user.getFriends().stream().map(this::toDTO).collect(Collectors.toSet()));
    }
}
