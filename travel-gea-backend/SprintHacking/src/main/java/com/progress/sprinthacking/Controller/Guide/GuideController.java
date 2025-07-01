package com.progress.sprinthacking.Controller.Guide;

import com.progress.sprinthacking.DTO.GuideDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.DTO.UserDTO;
import com.progress.sprinthacking.DTO.guide.GuideRequestDTO;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Repo.GuideRepo;
import com.progress.sprinthacking.Repo.RoleRepo;
import com.progress.sprinthacking.Services.Impl.IGuideService;
import com.progress.sprinthacking.Services.Impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/guide")
public class GuideController {
    @Autowired
    private IGuideService guideService;

    @Autowired
    private IUserService userService;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private GuideRepo guideRepo;

    @PostMapping("/create")
    public ResponseEntity<GuideDTO> createGuide(@RequestBody GuideRequestDTO guideDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(guideDTO.getUserName());
        userDTO.setPassword(guideDTO.getPassword());
        userDTO.setEmail(guideDTO.getEmail());
        userDTO.setRoleId(roleRepo.findByRoleAlias("GUIDE").getId());
        User user = userService.createUser(userDTO);
        GuideDTO guideDTO1 = new GuideDTO();
        guideDTO1.setUserName(user.getUserName());
        guideDTO1.setGuideDescription(guideDTO.getGuideDescription());
        guideDTO1.setGuideName(guideDTO.getGuideName());
        guideDTO1.setGuidePhone(guideDTO.getGuidePhone());
        guideDTO1.setGuideImageUrl(guideDTO.getGuideImageUrl());
        guideDTO1.setSpecialities(guideDTO.getSpecialities());
        GuideDTO createdGuide = guideService.createGuide(guideDTO1, user);
        return new ResponseEntity<>(createdGuide, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllGuides() {
        List<GuideDTO> guides = guideService.getAllGuides();
        Map<String, Object> response = Map.of("guides", guides);
        return new ResponseEntity<>(ResponseDTO.success("Guides retrieved successfully", response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getGuideById(@PathVariable Long id) {
        GuideDTO guide = guideService.getGuideById(id);
        Map<String, Object> response = Map.of("guide", guide);
        return new ResponseEntity<>(ResponseDTO.success("Guides retrieved successfully", response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateGuide(@PathVariable Long id, @RequestBody GuideDTO guideDTO) {
        guideDTO.setId(id); // Ensure the ID from the path is used
        User existingUser = guideRepo.getById(id).getUser();
        GuideDTO updatedGuide = guideService.updateGuide(guideDTO, existingUser);
        Map<String, Object> response = Map.of("guide", updatedGuide);
        return new ResponseEntity<>(ResponseDTO.success("Guides retrieved successfully", response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteGuide(@PathVariable Long id) {
        boolean deletedGuide = guideService.deleteGuide(id);
        return new ResponseEntity<>(ResponseDTO.success("Guide deleted successfully"), deletedGuide ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
