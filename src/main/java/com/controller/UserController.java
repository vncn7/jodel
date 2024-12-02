package com.controller;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Login endpoint - expects JSON input for username and password
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        // Calling service layer to perform login check
        return userService.login(user.getUsername(), user.getPassword());
    }

    // Get all users endpoint
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID endpoint
    @GetMapping("/getUser")
    public User getUser(@RequestParam("userId") Long userId) {
        return userService.getUserById(userId);
    }
}