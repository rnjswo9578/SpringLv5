package com.example.bloglv4.comment.entity;

import com.example.bloglv4.comment.dto.CommentRequestDto;
import com.example.bloglv4.global.entity.Timestamped;
import com.example.bloglv4.like.entity.CommentLike;
import com.example.bloglv4.post.entity.Post;
import com.example.bloglv4.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CommentLike> likes = new ArrayList<>();


    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.username = user.getUsername();
        post.getComments().add(this);
    }

    public void update(CommentRequestDto commentRequestDto) {
        content = commentRequestDto.getContent();
    }
}
