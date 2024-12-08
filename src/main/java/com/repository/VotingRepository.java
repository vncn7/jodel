package com.repository;

import com.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {
    List<Voting> findByCommentId(Long commentId); // New method to find votings by comment ID
    List<Voting> findByCommentIdAndValue(Long commentId, int value); // New method to find votings by comment ID and value
    Optional<Voting> findByCommentIdAndAuthorId(Long commentId, Long authorId); // New method to find votings by comment ID and author ID
}
