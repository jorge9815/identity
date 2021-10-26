package com.identity.roles.aplication.services;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.roles.infrastructure.JpaRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class GetByID {
    private JpaRoleRepository repository;

    public GetByID(JpaRoleRepository repository) {
        this.repository = repository;
    }

    public RoleDto get(String id){
        return new RoleDto(repository.getById(new RoleID(id)));
    }
}