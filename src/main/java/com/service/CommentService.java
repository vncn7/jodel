package com.service;

import com.model.Comment;
import com.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment addComment(String content, double longitude, double latitude, Long authorId, Long postId) {
        Comment comment = new Comment(content, longitude, latitude, authorId, postId);
        return commentRepository.save(comment);
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void removeComment(Long id) {
        commentRepository.deleteById(id);
    }
}