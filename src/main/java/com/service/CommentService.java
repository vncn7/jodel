package com.service;

import com.model.Comment;
import com.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class CommentService {
	
@Autowired
private CommentRepository commentRepository;


public List<Comment> getAllComments() {
	return (List<Comment>) commentRepository.findAll();
}

public List<Comment> getCommentsByPostId(long postId) {
    return commentRepository.findByPostId(postId);
}

public Comment getCommentById(long id) {
	return commentRepository.findById(id).get();
}

public Comment createComment(Comment comment) {
	return commentRepository.save(comment);
}

public String deleteComment(Comment  comment) {
	commentRepository.delete(comment);
	return "Comment is Deleted for commentId:"+comment.getId();
}}