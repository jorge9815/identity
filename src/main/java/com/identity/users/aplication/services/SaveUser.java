package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveUser {
    private JpaUserRepository repository;

    public SaveUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public void save(AppUserDto dto){
        repository.saveUser(dto.toAppUser());
    }
}
