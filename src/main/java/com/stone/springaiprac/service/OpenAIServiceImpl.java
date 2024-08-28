package com.stone.springaiprac.service;

import com.stone.springaiprac.model.Answer;
import com.stone.springaiprac.model.Question;
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

    @Override
    public Answer getAnswer(Question question) {
        String answerContent = getAnswer(question.question());
        return new Answer(answerContent);
    }
}
