package com.controller;

import com.model.Post;
import com.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/getPosts")
    public List<Post> getPosts(@RequestParam Double lat, @RequestParam Double lon) {
        return postService.getPosts(lat, lon);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @DeleteMapping("/deleteAllPosts")
    public void deleteAllPosts() {
        postService.deleteAllPosts();
    }
}
