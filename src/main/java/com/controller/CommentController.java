package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.model.Comment;
import com.service.CommentService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    // Working!
    @GetMapping("/getComments")
    public ResponseEntity<List<Comment>> getComments(@RequestParam("postId") Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    // Working!
    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        // Add explicit field setting
        comment.setCreatedAt();
        
        // Optional: Add validation logging
        System.out.println("Received comment: " + comment);
        
        return ResponseEntity.ok(commentService.save(comment));
    }
}
