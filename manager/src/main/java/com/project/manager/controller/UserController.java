package com.project.manager.controller;

import com.project.manager.model.User;
import com.project.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {

        return userService.getAllUser();
    }

    @GetMapping("/{userID}")
    public User getUserById(@PathVariable Long userID) {

        return userService.getUserById(userID);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        return userService.createUser(user);
    }

    @PutMapping("/{userID}")
    public User updateUser(@PathVariable Long userID, @RequestBody User userDetails) {
        return userService.updateUser(userID, userDetails);
    }

    @GetMapping("/username/{username}")
    public List<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
