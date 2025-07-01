package com.progress.sprinthacking.Controller.Guide;

import com.progress.sprinthacking.DTO.GuideDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.DTO.guide.GuideRequestDTO;
import com.progress.sprinthacking.Services.Impl.IGuideService;
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

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createGuide(@RequestBody GuideRequestDTO guideRequestDTO) {
        // Delegate the entire complex operation to the service
        GuideDTO createdGuide = guideService.createGuide(guideRequestDTO);
        Map<String, Object> response = Map.of("guide", createdGuide);
        return new ResponseEntity<>(ResponseDTO.success("Guide created successfully", response), HttpStatus.CREATED);
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
        return new ResponseEntity<>(ResponseDTO.success("Guide retrieved successfully", response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateGuide(@PathVariable Long id, @RequestBody GuideDTO guideDTO) {
        GuideDTO updatedGuide = guideService.updateGuide(id, guideDTO);
        Map<String, Object> response = Map.of("guide", updatedGuide);
        return new ResponseEntity<>(ResponseDTO.success("Guide updated successfully", response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteGuide(@PathVariable Long id) {
        guideService.deleteGuide(id);
        return new ResponseEntity<>(ResponseDTO.success("Guide deleted successfully"), HttpStatus.OK);
    }
}
