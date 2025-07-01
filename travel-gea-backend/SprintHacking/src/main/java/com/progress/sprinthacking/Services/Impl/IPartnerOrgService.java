package com.progress.sprinthacking.Services.Impl;

import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgDTO;
import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgRequestDTO;

import java.util.List;

public interface IPartnerOrgService {
    PartnerOrgDTO createPartnerOrg(PartnerOrgRequestDTO requestDTO);

    PartnerOrgDTO updatePartnerOrg(Long id, PartnerOrgDTO partnerOrgDTO);

    void deletePartnerOrg(Long id);

    PartnerOrgDTO getPartnerOrgById(Long id);

    List<PartnerOrgDTO> getAllPartnerOrgs();
}
