package com.repository;

import com.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {
    List<Voting> findByCommentId(Long commentId);
    
    // Adjusted to use value instead of isLike
    List<Voting> findByCommentIdAndValue(Long commentId, int value);
}
