package com.repository;

import com.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {

    // Find votings by comment ID
    List<Voting> findByCommentId(Long commentId);

    // Find likes for a specific comment
    List<Voting> findByCommentIdAndValue(Long commentId, int value);

    // Find a specific voting by user ID and comment ID
    Optional<Voting> findByAuthorIdAndCommentId(Long authorId, Long commentId);
}