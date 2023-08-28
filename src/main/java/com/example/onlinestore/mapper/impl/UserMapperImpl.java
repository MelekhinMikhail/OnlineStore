package com.example.onlinestore.mapper.impl;

import com.example.onlinestore.dto.auth.CreateUserDto;
import com.example.onlinestore.dto.user.UserDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToEntity(CreateUserDto dto) throws ParseException {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setDateOfBirth(new SimpleDateFormat("dd.MM.yyyy").parse(dto.getDateOfBirth()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(dto.getAddress());

        return user;
    }

    @Override
    public UserDto mapToDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setDateOfBirth(new SimpleDateFormat("dd.MM.yyyy").format(user.getDateOfBirth()));
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAddress(user.getAddress());
        dto.setRole(user.getRole());

        return dto;
    }
}
