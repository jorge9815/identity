package com.identity.roles.aplication.services;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.infrastructure.JpaRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveRole {
    private final JpaRoleRepository repository;

    public SaveRole(JpaRoleRepository repository) {
        this.repository = repository;
    }

    public void save(RoleDto dto){
        repository.saveRole(dto.toRole());
    }
}
