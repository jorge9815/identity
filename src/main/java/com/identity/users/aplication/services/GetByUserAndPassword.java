package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GetByUserAndPassword {
    private JpaUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public GetByUserAndPassword(JpaUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUserDto get(String user, String password){
        return new AppUserDto(repository.getByUserAndPassword(user, passwordEncoder.encode(password)));
    }
}
