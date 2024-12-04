package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import com.model.Post;
import com.model.User;
import com.service.PostService;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getPosts(
            @RequestParam(value = "lat", required = false) Double lat, 
            @RequestParam(value = "lon", required = false) Double lon) {
        if (lat != null && lon != null) {
            return ResponseEntity.ok(postService.getPosts(lat, lon));
        }
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost(
            @RequestParam("text") String text,
            @RequestParam("lon") double longitude,
            @RequestParam("lat") double latitude,
            @RequestParam("authorId") Long authorId) {
        Post post = new Post(text, longitude, latitude, authorId);
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