package com.stone.springaiprac.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("You are a helpful assistant.")
                .build();
    }

    @Bean
    public OpenAiAudioTranscriptionModel audioModel() {
        return new OpenAiAudioTranscriptionModel(new OpenAiAudioApi(apiKey));
    }
}
