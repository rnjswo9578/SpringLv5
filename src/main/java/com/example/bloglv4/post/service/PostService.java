package com.example.bloglv4.post.service;


import com.example.bloglv4.global.exception.RestApiException;
import com.example.bloglv4.like.repository.PostLikeRepository;
import com.example.bloglv4.post.dto.PostRequestDto;
import com.example.bloglv4.post.dto.PostResponseDto;
import com.example.bloglv4.post.entity.Post;
import com.example.bloglv4.post.repository.PostRepository;
import com.example.bloglv4.user.entity.User;
import com.example.bloglv4.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable).getContent().stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long id) {
        return new PostResponseDto(getPostOrElseThrow(id));
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto,user);

        Long id = postRepository.saveAndFlush(post).getId();
        return new PostResponseDto(getPostOrElseThrow(id));
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = getPostOrElseThrow(id);

        if (user.getUsername().equals(post.getUser().getUsername()) || user.getUserRole().equals(UserRoleEnum.ADMIN)) {
            post.update(postRequestDto);
        }

        return new PostResponseDto(post);
    }

    @Transactional
    public ResponseEntity<RestApiException> deletePost(Long id, User user) {
        Post post = getPostOrElseThrow(id);

        if (user.getUsername().equals(post.getUser().getUsername()) || user.getUserRole().equals(UserRoleEnum.ADMIN)) {
            postRepository.delete(post);
        }

        RestApiException restApiException = new RestApiException();
        restApiException.setErrorMessage("삭제 성공");
        restApiException.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(restApiException, HttpStatus.OK);
    }

    private Post getPostOrElseThrow(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("post 가 없습니다!")
        );
    }
}
