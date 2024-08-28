package com.stone.springaiprac.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService{

    private final ChatClient chatClient;

    @Override
    public String getAnswer(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
