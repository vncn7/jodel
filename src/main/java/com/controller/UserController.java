package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import com.model.User;
import com.service.UserService;
import java.util.List;




@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user.getUsername(), user.getPassword()));
    }
    
    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }
    
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
