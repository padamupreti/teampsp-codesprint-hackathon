package com.progress.sprinthacking.Services.Impl;


import com.progress.sprinthacking.DTO.GuideDTO;
import com.progress.sprinthacking.DTO.guide.GuideRequestDTO;
import com.progress.sprinthacking.Entity.User;

import java.util.List;

public interface IGuideService {
    GuideDTO createGuide(GuideRequestDTO guideRequestDTO);

    // MODIFIED: Takes the ID from the path, not the DTO
    GuideDTO updateGuide(Long id, GuideDTO guideDTO);

    // MODIFIED: Returns void for a cleaner REST response
    void deleteGuide(Long id);

    GuideDTO getGuideById(Long id);

    List<GuideDTO> getAllGuides();
}
