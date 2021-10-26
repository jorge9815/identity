package com.identity.users.aplication.services;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.infrastructure.JpaUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class SaveUser {
    private JpaUserRepository repository;
    private PasswordEncoder passwordEncoder;

    public SaveUser(JpaUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(AppUserDto dto){
        log.info("Encoding user password");
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        log.info("Processing user information");
        repository.saveUser(dto.toAppUser());
    }
}
