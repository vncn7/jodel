package com.jodel;

import com.controller.PostController;
import com.model.Post;
import com.service.PostService;
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
public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private Post post;

    // Setup the post object before each test
    @BeforeEach
    public void setUp() {
        post = new Post();
        post.setAuthorId(1L);
        post.setText("test");
        post.setLatitude(0.0);
        post.setLongitude(0.0);
    }

    // Test the getPosts method
    @Test
    public void testGetAllPosts() {
        // Setup the mock to return a list with one element
        when(postService.getAllPosts()).thenReturn(List.of(post));

        // Call the getPosts method
        ResponseEntity<List<Post>> response = postController.getPosts();

        // Check the response
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(post, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(postService).getAllPosts(); // Ensure the service method was called
    }

    // Test the getPosts method
    @Test
    public void testGetPosts() {  
        double lat = 0.0;
        double lon = 0.0; 
        // Setup the mock to return a list with one element
        when(postService.getPosts(lat, lon)).thenReturn(List.of(post));

        // Call the getPosts method
        ResponseEntity<List<Post>> response = postController.getPosts(lat, lon);

        // Check the response
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(post, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(postService).getPosts(lat, lon); // Ensure the service method was called
    }

    // Test the addPost method
    @Test
    public void testAddPost() {
        // Setup the mock to return the post
        when(postService.save(post)).thenReturn(post);

        // Call the addPost method
        ResponseEntity<Post> response = postController.addPost(post);

        // Check the response
        assertEquals(post, response.getBody()); // Ensure the post is returned
        assertEquals(HttpStatus.OK, response.getStatusCode());  // Ensure the status code is OK
        verify(postService).save(post); // Ensure the service method was called
    }
}
