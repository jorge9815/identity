package com.identity.users.aplication.services;

import com.identity.exeptions.exceptions.WrongPassword;
import com.identity.shared.Password;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetByUserAndPassword {
    private JpaUserRepository repository;

    public GetByUserAndPassword(JpaUserRepository repository) {
        this.repository = repository;
    }

    public AppUserDto get(String user, String password) throws WrongPassword{
        AppUser returned = repository.getByUser(user);
        Password pass = returned.getPassword();
            if (pass.verify(password)) {
                return new AppUserDto(returned);
            }else{
                throw new WrongPassword();
            }
        }
}
