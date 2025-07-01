package com.progress.sprinthacking.Utils.Guide;

import com.progress.sprinthacking.DTO.GuideDTO;
import com.progress.sprinthacking.Entity.Guide;
import com.progress.sprinthacking.Entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GuideUtils {
    public GuideDTO entityToDto(Guide guide) {
        GuideDTO dto = new GuideDTO();
        dto.setId(guide.getId());
        dto.setGuideName(guide.getGuideName());
        dto.setGuideDescription(guide.getGuideDescription());
        dto.setGuideImageUrl(guide.getGuideImageUrl());
        dto.setGuidePhone(guide.getGuidePhone());
        dto.setUserName(guide.getUser().getUserName());

        // Convert the comma-separated string back to a List
        if (guide.getSpecialities() != null && !guide.getSpecialities().isEmpty()) {
            dto.setSpecialities(Arrays.asList(guide.getSpecialities().split(",")));
        }

        return dto;
    }

    public Guide dtoToEntity(GuideDTO dto, User user) {
        Guide guide = new Guide();
        guide.setGuideName(dto.getGuideName());
        guide.setGuideDescription(dto.getGuideDescription());
        guide.setGuideImageUrl(dto.getGuideImageUrl());
        guide.setGuidePhone(dto.getGuidePhone());
        guide.setUser(user);

        // Convert the List of strings to a single comma-separated string
        if (dto.getSpecialities() != null && !dto.getSpecialities().isEmpty()) {
            guide.setSpecialities(String.join(",", dto.getSpecialities()));
        }

        return guide;
    }

    public List<GuideDTO> entityListToDtoList(List<Guide> guides) {
        return guides.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
