package com.identity.integration;

import com.identity.TestData;
import com.identity.users.infrastructure.JpaUserRepository;
import com.identity.utils.JsonWebToken;
import org.springframework.beans.factory.annotation.Autowired;


public class Data {
    @Autowired
    private JpaUserRepository repo;

    public void createFirstUser() {
        repo.saveUser(TestData.getUser());
    }

    public String generateToken() throws Exception {
        return JsonWebToken
                .generateJwtToken(TestData.getNewUserDto());
    }


}
