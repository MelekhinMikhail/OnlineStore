package com.example.onlinestore.service;

import com.example.onlinestore.dto.auth.CreateUserDto;
import com.example.onlinestore.entity.User;

import java.text.ParseException;

public interface UserService {
    void addUser(CreateUserDto dto) throws ParseException;

    User getAuthorizedUser();

    User findUserByEmail(String email);
}
