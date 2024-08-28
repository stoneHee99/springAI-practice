package com.stone.springaiprac.controller;

import com.stone.springaiprac.model.Answer;
import com.stone.springaiprac.model.GetCapitalRequest;
import com.stone.springaiprac.model.GetCapitalResponse;
import com.stone.springaiprac.model.Question;
import com.stone.springaiprac.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer ask(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest request) {
        return openAIService.getCapital(request);
    }
}
