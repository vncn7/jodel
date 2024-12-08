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

    @BeforeEach
    public void setUp() {
        comment = new Comment();
        comment.setAuthorId(1L);
        comment.setPostId(1L);
        comment.setText("test");
        comment.setLatitude(0.0);
        comment.setLongitude(0.0);
    }

    @Test
    public void testGetComments() {
        Long postId = 1L;
        when(commentService.getComments(postId)).thenReturn(List.of(comment));
    
        ResponseEntity<List<Comment>> response = commentController.getComments(postId);
    
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(comment, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        verify(commentService).getComments(postId);
    }

    @Test
    public void testAddComment() {
        when(commentService.save(comment)).thenReturn(comment);
    
        ResponseEntity<Comment> response = commentController.addComment(comment);
    
        assertEquals(comment, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        verify(commentService).save(comment);
    }
}
