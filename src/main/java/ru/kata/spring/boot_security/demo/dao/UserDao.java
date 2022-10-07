package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    User findEmail(String email);

    List<User> getUsers();

    void update(User user);

    void delete(int id);

    void save(User user);

    User findUser(int id);
}