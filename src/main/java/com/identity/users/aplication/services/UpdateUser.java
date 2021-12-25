package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser {
    private final JpaUserRepository repository;

    public UpdateUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public void update(AppUserDto dto){
        repository.updateUser(dto.toAppUser());
    }
}
