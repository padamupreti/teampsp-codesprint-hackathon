package com.progress.sprinthacking.Controller.RoleController;

import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.DTO.RoleDTO;
import com.progress.sprinthacking.Entity.Role;
import com.progress.sprinthacking.Services.Impl.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/createRole")
    public ResponseEntity<ResponseDTO> createRole(@RequestBody RoleDTO roleDTO) {
        try {
            Role role = roleService.createRole(roleDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("createdRole",role);
            return ResponseEntity.ok(ResponseDTO.success("Roles Created Sucesfully", response));
        }catch (DataAccessException e){
            return ResponseEntity.status(500).body(ResponseDTO.error("Database error: " + e.getMessage()));
        }
    }
}
