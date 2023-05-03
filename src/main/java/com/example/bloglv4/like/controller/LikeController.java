package com.example.bloglv4.like.controller;

import com.example.bloglv4.comment.dto.CommentResponseDto;
import com.example.bloglv4.global.security.UserDetailsImpl;
import com.example.bloglv4.like.service.LikeService;
import com.example.bloglv4.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/comment/like/{id}")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likeComment(id, userDetails.getUser());
    }

    @PostMapping("/post/like/{id}")
    public ResponseEntity<PostResponseDto> deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likePost(id, userDetails.getUser());
    }
}
