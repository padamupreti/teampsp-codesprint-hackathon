package com.progress.sprinthacking.Services.Event;

import com.progress.sprinthacking.DTO.Event.EventDTO;
import com.progress.sprinthacking.DTO.Event.EventRequestDTO;
import com.progress.sprinthacking.Entity.Event;
import com.progress.sprinthacking.Entity.Municipality;
import com.progress.sprinthacking.Entity.PartnerOrg;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.EventRepo;
import com.progress.sprinthacking.Repo.MunicipalityRepo;
import com.progress.sprinthacking.Repo.PartnerOrgRepo;
import com.progress.sprinthacking.Services.Impl.IEventService;
import com.progress.sprinthacking.Utils.Event.EventUtils;
import com.progress.sprinthacking.Utils.GetUserFromUserId;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepo eventRepo;
    @Autowired private PartnerOrgRepo partnerOrgRepo;
    @Autowired private MunicipalityRepo municipalityRepo;
    @Autowired private GetUserFromUserId getUserFromUserId;
    @Autowired private EventUtils eventUtils;

    @Override
    @Transactional
    @Tool("creates a new event in the system")
    public EventDTO createEvent(@P("EventRequestDTO") EventRequestDTO requestDTO) {
        User currentUser = getUserFromUserId.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("No logged-in user found. Cannot create an event.");
        }

        Optional<PartnerOrg> partnerOrgOpt = partnerOrgRepo.findByUser(currentUser);
        Optional<Municipality> municipalityOpt = municipalityRepo.findByUser(currentUser);

        if (partnerOrgOpt.isPresent() && municipalityOpt.isPresent()) {
            throw new IllegalStateException("User is associated with both a Partner Organization and a Municipality. Ambiguous event creator.");
        }

        Event event;
        if (partnerOrgOpt.isPresent()) {
            event = eventUtils.requestDtoToEntity(requestDTO, partnerOrgOpt.get(), null);
        } else if (municipalityOpt.isPresent()) {
            event = eventUtils.requestDtoToEntity(requestDTO, null, municipalityOpt.get());
        } else {
            throw new IllegalStateException("The current user is not authorized to create events. Must be linked to a Partner Organization or a Municipality.");
        }

        Event savedEvent = eventRepo.save(event);
        return eventUtils.entityToDto(savedEvent);
    }

    @Override
    @Tool("retrieves an event by its ID")
    public EventDTO getEventById(@P("event id") Long id) {
        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return eventUtils.entityToDto(event);
    }

    @Override
    @Tool("retrieves all the events present in the system")
    public List<EventDTO> getAllEvents() {
        return eventUtils.entityListToDtoList(eventRepo.findAll());
    }

    @Override
    @Transactional
    public EventDTO updateEvent(Long id, EventRequestDTO requestDTO) {
        Event existingEvent = eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        // Update fields from the request
        existingEvent.setName(requestDTO.getName());
        existingEvent.setSponsored(requestDTO.isSponsored());
        existingEvent.setStartTime(requestDTO.getStartTime());
        existingEvent.setDurationHours(requestDTO.getDurationHours());
        existingEvent.setPrice(requestDTO.getPrice());

        Event updatedEvent = eventRepo.save(existingEvent);
        return eventUtils.entityToDto(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepo.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepo.deleteById(id);
    }
}
