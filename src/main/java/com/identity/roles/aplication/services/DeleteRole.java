package com.identity.roles.aplication.services;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.roles.infrastructure.JpaRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteRole {
    private JpaRoleRepository repository;

    public DeleteRole(JpaRoleRepository repository) {
        this.repository = repository;
    }

    public void delete(RoleDto dto){
        repository.deleteRoles(new RoleID(dto.getId()));
    }
}
