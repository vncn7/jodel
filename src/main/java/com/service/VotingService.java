package com.service;


import com.model.Voting;
import com.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    // Get all votes for a specific comment
    public List<Voting> getVotings(Long commentId) {
        return votingRepository.findByCommentId(commentId);
    }

    // Get all likes for a specific comment (value = 1)
    public List<Voting> getLikes(Long commentId) {
        return votingRepository.findByCommentIdAndValue(commentId, 1); // 1 for likes
    }

    // Get all dislikes for a specific comment (value = -1)
    public List<Voting> getDislikes(Long commentId) {
        return votingRepository.findByCommentIdAndValue(commentId, -1); // -1 for dislikes
    }

    // Get a specific vote by user ID and comment ID
    public Voting getVoteByUserAndComment(Long userId, Long commentId) {
        Optional<Voting> existingVote = votingRepository.findByAuthorIdAndCommentId(userId, commentId);
        return existingVote.orElse(null); // Return null if no vote found
    }

    // Upvote a comment (set value = 1)
    public Voting upvote(Long userId, Long commentId) {
        // Check if the user has already voted
        Optional<Voting> existingVote = votingRepository.findByAuthorIdAndCommentId(userId, commentId);
        if (existingVote.isPresent()) {
            Voting vote = existingVote.get();
            if (vote.getValue() == -1) {
                vote.setValue(1); // Change dislike to like
                return votingRepository.save(vote);
            }
            return vote; // User already upvoted, return existing vote
        }

        // Create a new upvote if no vote exists
        Voting newVote = new Voting();
        newVote.setAuthorId(userId);
        newVote.setCommentId(commentId);
        newVote.setValue(1); // Upvote
        return votingRepository.save(newVote);
    }

    // Downvote a comment (set value = -1)
    public Voting downvote(Long userId, Long commentId) {
        // Check if the user has already voted
        Optional<Voting> existingVote = votingRepository.findByAuthorIdAndCommentId(userId, commentId);
        if (existingVote.isPresent()) {
            Voting vote = existingVote.get();
            if (vote.getValue() == 1) {
                vote.setValue(-1); // Change like to dislike
                return votingRepository.save(vote);
            }
            return vote; // User already downvoted, return existing vote
        }

        // Create a new downvote if no vote exists
        Voting newVote = new Voting();
        newVote.setAuthorId(userId);
        newVote.setCommentId(commentId);
        newVote.setValue(-1); // Downvote
        return votingRepository.save(newVote);
    }

    // Delete all votes for a specific comment
    public void deleteVotesForComment(Long commentId) {
        List<Voting> votes = votingRepository.findByCommentId(commentId);
        votingRepository.deleteAll(votes);
    }

    // Delete a specific vote by user and comment ID
    public void deleteVote(Long userId, Long commentId) {
        Optional<Voting> existingVote = votingRepository.findByAuthorIdAndCommentId(userId, commentId);
        existingVote.ifPresent(votingRepository::delete); // Delete if present
    }
}
