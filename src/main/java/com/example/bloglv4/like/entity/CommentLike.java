package com.example.bloglv4.like.entity;

import com.example.bloglv4.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn
    private Comment comment;

    public CommentLike(Long userId, Comment comment) {
        this.userId = userId;
        this.comment = comment;
        comment.getLikes().add(this);
    }
}
