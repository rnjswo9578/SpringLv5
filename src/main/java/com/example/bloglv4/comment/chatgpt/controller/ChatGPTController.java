package com.example.bloglv4.comment.chatgpt.controller;


import com.example.bloglv4.comment.chatgpt.Dto.ChatGPTResponseDto;
import com.example.bloglv4.comment.chatgpt.Dto.QuestionRequestDto;
import com.example.bloglv4.comment.chatgpt.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-gpt")
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGptService;

    @PostMapping("/question")
    public ChatGPTResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        return chatGptService.askQuestion(requestDto);
    }
}
