package com.progress.sprinthacking.Services.Impl;

import com.progress.sprinthacking.DTO.Event.EventDTO;
import com.progress.sprinthacking.DTO.Event.EventRequestDTO;

import java.util.List;

public interface IEventService {
    EventDTO createEvent(EventRequestDTO requestDTO);
    EventDTO getEventById(Long id);
    List<EventDTO> getAllEvents();
    EventDTO updateEvent(Long id, EventRequestDTO requestDTO);
    void deleteEvent(Long id);
}
