package com.progress.sprinthacking.Services.Guide;

import com.progress.sprinthacking.DTO.GuideDTO;
import com.progress.sprinthacking.DTO.UserDTO;
import com.progress.sprinthacking.DTO.guide.GuideRequestDTO;
import com.progress.sprinthacking.Entity.Guide;
import com.progress.sprinthacking.Entity.Role;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.GuideRepo;
import com.progress.sprinthacking.Repo.RoleRepo;
import com.progress.sprinthacking.Repo.UserRepo;
import com.progress.sprinthacking.Services.Impl.IGuideService;
import com.progress.sprinthacking.Services.Impl.IUserService;
import com.progress.sprinthacking.Utils.Guide.GuideUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuideService implements IGuideService {
    @Autowired
    private GuideRepo guideRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private IUserService userService; // Use the interface
    @Autowired
    private GuideUtils guideUtils;

    @Override
    @Transactional // Ensures the entire method succeeds or fails together
    public GuideDTO createGuide(GuideRequestDTO guideRequestDTO) {
        // 1. Find or create the GUIDE role
        Role guideRole = roleRepo.findByRoleAlias("GUIDE");
        if (guideRole == null)
                {
                    Role newRole = new Role();
                    newRole.setRoleName("GUIDE");
                    newRole.setRoleAlias("GUIDE");
                    newRole.setRemarks("For guide users");
                    guideRole = roleRepo.save(newRole);
                }

        // 2. Create the User by calling the UserService
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(guideRequestDTO.getUserName());
        userDTO.setPassword(guideRequestDTO.getPassword());
        userDTO.setEmail(guideRequestDTO.getEmail());
        userDTO.setRoleId(guideRole.getId());
        User savedUser = userService.createUser(userDTO);

        // 3. Create a temporary GuideDTO for conversion
        GuideDTO guideDataForEntity = new GuideDTO();
        guideDataForEntity.setGuideName(guideRequestDTO.getGuideName());
        guideDataForEntity.setGuideDescription(guideRequestDTO.getGuideDescription());
        guideDataForEntity.setGuideImageUrl(guideRequestDTO.getGuideImageUrl());
        guideDataForEntity.setGuidePhone(guideRequestDTO.getGuidePhone());
        guideDataForEntity.setSpecialities(guideRequestDTO.getSpecialities());

        // 4. Convert to entity and save the Guide
        Guide guide = guideUtils.dtoToEntity(guideDataForEntity, savedUser);
        Guide savedGuide = guideRepo.save(guide);

        return guideUtils.entityToDto(savedGuide);
    }

    @Override
    @Transactional
    public GuideDTO updateGuide(Long id, GuideDTO guideDTO) {
        Guide existingGuide = guideRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guide not found with id: " + id));

        // Update fields
        existingGuide.setGuideName(guideDTO.getGuideName());
        existingGuide.setGuideDescription(guideDTO.getGuideDescription());
        existingGuide.setGuideImageUrl(guideDTO.getGuideImageUrl());
        existingGuide.setGuidePhone(guideDTO.getGuidePhone());

        if (guideDTO.getSpecialities() != null && !guideDTO.getSpecialities().isEmpty()) {
            existingGuide.setSpecialities(String.join(",", guideDTO.getSpecialities()));
        } else {
            existingGuide.setSpecialities(null);
        }

        Guide updatedGuide = guideRepo.save(existingGuide);
        return guideUtils.entityToDto(updatedGuide);
    }

    @Override
    @Transactional
    public void deleteGuide(Long id) {
        Guide guide = guideRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guide not found with id: " + id));

        // It's good practice to delete the guide first, then the associated user
        guideRepo.delete(guide);
        userRepo.delete(guide.getUser());
    }

    @Override
    public GuideDTO getGuideById(Long id) {
        Guide guide = guideRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guide not found with id: " + id));
        return guideUtils.entityToDto(guide);
    }

    @Override
    public List<GuideDTO> getAllGuides() {
        List<Guide> guides = guideRepo.findAll();
        return guideUtils.entityListToDtoList(guides);
    }
}
