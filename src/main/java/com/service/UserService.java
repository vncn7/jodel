package com.service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElse(new User(0L, "", ""));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public String deleteUser(User user) {
        userRepository.delete(user);
        return "User is deleted for userId: " + user.getId();
    }

}
