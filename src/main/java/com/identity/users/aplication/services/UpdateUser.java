package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUsersDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser {
    private JpaUserRepository repository;

    public UpdateUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public void update(AppUsersDto dto){
        repository.updateUser(dto.toAppUser());
    }
}
