package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUsersDto;
import com.identity.users.domain.value_objects.AppUserID;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserByID {
    private JpaUserRepository repository;

    public GetUserByID(JpaUserRepository repository) {
        this.repository = repository;
    }

    public AppUsersDto get(String id){
        return new AppUsersDto(repository.getById(new AppUserID(id)));
    }
}
