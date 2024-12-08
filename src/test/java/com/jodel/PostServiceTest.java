package com.jodel;
import com.model.Post;
import com.repository.PostRepository;
import com.service.PostService;
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
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

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
    public void testGetPosts() {
        double lat = 0.0;
        double lon = 0.0;
        
        // Update the mock method to match the new repository method
        when(postRepository.findPostsWithin10km(lon, lat)).thenReturn(List.of(post));
        
        List<Post> posts = postService.getPosts(lat, lon);
        
        // Assertions
        assertEquals(1, posts.size()); // Ensure the list has one element
        assertEquals(post, posts.get(0)); // Check the first element in the list
        verify(postRepository).findPostsWithin10km(lon, lat); // Ensure the repository method was called
    }

    // Test the getAllPosts method
    @Test
    public void testGetAllPosts() {
        // Update the mock method to match the new repository method
        when(postRepository.findAll()).thenReturn(List.of(post));
        
        // Call the method
        List<Post> posts = postService.getAllPosts();
        
        // Assertions
        assertEquals(1, posts.size()); // Ensure the list has one element
        assertEquals(post, posts.get(0)); // Check the first element in the list
        verify(postRepository).findAll(); // Ensure the repository method was called
    }

    // Test the save method
    @Test
    public void testSave() {
        // Update the mock method to match the new repository method
        when(postRepository.save(post)).thenReturn(post);
        
        // Call the method
        Post savedPost = postService.save(post);
        
        // Assertions
        assertEquals(post, savedPost); // Ensure the post is returned
        verify(postRepository).save(post); // Ensure the repository method was called
    }
}