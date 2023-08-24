package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.auth.CreateUserDto;
import com.example.onlinestore.dto.user.UserDto;
import com.example.onlinestore.entity.User;

import java.text.ParseException;

public interface UserMapper {
    User mapToEntity(CreateUserDto dto) throws ParseException;

    UserDto mapToDto(User user);
}
