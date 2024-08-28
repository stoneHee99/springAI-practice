package com.stone.springaiprac.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void getAnswer() {
        String answer = openAIService.getAnswer("기가 막히게 재밌는 농담하나 알려줘");
        System.out.println("Got the answer!");
        System.out.println(answer);
    }
}