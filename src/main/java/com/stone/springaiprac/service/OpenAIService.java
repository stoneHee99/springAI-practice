package com.stone.springaiprac.service;

import com.stone.springaiprac.model.Answer;
import com.stone.springaiprac.model.GetCapitalRequest;
import com.stone.springaiprac.model.GetCapitalResponse;
import com.stone.springaiprac.model.Question;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest request);
}
