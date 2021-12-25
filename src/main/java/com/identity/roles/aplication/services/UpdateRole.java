package com.identity.roles.aplication.services;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.infrastructure.JpaRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateRole {
    private final JpaRoleRepository repository;

    public UpdateRole(JpaRoleRepository repository) {
        this.repository = repository;
    }

    public void update(RoleDto dto){
        repository.updateRoles(dto.toRole());
    }
}
