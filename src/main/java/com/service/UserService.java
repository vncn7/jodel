package com.service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;





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
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
