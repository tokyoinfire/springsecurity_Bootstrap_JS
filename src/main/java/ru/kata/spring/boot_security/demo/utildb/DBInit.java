package ru.kata.spring.boot_security.demo.utildb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
        String[] role1 = {"ROLE_ADMIN", "ROLE_USER"};
        userService.addUser(new User("Johny", "Bravo", "admin"),
                role1, "admin");
        String[] role2 = {"ROLE_USER"};
        userService.addUser(new User("Agent", "Smith", "user"),
                role2, "user");
    }
}
