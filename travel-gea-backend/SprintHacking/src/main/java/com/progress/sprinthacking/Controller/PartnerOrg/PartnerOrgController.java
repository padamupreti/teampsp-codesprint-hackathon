package com.progress.sprinthacking.Controller.PartnerOrg;

import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgDTO;
import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgRequestDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.Services.Impl.IPartnerOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partner")
public class PartnerOrgController {
    @Autowired
    private IPartnerOrgService partnerOrgService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createPartnerOrg(@RequestBody PartnerOrgRequestDTO requestDTO) {
        PartnerOrgDTO createdPartnerOrg = partnerOrgService.createPartnerOrg(requestDTO);
        Map<String, Object> response = Map.of("partnerOrg", createdPartnerOrg);
        return new ResponseEntity<>(ResponseDTO.success("Partner Organization created successfully", response), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllPartnerOrgs() {
        List<PartnerOrgDTO> partnerOrgs = partnerOrgService.getAllPartnerOrgs();
        Map<String, Object> response = Map.of("partnerOrgs", partnerOrgs);
        return new ResponseEntity<>(ResponseDTO.success("Partner Organizations retrieved successfully", response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPartnerOrgById(@PathVariable Long id) {
        PartnerOrgDTO partnerOrg = partnerOrgService.getPartnerOrgById(id);
        Map<String, Object> response = Map.of("partnerOrg", partnerOrg);
        return new ResponseEntity<>(ResponseDTO.success("Partner Organization retrieved successfully", response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePartnerOrg(@PathVariable Long id, @RequestBody PartnerOrgDTO partnerOrgDTO) {
        PartnerOrgDTO updatedPartnerOrg = partnerOrgService.updatePartnerOrg(id, partnerOrgDTO);
        Map<String, Object> response = Map.of("partnerOrg", updatedPartnerOrg);
        return new ResponseEntity<>(ResponseDTO.success("Partner Organization updated successfully", response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePartnerOrg(@PathVariable Long id) {
        partnerOrgService.deletePartnerOrg(id);
        return new ResponseEntity<>(ResponseDTO.success("Partner Organization deleted successfully"), HttpStatus.OK);
    }
}
