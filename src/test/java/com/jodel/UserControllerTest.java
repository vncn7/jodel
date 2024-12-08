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

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("test");
        user.setPassword("test");
    }

    @Test
    public void testRegisterUser() {
        when(userService.registerUser(user)).thenReturn(user);

        User registeredUser = userController.registerUser(user);

        assertEquals(user, registeredUser);
        verify(userService).registerUser(user);
    }

    @Test
    public void testLogin() {
        when(userService.login(user.getUsername(), user.getPassword())).thenReturn(user);

        User loggedInUser = userController.login(user);

        assertEquals(user, loggedInUser);
        verify(userService).login(user.getUsername(), user.getPassword());
    }

    @Test
    public void testGetUser() {
        Long userId = 1L;
        when(userService.getUser(userId)).thenReturn(user);
    
        ResponseEntity<User> response = userController.getUser(userId);
    
        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        verify(userService).getUser(userId);
    }
    
    @Test
    public void testGetAllUsers() {
        when(userService.getUsers()).thenReturn(List.of(user));
    
        ResponseEntity<List<User>> response = userController.getAllUsers();
    
        assertEquals(1, response.getBody().size());
        assertEquals(user, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        verify(userService).getUsers();
    }
}
