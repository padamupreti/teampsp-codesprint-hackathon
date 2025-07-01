package com.progress.sprinthacking.Utils.PartnerOrgs;

import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgDTO;
import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgRequestDTO;
import com.progress.sprinthacking.Entity.PartnerOrg;
import com.progress.sprinthacking.Entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PartnerOrgUtils {
    public PartnerOrgDTO entityToDto(PartnerOrg partnerOrg) {
        PartnerOrgDTO dto = new PartnerOrgDTO();
        dto.setId(partnerOrg.getId());
        dto.setName(partnerOrg.getName());
        dto.setDescription(partnerOrg.getDescription());
        dto.setPhoto(partnerOrg.getPhoto());
        dto.setContact(partnerOrg.getContact());
        if (partnerOrg.getUser() != null) {
            dto.setUserName(partnerOrg.getUser().getUserName());
        }

        // Convert comma-separated string to List
        if (partnerOrg.getTags() != null && !partnerOrg.getTags().isEmpty()) {
            dto.setTags(Arrays.asList(partnerOrg.getTags().split(",")));
        } else {
            dto.setTags(Collections.emptyList());
        }

        return dto;
    }

    public PartnerOrg requestDtoToEntity(PartnerOrgRequestDTO requestDTO, User user) {
        PartnerOrg partnerOrg = new PartnerOrg();
        partnerOrg.setName(requestDTO.getName());
        partnerOrg.setDescription(requestDTO.getDescription());
        partnerOrg.setPhoto(requestDTO.getPhoto());
        partnerOrg.setContact(requestDTO.getContact());
        partnerOrg.setUser(user);

        // Convert List to comma-separated string
        if (requestDTO.getTags() != null && !requestDTO.getTags().isEmpty()) {
            partnerOrg.setTags(String.join(",", requestDTO.getTags()));
        }

        return partnerOrg;
    }

    public List<PartnerOrgDTO> entityListToDtoList(List<PartnerOrg> partnerOrgs) {
        return partnerOrgs.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
