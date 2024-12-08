package com.jodel;

import com.model.Comment;
import com.repository.CommentRepository;
import com.service.CommentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository; 

    @InjectMocks
    private CommentService commentService; 

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
        // Setup the mock to return a list with one element
        when(commentRepository.findByPostId(comment.getPostId())).thenReturn(List.of(comment));
        
        // Call the getComments method
        List<Comment> comments = commentService.getComments(comment.getPostId());
        
        // Check the response
        assertEquals(1, comments.size()); // Ensure the list has one element
        assertEquals(comment, comments.get(0)); // Check the first element in the list
        verify(commentRepository).findByPostId(comment.getPostId()); // Ensure the repository method was called
    }

    // Test the save method
    @Test
    public void testSave() {
        // Setup the mock to return the comment
        when(commentRepository.save(comment)).thenReturn(comment);
        
        // Call the save method
        Comment savedComment = commentService.save(comment);
        
        // Check the response
        assertEquals(comment, savedComment); // Ensure the saved comment is the same as the original
        verify(commentRepository).save(comment); // Ensure the repository method was called
    }
}
