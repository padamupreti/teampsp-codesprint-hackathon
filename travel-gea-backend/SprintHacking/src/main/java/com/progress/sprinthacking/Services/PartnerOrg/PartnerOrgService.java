package com.progress.sprinthacking.Services.PartnerOrg;

import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgDTO;
import com.progress.sprinthacking.DTO.PartnerOrg.PartnerOrgRequestDTO;
import com.progress.sprinthacking.DTO.UserDTO;
import com.progress.sprinthacking.Entity.PartnerOrg;
import com.progress.sprinthacking.Entity.Role;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.PartnerOrgRepo;
import com.progress.sprinthacking.Repo.RoleRepo;
import com.progress.sprinthacking.Repo.UserRepo;
import com.progress.sprinthacking.Services.Impl.IPartnerOrgService;
import com.progress.sprinthacking.Services.Users.UserService;
import com.progress.sprinthacking.Utils.PartnerOrgs.PartnerOrgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartnerOrgService implements IPartnerOrgService {
    @Autowired
    private PartnerOrgRepo partnerOrgRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PartnerOrgUtils partnerOrgUtils;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public PartnerOrgDTO createPartnerOrg(PartnerOrgRequestDTO requestDTO) {
        // Find or create the PARTNER role
        Role partnerRole = roleRepo.findByRoleAlias("PARTNER");
        if (partnerRole == null) {
            partnerRole = new Role();
            partnerRole.setRoleAlias("PARTNER");
            partnerRole.setRoleName("Partner Organization");
            partnerRole.setRemarks("Role for partner organizations");
            partnerRole = roleRepo.save(partnerRole);
        }

        // Create and save the User entity by calling the UserService
        UserDTO newUser = new UserDTO();
        newUser.setUserName(requestDTO.getUserName());
        newUser.setEmail(requestDTO.getEmail());
        newUser.setPassword(requestDTO.getPassword());
        newUser.setRoleId(partnerRole.getId());
        User savedUser = userService.createUser(newUser);

        // Create and save the PartnerOrg entity
        PartnerOrg partnerOrg = partnerOrgUtils.requestDtoToEntity(requestDTO, savedUser);
        PartnerOrg savedPartnerOrg = partnerOrgRepo.save(partnerOrg);

        return partnerOrgUtils.entityToDto(savedPartnerOrg);
    }

    @Override
    public PartnerOrgDTO updatePartnerOrg(Long id, PartnerOrgDTO partnerOrgDTO) {
        PartnerOrg existingPartnerOrg = partnerOrgRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("parner organization not found for id: "+id));

        existingPartnerOrg.setName(partnerOrgDTO.getName());
        existingPartnerOrg.setDescription(partnerOrgDTO.getDescription());
        existingPartnerOrg.setPhoto(partnerOrgDTO.getPhoto());
        existingPartnerOrg.setContact(partnerOrgDTO.getContact());

        if (partnerOrgDTO.getTags() != null && !partnerOrgDTO.getTags().isEmpty()) {
            existingPartnerOrg.setTags(String.join(",", partnerOrgDTO.getTags()));
        } else {
            existingPartnerOrg.setTags(null);
        }

        PartnerOrg updatedPartnerOrg = partnerOrgRepo.save(existingPartnerOrg);
        return partnerOrgUtils.entityToDto(updatedPartnerOrg);
    }

    @Override
    @Transactional
    public void deletePartnerOrg(Long id) {
        PartnerOrg partnerOrg = partnerOrgRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("parner organization not found for id: "+id));

        // Deleting the partner org and its associated user
        partnerOrgRepo.delete(partnerOrg);
        userRepo.delete(partnerOrg.getUser());
    }

    @Override
    public PartnerOrgDTO getPartnerOrgById(Long id) {
        PartnerOrg partnerOrg = partnerOrgRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("parner organization not found for id: "+id));
        return partnerOrgUtils.entityToDto(partnerOrg);
    }

    @Override
    public List<PartnerOrgDTO> getAllPartnerOrgs() {
        List<PartnerOrg> partnerOrgs = partnerOrgRepo.findAll();
        return partnerOrgUtils.entityListToDtoList(partnerOrgs);
    }
}
