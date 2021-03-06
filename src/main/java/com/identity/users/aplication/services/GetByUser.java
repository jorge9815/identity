package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetByUser {
    private final JpaUserRepository repository;

    public GetByUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public AppUserDto get(String user){
        return new AppUserDto(repository.getByUser(user));
    }
}
