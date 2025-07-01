package com.progress.sprinthacking.Services.Municipality;

import com.progress.sprinthacking.DTO.Municipality.MunicipalityDTO;
import com.progress.sprinthacking.DTO.Municipality.MunicipalityRequestDTO;
import com.progress.sprinthacking.DTO.UserDTO;
import com.progress.sprinthacking.Entity.Municipality;
import com.progress.sprinthacking.Entity.Role;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.MunicipalityRepo;
import com.progress.sprinthacking.Repo.RoleRepo;
import com.progress.sprinthacking.Repo.UserRepo;
import com.progress.sprinthacking.Services.Impl.IMunicipalityService;
import com.progress.sprinthacking.Services.Users.UserService;
import com.progress.sprinthacking.Utils.GetUserFromUserId;
import com.progress.sprinthacking.Utils.MunicipalityUtils.MunicipalityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MunicipalityService implements IMunicipalityService {
    @Autowired
    private MunicipalityRepo municipalityRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private MunicipalityUtils municipalityUtils;
    @Autowired
    private GetUserFromUserId getUserFromUserId;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public MunicipalityDTO createMunicipality(MunicipalityRequestDTO requestDTO) {
        // 1. Find or create the MUNICIPALITY role
        Role municipalityRole = roleRepo.findByRoleAlias("MUNICIPALITY");
        if (municipalityRole == null) {
            municipalityRole = new Role();
            municipalityRole.setRoleAlias("MUNICIPALITY");
            municipalityRole.setRoleName("MUNICIPALITY");
            municipalityRole.setRemarks("For municipality users");
            municipalityRole = roleRepo.save(municipalityRole);
        }
        // 2. Create and save the User entity for the municipality
        UserDTO newUser = new UserDTO();
        newUser.setUserName(requestDTO.getUserName());
        newUser.setEmail(requestDTO.getEmail());
        newUser.setPassword(requestDTO.getPassword());
        newUser.setRoleId(municipalityRole.getId());
        User savedMunicipalityUser = userService.createUser(newUser);

        // 3. Get the user who is performing the insertion
        User insertUser = getUserFromUserId.getCurrentUser();
        if (insertUser == null) {
            throw new IllegalStateException("Cannot create a municipality without a logged-in user to track the insertion.");
        }

        // 4. Create and save the Municipality entity
        Municipality municipality = municipalityUtils.requestDtoToEntity(requestDTO, savedMunicipalityUser, insertUser);
        Municipality savedMunicipality = municipalityRepo.save(municipality);

        return municipalityUtils.entityToDto(savedMunicipality);
    }

    @Override
    public MunicipalityDTO getMunicipalityById(Long id) {
        Municipality municipality = municipalityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Municipality id not found: "+ id));
        return municipalityUtils.entityToDto(municipality);
    }

    @Override
    public List<MunicipalityDTO> getAllMunicipalities() {
        List<Municipality> municipalities = municipalityRepo.findAll();
        return municipalityUtils.entityListToDtoList(municipalities);
    }

    @Override
    @Transactional
    public MunicipalityDTO updateMunicipality(Long id, MunicipalityRequestDTO requestDTO) {
        Municipality existingMunicipality = municipalityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Municipality id not found: "+ id));

        // Update the municipality's own details
        existingMunicipality.setName(requestDTO.getName());
        existingMunicipality.setDescription(requestDTO.getDescription());
        existingMunicipality.setPhoto(requestDTO.getPhoto());
        existingMunicipality.setContact(requestDTO.getContact());

        Municipality updatedMunicipality = municipalityRepo.save(existingMunicipality);
        return municipalityUtils.entityToDto(updatedMunicipality);
    }

    @Override
    @Transactional
    public void deleteMunicipality(Long id) {
        Municipality municipality = municipalityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Municipality id not found: "+ id));

        // First delete the municipality, then the associated user
        municipalityRepo.delete(municipality);
        userRepo.delete(municipality.getUser());
    }
}
