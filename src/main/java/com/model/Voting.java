package com.model;

import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name = "Tvoting")
public class Voting {

    // Sequence generator to auto increment the ID field
    @Id
    @SequenceGenerator(name = "votingSeq", sequenceName = "ZSEQ_VOTING_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "votingSeq") 
    
    @Column(name = "id")
    private Long id;
    @Column(name = "commentId")
    private Long commentId;
    @Column(name = "authorId")
    private Long authorId;
    @Column(name = "createdAt")
    private Date createdAt;
    @Column(name = "value")
    private int value;

    public Voting() {    	
    }

    public Voting(Long commentId, Long authorId, int value) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.value = value;
        this.createdAt = new Date();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(){
        this.createdAt = new Date();
    }
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}