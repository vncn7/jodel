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
    public void getPosts() {
        double lat = 0.0;
        double lon = 0.0;
        when(postRepository.findByLatitudeAndLongitude(lat, lon)).thenReturn(List.of(post));

        List<Post> posts = postService.getPosts(lat, lon);

        assertEquals(1, posts.size());
        assertEquals(post, posts.get(0));

        verify(postRepository).findByLatitudeAndLongitude(lat, lon);
    }

    @Test
    public void getAllPosts() {
        when(postRepository.findAll()).thenReturn(List.of(post));

        List<Post> posts = postService.getAllPosts();

        assertEquals(1, posts.size());
        assertEquals(post, posts.get(0));

        verify(postRepository).findAll();
    }

    @Test
    public void save() {
        when(postRepository.save(post)).thenReturn(post);

        Post savedPost = postService.save(post);

        assertEquals(post, savedPost);

        verify(postRepository).save(post);
    }
}
