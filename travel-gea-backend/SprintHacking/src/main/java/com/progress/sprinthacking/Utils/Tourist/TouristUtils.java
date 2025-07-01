package com.progress.sprinthacking.Utils.Tourist;

import com.progress.sprinthacking.DTO.tourist.TouristDTO;
import com.progress.sprinthacking.DTO.tourist.TouristRequestDTO;
import com.progress.sprinthacking.Entity.Tourist;
import com.progress.sprinthacking.Entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TouristUtils {
    public TouristDTO entityToDto(Tourist tourist) {
        TouristDTO dto = new TouristDTO();
        dto.setId(tourist.getId());
        dto.setTouristName(tourist.getTouristName());
        dto.setTouristDescription(tourist.getTouristDescription());
        dto.setUserName(tourist.getUser().getUserName());

        // Convert the comma-separated string back to a List
        if (tourist.getInterests() != null && !tourist.getInterests().isEmpty()) {
            dto.setInterests(Arrays.asList(tourist.getInterests().split(",")));
        } else {
            dto.setInterests(Collections.emptyList());
        }

        return dto;
    }

    public Tourist requestDtoToEntity(TouristRequestDTO dto, User user) {
        Tourist tourist = new Tourist();
        tourist.setTouristName(dto.getTouristName());
        tourist.setTouristDescription(dto.getTouristDescription());
        tourist.setUser(user);

        // Convert the List of strings to a single comma-separated string
        if (dto.getInterests() != null && !dto.getInterests().isEmpty()) {
            tourist.setInterests(String.join(",", dto.getInterests()));
        }

        return tourist;
    }

    public List<TouristDTO> entityListToDtoList(List<Tourist> tourists) {
        return tourists.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
