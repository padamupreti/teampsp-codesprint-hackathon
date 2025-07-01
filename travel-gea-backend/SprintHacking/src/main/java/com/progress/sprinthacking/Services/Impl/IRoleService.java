package com.progress.sprinthacking.Services.Impl;

import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.DTO.RoleDTO;
import com.progress.sprinthacking.Entity.Role;

public interface IRoleService {
    Role createRole(RoleDTO roleDTO);
}
