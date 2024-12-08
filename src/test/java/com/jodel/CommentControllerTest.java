package com.jodel;

import com.controller.CommentController;
import com.model.Comment;
import com.service.CommentService;
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
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks 
    private CommentController commentController;

    private Comment comment;

    // Setup the comment object before each test
    @BeforeEach
    public void setUp() {
        comment = new Comment();
        comment.setAuthorId(1L);
        comment.setPostId(1L);
        comment.setText("test");
        comment.setLatitude(0.0);
        comment.setLongitude(0.0);
    }

    // Test the getComments method
    @Test
    public void testGetComments() {
        Long postId = 1L; 
        
        // Setup the mock to return a list with one element
        when(commentService.getComments(postId)).thenReturn(List.of(comment)); 
    
        // Call the getComments method
        ResponseEntity<List<Comment>> response = commentController.getComments(postId);
    
        // Check the response
        assertNotNull(response.getBody()); // Ensure the list is not null
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(comment, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(commentService).getComments(postId);// Ensure the service method was called
    }

    // Test the addComment method
    @Test
    public void testAddComment() {
        when(commentService.save(comment)).thenReturn(comment);
    
        // Call the addComment method
        ResponseEntity<Comment> response = commentController.addComment(comment);
    
        // Check the response
        assertEquals(comment, response.getBody()); // Ensure the comment is returned
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(commentService).save(comment);// Ensure the service method was called
    }
}
