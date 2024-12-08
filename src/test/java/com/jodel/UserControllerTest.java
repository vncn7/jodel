package com.jodel;

import com.controller.UserController;
import com.model.User;
import com.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

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
        when(userService.registerUser(user)).thenReturn(user);

        // Call the registerUser method
        User registeredUser = userController.registerUser(user);

        // Check the response
        assertEquals(user, registeredUser); // Ensure the user is returned
        verify(userService).registerUser(user); // Ensure the service method was called
    }

    // Test the login method
    @Test
    public void testLogin() {
        // Setup the mock to return the user
        when(userService.login(user.getUsername(), user.getPassword())).thenReturn(user);

        // Call the login method
        User loggedInUser = userController.login(user);

        // Check the response
        assertEquals(user, loggedInUser); // Ensure the user is returned
        verify(userService).login(user.getUsername(), user.getPassword()); // Ensure the service method was called
    }

    // Test the getUser method
    @Test
    public void testGetUser() {
        Long userId = 1L;
        // Setup the mock to return the user
        when(userService.getUser(userId)).thenReturn(user);
    
        // Call the getUser method
        ResponseEntity<User> response = userController.getUser(userId);
    
        // Check the response
        assertEquals(user, response.getBody()); // Ensure the user is returned
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(userService).getUser(userId); // Ensure the service method was called
    }
    
    // Test the getAllUsers method
    @Test
    public void testGetAllUsers() {
        // Setup the mock to return a list with one element
        when(userService.getUsers()).thenReturn(List.of(user));
    
        // Call the getAllUsers method
        ResponseEntity<List<User>> response = userController.getAllUsers();
    
        // Check the response
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(user, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(userService).getUsers(); // Ensure the service method was called
    }
}
