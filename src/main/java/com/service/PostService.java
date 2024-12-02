package com.service;

import com.model.Post;
import com.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPosts(Double lat, Double lon) {
        Double lonMin = lon - 0.14;
        Double lonMax = lon + 0.14;
        Double latMin = lat - 0.14;
        Double latMax = lat + 0.14;

        return postRepository.findByLongitudeBetweenAndLatitudeBetween(lonMin, lonMax, latMin, latMax);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteAllPosts() {
        postRepository.deleteAll();
    }
}
