package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUsersDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveUser {
    private JpaUserRepository repository;

    public SaveUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public void save(AppUsersDto dto){
        repository.saveUser(dto.toAppUser());
    }
}
