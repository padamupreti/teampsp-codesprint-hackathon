package com.progress.sprinthacking.Controller.Municipality;

import com.progress.sprinthacking.DTO.Municipality.MunicipalityDTO;
import com.progress.sprinthacking.DTO.Municipality.MunicipalityRequestDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.Services.Impl.IMunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/municipality")
public class MunicipalityController {
    @Autowired
    private IMunicipalityService municipalityService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createMunicipality(@RequestBody MunicipalityRequestDTO requestDTO) {
        MunicipalityDTO createdMunicipality = municipalityService.createMunicipality(requestDTO);
        Map<String, Object> response = Map.of("municipality", createdMunicipality);
        return new ResponseEntity<>(ResponseDTO.success("Municipality created successfully", response), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllMunicipalities() {
        List<MunicipalityDTO> municipalities = municipalityService.getAllMunicipalities();
        Map<String, Object> response = Map.of("municipalities", municipalities);
        return new ResponseEntity<>(ResponseDTO.success("Municipalities retrieved successfully", response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getMunicipalityById(@PathVariable Long id) {
        MunicipalityDTO municipality = municipalityService.getMunicipalityById(id);
        Map<String, Object> response = Map.of("municipality", municipality);
        return new ResponseEntity<>(ResponseDTO.success("Municipality retrieved successfully", response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateMunicipality(@PathVariable Long id, @RequestBody MunicipalityRequestDTO requestDTO) {
        MunicipalityDTO updatedMunicipality = municipalityService.updateMunicipality(id, requestDTO);
        Map<String, Object> response = Map.of("municipality", updatedMunicipality);
        return new ResponseEntity<>(ResponseDTO.success("Municipality updated successfully", response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> deleteMunicipality(@PathVariable Long id) {
        municipalityService.deleteMunicipality(id);
        return new ResponseEntity<>(ResponseDTO.success("Municipality deleted successfully"), HttpStatus.OK);
    }
}
