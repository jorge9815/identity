package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetByUser {
    private JpaUserRepository repository;

    public GetByUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    public AppUserDto get(String user){
        var returned = repository.getByUser(user);
        if (!returned.isEmpty()) {
            return new AppUserDto(returned.get());
        }else{
            return null;
        }
    }
}
