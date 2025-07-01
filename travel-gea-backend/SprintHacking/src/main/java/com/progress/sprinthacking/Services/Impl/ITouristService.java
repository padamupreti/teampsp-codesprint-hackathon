package com.progress.sprinthacking.Services.Impl;

import com.progress.sprinthacking.DTO.tourist.TouristDTO;
import com.progress.sprinthacking.DTO.tourist.TouristRequestDTO;

import java.util.List;

public interface ITouristService {
    TouristDTO createTourist(TouristRequestDTO touristRequestDTO);

    TouristDTO updateTourist(Long id, TouristDTO touristDTO);

    void deleteTourist(Long id);

    TouristDTO getTouristById(Long id);

    List<TouristDTO> getAllTourists();
}
