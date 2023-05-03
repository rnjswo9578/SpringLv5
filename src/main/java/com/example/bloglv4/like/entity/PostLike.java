package com.example.bloglv4.like.entity;

import com.example.bloglv4.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn
    private Post postLike;

    public PostLike(Long userId, Post post){
        this.userId = userId;
        this.postLike = post;
        post.getLikes().add(this);
    }
}
