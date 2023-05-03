package com.example.bloglv4.comment.dto;

import com.example.bloglv4.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String username;
    private int like;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createAt = comment.getCreateAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUsername();
        this.like = comment.getLikes().size();
    }
}
