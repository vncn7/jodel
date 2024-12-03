package com.controller;

import com.model.Post;
import com.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/posts/location")
    public ResponseEntity<List<Post>> getPostsByLocation(@RequestParam Double lat, @RequestParam Double lon) {
        return ResponseEntity.ok(postService.getPostsByLocation(lat, lon));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(postService.deletePost(post));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
