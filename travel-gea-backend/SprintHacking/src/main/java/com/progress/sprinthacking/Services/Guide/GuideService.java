package com.progress.sprinthacking.Services.Guide;

import com.progress.sprinthacking.DTO.GuideDTO;
import com.progress.sprinthacking.Entity.Guide;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.GuideRepo;
import com.progress.sprinthacking.Repo.UserRepo;
import com.progress.sprinthacking.Services.Impl.IGuideService;
import com.progress.sprinthacking.Utils.Guide.GuideUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService implements IGuideService {
    @Autowired
    private GuideRepo guideRepo;

    @Autowired
    private GuideUtils guideUtils;
    @Autowired
    private UserRepo userRepo;

    @Override
    public GuideDTO createGuide(GuideDTO guideDTO, User user) {
        Guide guide = guideUtils.dtoToEntity(guideDTO, user);
        Guide savedGuide = guideRepo.save(guide);
        return guideUtils.entityToDto(savedGuide);
    }

    @Override
    public GuideDTO updateGuide(GuideDTO guideDTO, User user) {
        Guide existingGuide = guideRepo.findById(guideDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guideid: "+guideDTO.getId()+" not found"));

        // Update fields
        existingGuide.setGuideName(guideDTO.getGuideName());
        existingGuide.setGuideDescription(guideDTO.getGuideDescription());
        existingGuide.setGuideImageUrl(guideDTO.getGuideImageUrl());
        existingGuide.setGuidePhone(guideDTO.getGuidePhone());
        existingGuide.setUser(user);

        if (guideDTO.getSpecialities() != null && !guideDTO.getSpecialities().isEmpty()) {
            existingGuide.setSpecialities(String.join(",", guideDTO.getSpecialities()));
        } else {
            existingGuide.setSpecialities(null);
        }

        Guide updatedGuide = guideRepo.save(existingGuide);
        return guideUtils.entityToDto(updatedGuide);
    }

    @Override
    public boolean deleteGuide(Long id) {
        User user = guideRepo.findUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found for guide id: " + id);
        }
        Guide guide = guideRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guideid: "+id+" not found"));
        guideRepo.delete(guide);

        return true;
    }

    @Override
    public GuideDTO getGuideById(Long id) {
        Guide guide = guideRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guideid: "+id+" not found"));
        return guideUtils.entityToDto(guide);
    }

    @Override
    public List<GuideDTO> getAllGuides() {
        List<Guide> guides = guideRepo.findAll();
        return guideUtils.entityListToDtoList(guides);
    }
}
