package com.stone.springaiprac.service;

import com.stone.springaiprac.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService{

    private final ChatClient chatClient;
    private final OpenAiAudioTranscriptionModel model;

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
    public GetCapitalResponse getCapital(GetCapitalRequest request) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt p = promptTemplate.create(Map.of("country", request.country(), "format", format));

        String responseString = chatClient.prompt(p).call().content();
        return converter.convert(responseString);
    }

    @Override
    public SummarizeResponse summarize(SummarizeRequest request, MultipartFile file) {
        String transcription = transcriptAudio(file);
        String summarizedTranscription = summarizeTranscription(transcription, request);
        return new SummarizeResponse(summarizedTranscription);
    }

    private String transcriptAudio(MultipartFile file) {
        Resource audioResource = convertToResource(file);
        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
                .withLanguage("ko")
                .withModel("whisper-1")
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .withTemperature(0f)
                .build();

        AudioTranscriptionResponse response = model.call(new AudioTranscriptionPrompt(audioResource, options));
        return response.getResult().getOutput();
    }

    private Resource convertToResource(MultipartFile file) {
        try {
            return new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to Resource", e);
        }
    }

    @Value("classpath:templates/summarize-counseling-record-prompt.st")
    private Resource summarizeCounselingRecordPrompt;

    private String summarizeTranscription(String transcription, SummarizeRequest request) {
        PromptTemplate promptTemplate = new PromptTemplate(summarizeCounselingRecordPrompt);
        Prompt p = promptTemplate.create(Map.of("transcription", transcription, "purpose", request.purpose(), "studentName", request.studentName()));
        return chatClient.prompt(p).call().content();
    }
}
