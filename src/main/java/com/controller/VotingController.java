package com.controller;

import com.model.Voting;
import com.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VotingController {

    @Autowired
    private VotingService votingService;

    // Endpoint to get all votes for a specific comment
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<List<Voting>> getVotings(@PathVariable Long commentId) {
        List<Voting> votes = votingService.getVotings(commentId);
        return ResponseEntity.ok(votes);
    }

    // Endpoint to get all likes for a specific comment
    @GetMapping("/comment/{commentId}/likes")
    public ResponseEntity<List<Voting>> getLikes(@PathVariable Long commentId) {
        List<Voting> likes = votingService.getLikes(commentId);
        return ResponseEntity.ok(likes);
    }

    // Endpoint to get all dislikes for a specific comment
    @GetMapping("/comment/{commentId}/dislikes")
    public ResponseEntity<List<Voting>> getDislikes(@PathVariable Long commentId) {
        List<Voting> dislikes = votingService.getDislikes(commentId);
        return ResponseEntity.ok(dislikes);
    }

    // Endpoint to upvote a comment
    @PostMapping("/upvote")
    public ResponseEntity<Voting> upvote(@RequestParam Long userId, @RequestParam Long commentId) {
        Voting upvote = votingService.upvote(userId, commentId);
        return new ResponseEntity<>(upvote, HttpStatus.CREATED);
    }

    // Endpoint to downvote a comment
    @PostMapping("/downvote")
    public ResponseEntity<Voting> downvote(@RequestParam Long userId, @RequestParam Long commentId) {
        Voting downvote = votingService.downvote(userId, commentId);
        return new ResponseEntity<>(downvote, HttpStatus.CREATED);
    }

    // Endpoint to delete all votes for a specific comment
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteVotesForComment(@PathVariable Long commentId) {
        votingService.deleteVotesForComment(commentId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to delete a specific vote by user and comment ID
    @DeleteMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long userId, @PathVariable Long commentId) {
        votingService.deleteVote(userId, commentId);
        return ResponseEntity.noContent().build();
    }
}
