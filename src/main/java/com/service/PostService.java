package com.service;

import com.model.Post;
import com.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    // Returns all posts for a given location (latitude and longitude + 10km (in PostRepository))
    public List<Post> getPosts(double lat, double lon) {
        return postRepository.findPostsWithin10km(lon, lat);
    }

    // Returns all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Add post
    public Post save(Post post) {
        return postRepository.save(post);
    }
}