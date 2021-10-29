package com.identity.roles.aplication.services;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.infrastructure.JpaRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class GetByName {
    private final JpaRoleRepository repository;

    public GetByName(JpaRoleRepository repository) {
        this.repository = repository;
    }

    public RoleDto get(String name){
        return new RoleDto(repository.getByName(name));
    }
}
