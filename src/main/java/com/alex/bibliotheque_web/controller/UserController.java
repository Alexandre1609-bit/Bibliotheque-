package com.alex.bibliotheque_web.controller;

import com.alex.bibliotheque_web.dao.UserDAO;
import com.alex.bibliotheque_web.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserDAO userDAO;
    public UserController (UserDAO userDAO) {this.userDAO = userDAO;}

    @PostMapping("/sign-in")
    public int addUser(@RequestBody User user) {
        return userDAO.addUser(user);
    }

    @PostMapping("/anonymize")
    public void anonymizeUser (@RequestBody User userToAnonymize) {
        userDAO.anonymizeUser(userToAnonymize);
    }

    @PostMapping("/login")
    public User connect (@RequestBody User user) {
       return userDAO.connect(user.getEmail(), user.getPswd());
    }
}
