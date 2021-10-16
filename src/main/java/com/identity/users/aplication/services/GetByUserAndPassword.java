package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetByUserAndPassword {
    private JpaUserRepository repository;

    public GetByUserAndPassword(JpaUserRepository repository) {
        this.repository = repository;
    }

    public AppUserDto get(String user, String password){
        return new AppUserDto(repository.getByUserAndPassword(user, password));
    }
}
