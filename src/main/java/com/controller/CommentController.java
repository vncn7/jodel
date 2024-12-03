package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.model.Comment;
import com.service.CommentService;

//import com.model.Comment;

import java.util.List;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getAllComments() {
		return ResponseEntity.ok(commentService.getAllComments());
	}
	
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(commentService.getCommentById(id));
	}
	
	@PostMapping("/comments")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.createComment(comment));
	}

	@DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(commentService.deleteComment(comment));
    }
	
}