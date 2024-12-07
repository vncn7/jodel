package com.jodel;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository; 

    @Mock
    private PasswordEncoder passwordEncoder; 

    @InjectMocks
    private UserService userService; 

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("test");
        user.setPassword("test");
    }

    @Test
    public void testRegisterUser() {
        when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertEquals(user, savedUser);
        assertEquals("hashedPassword", savedUser.getPassword());

        verify(passwordEncoder).encode("test");
        verify(userRepository).save(user);
    }

    @Test
    public void testLogin() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(true);

        User loggedInUser = userService.login(user.getUsername(), user.getPassword());

        assertEquals(user, loggedInUser);

        verify(userRepository).findByUsername(user.getUsername());
        verify(passwordEncoder).matches(user.getPassword(), user.getPassword());
    }
    
    @Test 
    public void testGetUsers() {
        userService.getUsers();
        verify(userRepository).findAll();
    }

    @Test
    public void testGetUser() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getUser(userId);

        assertEquals(user, foundUser);

        verify(userRepository).findById(userId);
    }
}
