package com.stone.springaiprac.service;

import com.stone.springaiprac.model.*;
import org.springframework.web.multipart.MultipartFile;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest request);

    SummarizeResponse summarize(SummarizeRequest request, MultipartFile file);
}
