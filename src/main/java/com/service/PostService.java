package com.service;

import com.model.Post;
import com.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public List<Post> getPostsByLocation(Double lat, Double lon) {
        Double lonMin = lon - 0.14;
        Double lonMax = lon + 0.14;
        Double latMin = lat - 0.14;
        Double latMax = lat + 0.14;

        return postRepository.findByLongitudeBetweenAndLatitudeBetween(lonMin, lonMax, latMin, latMax);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public String deletePost(Post post) {
        postRepository.delete(post);
        return "Post is deleted for postId: " + post.getId();
    }

}
