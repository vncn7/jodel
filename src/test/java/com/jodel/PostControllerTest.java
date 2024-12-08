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

    @BeforeEach
    public void setUp() {
        post = new Post();
        post.setAuthorId(1L);
        post.setText("test");
        post.setLatitude(0.0);
        post.setLongitude(0.0);
    }

    @Test
    public void testGetAllPosts() {
        when(postService.getAllPosts()).thenReturn(List.of(post));

        ResponseEntity<List<Post>> response = postController.getPosts();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(post, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(postService).getAllPosts();
    }

    @Test
    public void testGetPostsByLocation() {  // Changed method name
        double lat = 0.0;
        double lon = 0.0;
        when(postService.getPosts(lat, lon)).thenReturn(List.of(post));

        ResponseEntity<List<Post>> response = postController.getPosts(lat, lon);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(post, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(postService).getPosts(lat, lon);
    }

    @Test
    public void testAddPost() {
        when(postService.save(post)).thenReturn(post);

        ResponseEntity<Post> response = postController.addPost(post);

        assertEquals(post, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());  // POST should return CREATED
        verify(postService).save(post);
    }
}
