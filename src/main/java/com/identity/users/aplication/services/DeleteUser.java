package com.identity.users.aplication.services;

import com.identity.users.domain.value_objects.AppUserID;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUser {
    private JpaUserRepository repository;

    public DeleteUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public void delete(String id){
        repository.deleteUser(new AppUserID(id));
    }
}
