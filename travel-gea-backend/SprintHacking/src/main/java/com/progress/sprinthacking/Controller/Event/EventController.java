package com.progress.sprinthacking.Controller.Event;

import com.progress.sprinthacking.DTO.Event.EventDTO;
import com.progress.sprinthacking.DTO.Event.EventRequestDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.Services.Impl.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private IEventService eventService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_MUNICIPALITY','ROLE_PARTNER')")
    public ResponseEntity<ResponseDTO> createEvent(@RequestBody EventRequestDTO requestDTO) {
        EventDTO createdEvent = eventService.createEvent(requestDTO);
        Map<String, Object> response = Map.of("event", createdEvent);
        return new ResponseEntity<>(ResponseDTO.success("Event created successfully", response), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        Map<String, Object> response = Map.of("events", events);
        return new ResponseEntity<>(ResponseDTO.success("Events retrieved successfully", response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getEventById(@PathVariable Long id) {
        EventDTO event = eventService.getEventById(id);
        Map<String, Object> response = Map.of("event", event);
        return new ResponseEntity<>(ResponseDTO.success("Event retrieved successfully", response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateEvent(@PathVariable Long id, @RequestBody EventRequestDTO requestDTO) {
        EventDTO updatedEvent = eventService.updateEvent(id, requestDTO);
        Map<String, Object> response = Map.of("event", updatedEvent);
        return new ResponseEntity<>(ResponseDTO.success("Event updated successfully", response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(ResponseDTO.success("Event deleted successfully"), HttpStatus.OK);
    }
}
