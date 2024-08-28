package com.stone.springaiprac.service;

import com.stone.springaiprac.model.Answer;
import com.stone.springaiprac.model.GetCapitalRequest;
import com.stone.springaiprac.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Override
    public Answer getCapital(GetCapitalRequest request) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt p = promptTemplate.create(Map.of("country", request.country()));
        return new Answer(chatClient.prompt(p)
                .call()
                .content());
    }
}
