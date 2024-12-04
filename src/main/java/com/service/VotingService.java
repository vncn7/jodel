package com.service;

import com.model.Voting;
import com.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    public List<Voting> getVotings(Long commentId) {
        return votingRepository.findByCommentId(commentId);
    }

    // Adjusted to handle value instead of isLike
    public List<Voting> getLikes(Long commentId) {
        return votingRepository.findByCommentIdAndValue(commentId, 1); // 1 for like
    }

    // Adjusted to handle value instead of isLike
    public List<Voting> getDislikes(Long commentId) {
        return votingRepository.findByCommentIdAndValue(commentId, -1); // -1 for dislike
    }

    public Voting upvote(Long userId, Long commentId) {
        // Use the constructor that accepts parameters
        Voting voting = new Voting(commentId, userId, 1); // 1 for like
        return votingRepository.save(voting);
    }

    public Voting downvote(Long userId, Long commentId) {
        // Use the constructor that accepts parameters
        Voting voting = new Voting(commentId, userId, -1); // -1 for dislike
        return votingRepository.save(voting);
    }

}
