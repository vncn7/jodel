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

    @BeforeEach
    public void setUp() {
        post = new Post();
        post.setAuthorId(1L);
        post.setText("test");
        post.setLatitude(0.0);
        post.setLongitude(0.0);
    }

    @Test
    public void testGetPosts() {
        double lat = 0.0;
        double lon = 0.0;
        
        // Update the mock method to match the new repository method
        when(postRepository.findPostsWithin10km(lon, lat)).thenReturn(List.of(post));
        
        List<Post> posts = postService.getPosts(lat, lon);
        
        assertEquals(1, posts.size());
        assertEquals(post, posts.get(0));
        
        // Verify the correct method was called with correct parameters
        verify(postRepository).findPostsWithin10km(lon, lat);
    }

    @Test
    public void testGetAllPosts() {
        when(postRepository.findAll()).thenReturn(List.of(post));
        
        List<Post> posts = postService.getAllPosts();
        
        assertEquals(1, posts.size());
        assertEquals(post, posts.get(0));
        
        verify(postRepository).findAll();
    }

    @Test
    public void testSave() {
        when(postRepository.save(post)).thenReturn(post);
        
        Post savedPost = postService.save(post);
        
        assertEquals(post, savedPost);
        verify(postRepository).save(post);
    }
}