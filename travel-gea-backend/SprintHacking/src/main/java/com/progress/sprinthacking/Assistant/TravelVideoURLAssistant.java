package com.progress.sprinthacking.Assistant;

import com.progress.sprinthacking.DTO.ResponseDTO;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface TravelVideoURLAssistant {
    @SystemMessage("""
            You are a Travel Assistant. clarify all the doubts of places that the user has and also recommend new events and places. but put the event and places in the detail
            """)
    ResponseDTO chat(String message);
}
