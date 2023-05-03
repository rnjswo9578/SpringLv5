package com.example.bloglv4.like.service;

import com.example.bloglv4.comment.dto.CommentResponseDto;
import com.example.bloglv4.comment.entity.Comment;
import com.example.bloglv4.comment.repository.CommentRepository;
import com.example.bloglv4.like.entity.CommentLike;
import com.example.bloglv4.like.entity.PostLike;
import com.example.bloglv4.like.repository.CommentLikeRepository;
import com.example.bloglv4.like.repository.PostLikeRepository;
import com.example.bloglv4.post.dto.PostResponseDto;
import com.example.bloglv4.post.entity.Post;
import com.example.bloglv4.post.repository.PostRepository;
import com.example.bloglv4.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<CommentResponseDto> likeComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("comment 가 없습니다!")
        );

        CommentLike commentLike = new CommentLike(user.getId(), comment);
        commentLikeRepository.saveAndFlush(commentLike);
        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<PostResponseDto> likePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("post 가 없습니다!")
        );

        PostLike postLike = new PostLike(user.getId(), post);
        postLikeRepository.saveAndFlush(postLike);
        return new ResponseEntity<>(new PostResponseDto(post), HttpStatus.OK);
    }
}
