package com.controller;

import com.service.CommentService;

import com.model.Comment;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Get comments by post ID
    @GetMapping("/getComments")
    public List<Comment> getCommentsByPostId(@RequestParam Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    // Add a new comment
    @PostMapping("/addComment")
    public Comment createComment(@RequestParam String content,
                                 @RequestParam double longitude,
                                 @RequestParam double latitude,
                                 @RequestParam Long authorId,
                                 @RequestParam Long postId) {
        return commentService.addComment(content, longitude, latitude, authorId, postId);
    }

    // Get a comment by its ID
    @GetMapping("/getComment/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    // Remove a comment by ID
    @DeleteMapping("/deleteComment/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.removeComment(id);
    }
}