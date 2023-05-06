package com.example.bloglv4.comment.chatgpt.service;

import com.example.bloglv4.comment.chatgpt.Dto.ChatGPTRequestDto;
import com.example.bloglv4.comment.chatgpt.Dto.ChatGPTResponseDto;
import com.example.bloglv4.comment.chatgpt.Dto.QuestionRequestDto;
import com.example.bloglv4.comment.chatgpt.config.ChatGPTConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGPTRequestDto> buildHttpEntity(ChatGPTRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGPTConfig.MEDIA_TYPE));
        headers.add(ChatGPTConfig.AUTHORIZATION, ChatGPTConfig.BEARER + chatGPTConfig.getApiKey());
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGPTResponseDto getResponse(HttpEntity<ChatGPTRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGPTResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGPTConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGPTResponseDto.class);

        return responseEntity.getBody();
    }

    public ChatGPTResponseDto askQuestion(QuestionRequestDto requestDto) {
        return this.getResponse(this.buildHttpEntity(
                new ChatGPTRequestDto(
                        ChatGPTConfig.MODEL,
                        requestDto.getQuestion(),
                        ChatGPTConfig.MAX_TOKEN,
                        ChatGPTConfig.TEMPERATURE,
                        ChatGPTConfig.TOP_P))
        );
    }
}
