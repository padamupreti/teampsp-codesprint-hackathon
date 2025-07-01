package com.progress.sprinthacking.Services.Impl;

import com.progress.sprinthacking.DTO.Municipality.MunicipalityDTO;
import com.progress.sprinthacking.DTO.Municipality.MunicipalityRequestDTO;

import java.util.List;

public interface IMunicipalityService {
    MunicipalityDTO createMunicipality(MunicipalityRequestDTO requestDTO);

    MunicipalityDTO getMunicipalityById(Long id);

    List<MunicipalityDTO> getAllMunicipalities();

    MunicipalityDTO updateMunicipality(Long id, MunicipalityRequestDTO requestDTO);

    void deleteMunicipality(Long id);
}
