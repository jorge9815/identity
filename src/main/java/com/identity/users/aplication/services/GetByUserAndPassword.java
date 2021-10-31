package com.identity.users.aplication.services;

import com.identity.exeptions.exceptions.WrongPassword;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.infrastructure.JpaUserRepository;
import com.identity.utils.JsonWebToken;
import com.identity.utils.PrivateKeyReader;
import org.springframework.stereotype.Service;

@Service
public class GetByUserAndPassword {
    private final JpaUserRepository repository;

    public GetByUserAndPassword(JpaUserRepository repository) {
        this.repository = repository;
    }

    public String get(String user, String password) throws Exception {
        AppUser returned = repository.getByUser(user);
            if (returned.getPassword().verify(password)) {
                return JsonWebToken
                        .generateJwtToken(PrivateKeyReader
                                .get("/media/jorge/Trabajo/DesarrolloWork/DevelomentJava/identity/private.der"),
                                new AppUserDto(returned));
            }else{
                throw new WrongPassword();
            }
        }
}
