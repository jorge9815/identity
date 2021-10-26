package com.identity.users.aplication.services;

import com.identity.roles.domain.value_objects.RoleID;
import com.identity.users.domain.value_objects.AppUserID;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AddRoleToUser {
    private JpaUserRepository repository;

    public AddRoleToUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public void addRole(AppUserID userID, RoleID roleID){
        repository.addRoleToUser(userID, roleID);
    }
}
