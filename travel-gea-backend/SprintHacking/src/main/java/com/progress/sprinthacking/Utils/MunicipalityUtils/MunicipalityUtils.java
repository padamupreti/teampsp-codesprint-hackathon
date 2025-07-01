package com.progress.sprinthacking.Utils.MunicipalityUtils;

import com.progress.sprinthacking.DTO.Municipality.MunicipalityDTO;
import com.progress.sprinthacking.DTO.Municipality.MunicipalityRequestDTO;
import com.progress.sprinthacking.Entity.Municipality;
import com.progress.sprinthacking.Entity.User;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MunicipalityUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MunicipalityDTO entityToDto(Municipality municipality) {
        MunicipalityDTO dto = new MunicipalityDTO();
        dto.setId(municipality.getId());
        dto.setName(municipality.getName());
        dto.setDescription(municipality.getDescription());
        dto.setPhoto(municipality.getPhoto());
        dto.setContact(municipality.getContact());
        if (municipality.getUser() != null) {
            dto.setUserName(municipality.getUser().getUserName());
        }
        if (municipality.getInsertUser() != null) {
            dto.setInsertUserName(municipality.getInsertUser().getUserName());
        }
        if (municipality.getInsertDate() != null) {
            dto.setInsertDate(municipality.getInsertDate().format(DATE_TIME_FORMATTER));
        }
        return dto;
    }

    public Municipality requestDtoToEntity(MunicipalityRequestDTO requestDTO, User municipalityUser, User insertUser) {
        Municipality municipality = new Municipality();
        municipality.setName(requestDTO.getName());
        municipality.setDescription(requestDTO.getDescription());
        municipality.setPhoto(requestDTO.getPhoto());
        municipality.setContact(requestDTO.getContact());
        municipality.setUser(municipalityUser);
        municipality.setInsertUser(insertUser);
        municipality.setInsertDate(ZonedDateTime.now()); // Set current time on creation
        return municipality;
    }

    public List<MunicipalityDTO> entityListToDtoList(List<Municipality> municipalities) {
        return municipalities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
