package com.service;

import com.model.Voting;
import com.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    // get all votings for a comment
    public List<Voting> getVotings(Long commentId) {
        return votingRepository.findByCommentId(commentId);
    }

    // get likes for a comment
    public List<Voting> getLikes(Long commentId) {
        return votingRepository.findByCommentIdAndValue(commentId, 1); // 1 for like
    }

    // get dislikes for a comment
    public List<Voting> getDislikes(Long commentId) {
        return votingRepository.findByCommentIdAndValue(commentId, -1); // -1 for dislike
    }

    // Upvote a comment
    public Voting upvote(Voting voting) {
        Optional<Voting> existingVote = votingRepository.findByCommentIdAndAuthorId(voting.getCommentId(), voting.getAuthorId());
        // If the user has already voted on this comment update the vote
        if (existingVote.isPresent()) {
            Voting existingVoting = existingVote.get();
            existingVoting.setValue(1);  // Update the existing vote to upvote
            existingVoting.setCreatedAt();  // Update the timestamp
            return votingRepository.save(existingVoting);
        } else {
            // No previous vote, create a new one
            return votingRepository.save(voting);
        }
    }

    // Downvote a comment
    public Voting downvote(Voting voting) {
        Optional<Voting> existingVote = votingRepository.findByCommentIdAndAuthorId(voting.getCommentId(), voting.getAuthorId());
        // If the user has already voted on this comment update the vote
        if (existingVote.isPresent()) {
            Voting existingVoting = existingVote.get();
            existingVoting.setValue(-1);  // Update the existing vote to downvote
            existingVoting.setCreatedAt();  // Update the timestamp
            return votingRepository.save(existingVoting);
        } else {
            // No previous vote, create a new one
            return votingRepository.save(voting);
        }
    }
}
