package com.example.bloglv4.comment.service;

import com.example.bloglv4.comment.dto.CommentRequestDto;
import com.example.bloglv4.comment.dto.CommentResponseDto;
import com.example.bloglv4.comment.entity.Comment;
import com.example.bloglv4.comment.repository.CommentRepository;
import com.example.bloglv4.post.entity.Post;
import com.example.bloglv4.post.repository.PostRepository;
import com.example.bloglv4.user.entity.User;
import com.example.bloglv4.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("post 가 없습니다!")
        );

        Comment comment = new Comment(commentRequestDto.getContent(), post, user);

        commentRepository.saveAndFlush(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment( Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Comment 가 없습니다!")
        );

        if (user.getUsername().equals(comment.getUsername())|| user.getUserRole().equals(UserRoleEnum.ADMIN)) {
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        } else {
            throw new IllegalArgumentException("다른 유져의 댓글입니다!");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Comment 가 없습니다!")
        );

        if (user.getUsername().equals(comment.getUsername()) || user.getUserRole().equals(UserRoleEnum.ADMIN)) {
            commentRepository.delete(comment);
            return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
        }

        return new ResponseEntity<>("댓글 삭제 실패", HttpStatus.BAD_REQUEST);
    }
}


