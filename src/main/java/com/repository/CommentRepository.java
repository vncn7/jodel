package com.repository;

import com.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    // New method to find comments by post ID
    List<Comment> findByPostId(long postId);
}