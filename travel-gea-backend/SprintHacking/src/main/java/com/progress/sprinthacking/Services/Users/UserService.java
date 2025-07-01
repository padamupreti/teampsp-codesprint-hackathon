package com.progress.sprinthacking.Services.Users;


import com.progress.sprinthacking.DTO.LoginDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.DTO.UserDTO;
import com.progress.sprinthacking.Entity.Role;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Enums.ResponseStatus;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.RoleRepo;
import com.progress.sprinthacking.Repo.UserRepo;
import com.progress.sprinthacking.Services.Impl.IUserService;
import com.progress.sprinthacking.Services.JWT.JWTService;
import com.progress.sprinthacking.Utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    AuthenticationManager authmanager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @Override
    public User createUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepo.findByUserName(userDTO.getUserName());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists with username: " + userDTO.getUserName());
        }
        Optional<Role> role = roleRepo.findById(userDTO.getRoleId());
        if(role.isEmpty()){
            throw new IllegalArgumentException("Role does not exist");
        }
        boolean emailValid = EmailValidator.isValidEmail(userDTO.getEmail());
        if (!emailValid) {
            throw new IllegalArgumentException("Invalid email format");
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPasswordHash(encoder.encode(userDTO.getPassword()));
        user.setRole(role.get());
        user.setEmail(userDTO.getEmail());
        User user1 = userRepo.save(user);
        return user1;
    }

    @Override
    public ResponseDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseDTO deleteUser(Long id) {
        return null;
    }

    @Override
    public ResponseDTO getUserById(Long id) {
        return null;
    }

    @Override
    public ResponseDTO getAllUsers() {
        List<User> allUser = userRepo.findAll();
        Map<String, Object> detail = new HashMap<>();
        detail.put("users", allUser);
        return ResponseDTO.success("Users fetched successfully", detail);
    }

    @Override
    public ResponseDTO verify(LoginDTO dto) {
        Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userRepo.findByUserName(dto.getUserName())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String token = jwtService.generateToken(dto.getUserName(), user.getId());
            Map<String, Object> details = new HashMap<>();
            details.put("token", token);
            details.put("userName", user.getUserName());
            details.put("userId", user.getId());
            details.put("role", user.getRole().getRoleAlias());
            return ResponseDTO.success("User logged in successfully",details);
        }
        else return ResponseDTO.error("Authentication failed");
    }
}
