package com.example.onlinestore.service;

import com.example.onlinestore.dao.UserDao;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(user);
    }
}
