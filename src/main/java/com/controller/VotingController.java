package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import com.model.Voting;
import com.service.VotingService;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VotingController {
    
    @Autowired
    private VotingService votingService;
    
    @GetMapping("/getLikes")
    public ResponseEntity<List<Voting>> getLikes(@RequestParam("commentId") Long commentId) {
        return ResponseEntity.ok(votingService.getLikes(commentId));
    }
    
    @GetMapping("/getDislikes")
    public ResponseEntity<List<Voting>> getDislikes(@RequestParam("commentId") Long commentId) {
        return ResponseEntity.ok(votingService.getDislikes(commentId));
    }
    
    @PostMapping("/upvote")
    public ResponseEntity<Voting> upvote(
            @RequestParam("authorId") Long userId, 
            @RequestParam("commentId") Long commentId) {
        return ResponseEntity.ok(votingService.upvote(userId, commentId));
    }
    
    @PostMapping("/downvote")
    public ResponseEntity<Voting> downvote(
            @RequestParam("authorId") Long userId, 
            @RequestParam("commentId") Long commentId) {
        return ResponseEntity.ok(votingService.downvote(userId, commentId));
    }
}
