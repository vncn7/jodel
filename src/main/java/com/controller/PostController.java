package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.model.Post;
import com.service.PostService;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    // Returns all posts
    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    
    // Returns all posts for a given location
    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getPosts(

            @RequestParam("lat") Double lat, 
            @RequestParam("lon") Double lon) {
        return ResponseEntity.ok(postService.getPosts(lat, lon));
    }

    // Add post
    @PostMapping("/addPost")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
    	// Set the createdAt attribute to the current date
    	post.setCreatedAt();
        return ResponseEntity.ok(postService.save(post));
    }
}

























/*
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
*/