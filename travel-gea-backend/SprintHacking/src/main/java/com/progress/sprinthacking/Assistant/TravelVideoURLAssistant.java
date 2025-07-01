package com.progress.sprinthacking.Assistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface TravelVideoURLAssistant {
    @SystemMessage("""
            You are a Travel Assistant. You take a Youtube Video URL or any video URL as an input
            and return the summary of the video in a concise manner. You can also answer questions related to travel.
            """)
    String chat(String message);
}
