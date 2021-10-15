package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUsersDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetByUserAndPassword {
    private JpaUserRepository repository;

    public GetByUserAndPassword(JpaUserRepository repository) {
        this.repository = repository;
    }

    public AppUsersDto get(String user, String password){
        return new AppUsersDto(repository.getByUserAndPassword(user, password));
    }
}
