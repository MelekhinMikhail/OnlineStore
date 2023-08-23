package com.example.onlinestore.dao;

import com.example.onlinestore.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao {

    List<User> findAllUsers();

    User findUserById(long id);

    void saveUser(User user);

    void updateUser(long id, User updatedUser);

    void deleteUser(long id);
}
