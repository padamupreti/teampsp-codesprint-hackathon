package com.progress.sprinthacking.Utils.Event;

import com.progress.sprinthacking.DTO.Event.EventDTO;
import com.progress.sprinthacking.DTO.Event.EventRequestDTO;
import com.progress.sprinthacking.Entity.Event;
import com.progress.sprinthacking.Entity.Municipality;
import com.progress.sprinthacking.Entity.PartnerOrg;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventUtils {
    public EventDTO entityToDto(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setSponsored(event.isSponsored());
        dto.setStartTime(event.getStartTime());
        dto.setDurationHours(event.getDurationHours());
        dto.setPrice(event.getPrice());

        if (event.getPartnerOrg() != null) {
            dto.setPartnerOrgName(event.getPartnerOrg().getName());
            dto.setPartnerOrgId(event.getPartnerOrg().getId().intValue());
        }

        if (event.getMunicipality() != null) {
            dto.setMunicipalityName(event.getMunicipality().getName());
            dto.setMunicipalityId(event.getMunicipality().getId().intValue());
        }

        return dto;
    }

    public Event requestDtoToEntity(EventRequestDTO requestDTO, PartnerOrg partnerOrg, Municipality municipality) {
        Event event = new Event();
        event.setName(requestDTO.getName());
        event.setSponsored(requestDTO.isSponsored());
        event.setStartTime(requestDTO.getStartTime());
        event.setDurationHours(requestDTO.getDurationHours());
        event.setPrice(requestDTO.getPrice());

        // Set either the partner org or the municipality, but not both
        event.setPartnerOrg(partnerOrg);
        event.setMunicipality(municipality);

        return event;
    }

    public List<EventDTO> entityListToDtoList(List<Event> events) {
        return events.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
