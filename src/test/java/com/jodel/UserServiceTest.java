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

    // Setup the user object before each test
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("test");
        user.setPassword("test");
    }

    // Test the registerUser method
    @Test
    public void testRegisterUser() {
        // Setup the mock to return the user
        when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword"); // simulate hashing
        // Update the mock to return the user
        when(userRepository.save(user)).thenReturn(user);

        // Call the registerUser method
        User savedUser = userService.registerUser(user);

        // Check the response
        assertEquals(user, savedUser); // Ensure the user is returned
        assertEquals("hashedPassword", savedUser.getPassword()); // Ensure the password is hashed
        verify(passwordEncoder).encode("test"); // Ensure the password was hashed
        verify(userRepository).save(user); // Ensure the repository method was called
    }

    // Test the login method
    @Test
    public void testLogin() {
        // Setup the mock to return the user
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        // Update the mock to return the user
        when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(true);

        // Call the login method
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());

        // Check the response
        assertEquals(user, loggedInUser); // Ensure the user is returned
        verify(userRepository).findByUsername(user.getUsername()); // Ensure the repository method was called
        verify(passwordEncoder).matches(user.getPassword(), user.getPassword()); // Ensure the password was checked
    }
    
    // Test the getUsers method
    @Test 
    public void testGetUsers() {
        // Call the getUsers method
        userService.getUsers();

        // Check the response
        verify(userRepository).findAll(); // Ensure the repository method was called
    }

    // Test the getUser method
    @Test
    public void testGetUser() {
        Long userId = 1L;

        // Setup the mock to return the user
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Call the getUser method
        User foundUser = userService.getUser(userId);

        // Check the response
        assertEquals(user, foundUser); // Ensure the user is returned
        verify(userRepository).findById(userId); // Ensure the repository method was called
    }
}
