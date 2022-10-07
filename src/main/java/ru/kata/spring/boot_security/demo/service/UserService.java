package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User findEmail(String email);

    void update(User user);

    User findUser(int id);

    void addUser(User user, String[] roles, String pass);

    void delete(int id);

    void save(User user);
}