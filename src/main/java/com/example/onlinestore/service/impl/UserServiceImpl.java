package com.example.onlinestore.service.impl;

import com.example.onlinestore.dao.UserDao;
import com.example.onlinestore.dto.auth.CreateUserDto;
import com.example.onlinestore.entity.Role;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.security.PersonDetails;
import com.example.onlinestore.service.UserService;
import com.example.onlinestore.util.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    @Override
    public void addUser(CreateUserDto dto) throws ParseException {
        if (userDao.findUserByEmail(dto.getEmail()) != null) {
            throw new UserAlreadyExistsException();
        }

        User user = userMapper.mapToEntity(dto);

        if (user == null) {
            return;
        }

        user.setRole(Role.USER.name());
        user.setPassword(encoder.encode(user.getPassword()));

        Instant registrationDate = Instant.now();
        user.setRegistrationDate(registrationDate);
        user.setLastUpdate(registrationDate);

        userDao.saveUser(user);
    }

    @Override
    public User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
