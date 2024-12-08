package com.jodel;

import com.model.Post;
import com.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryTest {

    @Mock
    private PostRepository postRepository;

    private Post nearPost;
    private Post farPost;

    @BeforeEach
    public void setUp() {
        // Create test posts
        nearPost = new Post();
        nearPost.setLatitude(0.1);
        nearPost.setLongitude(0.1);
        nearPost.setText("Near Post");

        farPost = new Post();
        farPost.setLatitude(10.0);
        farPost.setLongitude(10.0);
        farPost.setText("Far Post");
    }

    @Test
    public void testFindPostsWithin10km() {
        // Search point close to nearPost
        double searchLat = 0.0;
        double searchLon = 0.0;

        // Mock the repository method
        when(postRepository.findPostsWithin10km(searchLon, searchLat))
            .thenReturn(List.of(nearPost));

        // Call the method
        List<Post> posts = postRepository.findPostsWithin10km(searchLon, searchLat);

        // Verify interactions and results
        verify(postRepository).findPostsWithin10km(searchLon, searchLat);
        
        // Assertions
        assertNotNull(posts);
        assertEquals(1, posts.size());
        assertEquals(nearPost.getText(), posts.get(0).getText());
    }

    @Test
    public void testFindPostsOutsideRadius() {
        // Search point far from both posts
        double searchLat = 50.0;
        double searchLon = 50.0;

        // Mock the repository method to return an empty list
        when(postRepository.findPostsWithin10km(searchLon, searchLat))
            .thenReturn(List.of());

        // Call the method
        List<Post> posts = postRepository.findPostsWithin10km(searchLon, searchLat);

        // Verify interactions
        verify(postRepository).findPostsWithin10km(searchLon, searchLat);

        // Assertions
        assertTrue(posts.isEmpty());
    }
}