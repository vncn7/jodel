package com.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Voting")
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "commentId")
    private Long commentId;

    @Column(name = "authorId")
    private Long authorId;

    @Column(name = "value")
    private int value;

    public Voting(int value) {
        this.value = value;
    }

    public Voting(Long commentId, Long authorId, int value) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.value = value;
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