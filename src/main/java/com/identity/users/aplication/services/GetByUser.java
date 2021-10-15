package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUsersDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetByUser {
    private JpaUserRepository repository;

    public GetByUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public AppUsersDto get(String user){
        return new AppUsersDto(repository.getByUser(user));
    }
}
