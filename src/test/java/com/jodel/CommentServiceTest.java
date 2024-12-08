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
        when(commentRepository.findByPostId(comment.getPostId())).thenReturn(List.of(comment));
        
        List<Comment> comments = commentService.getComments(comment.getPostId());
        
        assertEquals(1, comments.size());
        assertEquals(comment, comments.get(0));
        
        verify(commentRepository).findByPostId(comment.getPostId());
    }

    @Test
    public void testSave() {
        when(commentRepository.save(comment)).thenReturn(comment);
        
        Comment savedComment = commentService.save(comment);
        
        assertEquals(comment, savedComment);
        
        verify(commentRepository).save(comment);
    }
}
