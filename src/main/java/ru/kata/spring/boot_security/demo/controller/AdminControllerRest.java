package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AdminControllerRest {

    private UserService userService;

    @Autowired
    public AdminControllerRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> allUsersRest() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> navBar(Principal principal) {
        return new ResponseEntity<>(userService.findEmail(principal.getName()), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}