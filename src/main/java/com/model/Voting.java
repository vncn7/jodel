package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Voting")
public class Voting {

	@Id
	@SequenceGenerator(name = "votingSeq", sequenceName = "ZSEQ_VOTING_ID", allocationSize = 1, initialValue = 10)
	@GeneratedValue(generator = "votingSeq")

	@Column(name = "id")
	private Long id;

	@Column(name = "commentId")
	private Long commentId;


	@Column(name = "authorId")
	private Long authorId;

	@Column(name = "value")
	private int value;

	public Voting() {

	}

	public Voting(int value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

}