package com.progress.sprinthacking.Services.Tourist;

import com.progress.sprinthacking.DTO.UserDTO;
import com.progress.sprinthacking.DTO.tourist.TouristDTO;
import com.progress.sprinthacking.DTO.tourist.TouristRequestDTO;
import com.progress.sprinthacking.Entity.Role;
import com.progress.sprinthacking.Entity.Tourist;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.RoleRepo;
import com.progress.sprinthacking.Repo.TouristRepo;
import com.progress.sprinthacking.Repo.UserRepo;
import com.progress.sprinthacking.Services.Impl.ITouristService;
import com.progress.sprinthacking.Services.Users.UserService;
import com.progress.sprinthacking.Utils.Tourist.TouristUtils;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TouristService implements ITouristService {
    @Autowired
    private TouristRepo touristRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private TouristUtils touristUtils;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public TouristDTO createTourist(TouristRequestDTO touristRequestDTO) {
        // Find or create the TOURIST role
        Role touristRole = roleRepo.findByRoleAlias("TOURIST");
        if (touristRole == null) {
            touristRole = new Role();
            touristRole.setRoleAlias("TOURIST");
            touristRole.setRoleName("Tourist");
            touristRole.setRemarks("Role for tourists");
            touristRole = roleRepo.save(touristRole);
        }
        // Create and save the User entity
        UserDTO newUser = new UserDTO();
        newUser.setUserName(touristRequestDTO.getUserName());
        newUser.setEmail(touristRequestDTO.getEmail());
        newUser.setPassword(touristRequestDTO.getPassword());
        newUser.setRoleId(touristRole.getId());
        User savedUser = userService.createUser(newUser);

        // Create and save the Tourist entity
        Tourist tourist = touristUtils.requestDtoToEntity(touristRequestDTO, savedUser);
        Tourist savedTourist = touristRepo.save(tourist);

        return touristUtils.entityToDto(savedTourist);
    }

    @Override
    public TouristDTO updateTourist(Long id, TouristDTO touristDTO) {
        Tourist existingTourist = touristRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tourist not found with id: " + id));

        existingTourist.setTouristName(touristDTO.getTouristName());
        existingTourist.setTouristDescription(touristDTO.getTouristDescription());

        if (touristDTO.getInterests() != null && !touristDTO.getInterests().isEmpty()) {
            existingTourist.setInterests(String.join(",", touristDTO.getInterests()));
        } else {
            existingTourist.setInterests(null);
        }

        Tourist updatedTourist = touristRepo.save(existingTourist);
        return touristUtils.entityToDto(updatedTourist);
    }

    @Override
    @Transactional
    public void deleteTourist(Long id) {
        Tourist tourist = touristRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tourist not found with id: " + id));

        // Deleting the tourist and the associated user
        touristRepo.delete(tourist);
        userRepo.delete(tourist.getUser());
    }

    @Override
    @Tool("Get a tourist by their ID")
    public TouristDTO getTouristById(@P("tourist id") Long id) {
        Tourist tourist = touristRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tourist not found with id: " + id));
        return touristUtils.entityToDto(tourist);
    }

    @Override
    @Tool("Get all the tourists registered in the system")
    public List<TouristDTO> getAllTourists() {
        List<Tourist> tourists = touristRepo.findAll();
        return touristUtils.entityListToDtoList(tourists);
    }
}
