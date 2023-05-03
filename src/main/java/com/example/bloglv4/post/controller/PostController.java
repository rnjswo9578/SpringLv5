package com.example.bloglv4.post.controller;


import com.example.bloglv4.global.exception.RestApiException;
import com.example.bloglv4.global.security.UserDetailsImpl;
import com.example.bloglv4.post.dto.PostRequestDto;
import com.example.bloglv4.post.dto.PostResponseDto;
import com.example.bloglv4.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    @ResponseBody
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(Pageable pageable) {
        return postService.getPosts(pageable); //@RequestParam 으로 들어온 page=3&size=10&sort=id,DESC을 자동으로 pageable로 만들어줌!
    }
    //Page<T>

    @ResponseBody
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @ResponseBody
    @PostMapping("/post")
    public PostResponseDto createPost(
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    @ResponseBody
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(id, postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<RestApiException> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getUser());
    }


}
