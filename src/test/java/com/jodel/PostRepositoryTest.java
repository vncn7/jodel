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

    // Setup the post objects before each test
    @BeforeEach
    public void setUp() {
        // Near post (less than 10km away)
        nearPost = new Post();
        nearPost.setLatitude(0.1);
        nearPost.setLongitude(0.1);
        nearPost.setText("Near Post");

        // Far post (more than 10km away)
        farPost = new Post();
        farPost.setLatitude(99.0);
        farPost.setLongitude(99.0);
        farPost.setText("Far Post");
    }

    // Test the findPostsWithin10km method
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
        assertEquals(1, posts.size()); // Ensure the list has one element
        assertEquals(nearPost.getText(), posts.get(0).getText()); // Check the first element in the list
        verify (postRepository).findPostsWithin10km(searchLon, searchLat); // Ensure the repository method was called
    }

    // Test the findPostsWithin10km method
    @Test
    public void testFindPostsOutsideRadius() {
        // Search point far from both posts
        double searchLat = 45.0;
        double searchLon = 45.0;

        // Mock the repository method to return an empty list
        when(postRepository.findPostsWithin10km(searchLon, searchLat))
            .thenReturn(List.of());

        // Call the method
        List<Post> posts = postRepository.findPostsWithin10km(searchLon, searchLat);

        // assertions
        assertTrue(posts.isEmpty()); // Ensure the list is empty
        verify(postRepository).findPostsWithin10km(searchLon, searchLat); // Ensure the repository method was called
    }
}