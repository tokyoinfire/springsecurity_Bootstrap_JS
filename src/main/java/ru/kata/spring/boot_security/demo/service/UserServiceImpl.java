package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }


    @Override
    public User findEmail(String email) {
        return userDao.findEmail(email);
    }

    @Transactional
    @Override
    public void update(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }


    @Transactional
    @Override
    public void save(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        userDao.save(user);
    }


    @Override
    public User findUser(int id) {
        return userDao.findUser(id);
    }

    @Transactional
    @Override
    public void addUser(User user, String[] roles, String pass) {
        user.setPassword(passwordEncoder.encode(pass));
        user.setRoles(Arrays.stream(roles)
                .map(role -> roleService.findRoles(role))
                .collect(Collectors.toList()));
        userDao.save(user);
    }
}