package com.stone.springaiprac.controller;

import com.stone.springaiprac.model.*;
import com.stone.springaiprac.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/summarize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SummarizeResponse summarize(@RequestPart SummarizeRequest request, @RequestPart("file") MultipartFile file) {
        return openAIService.summarize(request, file);
    }
}
