package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Comment;
import com.service.CommentService;

//import com.model.Comment;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/getComments")
    public ResponseEntity<List<Comment>> getComments(@RequestParam("postId") Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(
            @RequestParam("text") String text,
            @RequestParam("lon") double longitude,
            @RequestParam("lat") double latitude,
            @RequestParam("authorId") Long authorId,
            @RequestParam("postId") Long postId) {
        Comment comment = new Comment(text, longitude, latitude, authorId, postId);
        return ResponseEntity.ok(commentService.save(comment));
    }
}
