package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class SaveUser {
    private JpaUserRepository repository;

    public SaveUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public void save(AppUserDto dto){

        log.info("Processing user information");
        repository.saveUser(dto.toAppUser());
    }
}
