package com.example.onlinestore.dao.impl;

import com.example.onlinestore.dao.UserDao;
import com.example.onlinestore.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(User.class, id);
    }

//    @Transactional
    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.save(user);
    }

    @Transactional
    @Override
    public void updateUser(long id, User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User userToBeUpdated = session.get(User.class, id);

        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setPassword(updatedUser.getPassword());
        userToBeUpdated.setRole(updatedUser.getRole());
        userToBeUpdated.setDateOfBirth(updatedUser.getDateOfBirth());
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setRequisites(updatedUser.getRequisites());
        userToBeUpdated.setImagePath(updatedUser.getImagePath());
        userToBeUpdated.setAddress(updatedUser.getAddress());
        userToBeUpdated.setRegistrationDate(updatedUser.getRegistrationDate());
        userToBeUpdated.setLastUpdate(updatedUser.getLastUpdate());
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(User.class, id));
    }

    @Override
    public User findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM User WHERE email = :paramName");
        query.setParameter("paramName", email);

        List<User> users = query.list();

        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
