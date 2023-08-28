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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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

        user.setRole(Role.ROLE_USER.name());
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

    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }

        return userDao.findUserByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с email'ом " + username + " не найден!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
