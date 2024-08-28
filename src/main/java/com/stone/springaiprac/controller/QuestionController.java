package com.stone.springaiprac.controller;

import com.stone.springaiprac.model.Answer;
import com.stone.springaiprac.model.Question;
import com.stone.springaiprac.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping
    public Answer ask(Question question) {
        return openAIService.getAnswer(question);
    }
}
