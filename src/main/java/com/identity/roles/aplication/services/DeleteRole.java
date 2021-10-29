package com.identity.roles.aplication.services;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.roles.infrastructure.JpaRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteRole {
    private final JpaRoleRepository repository;

    public DeleteRole(JpaRoleRepository repository) {
        this.repository = repository;
    }

    public void delete(String roleId){
        repository.deleteRoles(new RoleID(roleId));
    }
}
