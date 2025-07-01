package com.progress.sprinthacking.Controller;

import com.progress.sprinthacking.Assistant.TravelVideoURLAssistant;
import com.progress.sprinthacking.DTO.Reservation.ReservationDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Test {
    private final TravelVideoURLAssistant assistant;

    @GetMapping("/chat")
    public ResponseEntity<ResponseDTO> chat(String message) {
        return new ResponseEntity<>(assistant.chat(message), HttpStatus.OK);
    }
}
