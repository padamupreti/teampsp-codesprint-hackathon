package com.progress.sprinthacking.Services.Impl;


import com.progress.sprinthacking.DTO.GuideDTO;
import com.progress.sprinthacking.Entity.User;

import java.util.List;

public interface IGuideService {
    GuideDTO createGuide(GuideDTO guideDTO, User user);
    GuideDTO updateGuide(GuideDTO guideDTO, User user);
    boolean deleteGuide(Long id);
    GuideDTO getGuideById(Long id);
    List<GuideDTO> getAllGuides();
}
