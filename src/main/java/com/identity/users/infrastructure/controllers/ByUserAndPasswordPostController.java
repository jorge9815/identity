package com.identity.users.infrastructure.controllers;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.aplication.services.GetByUserAndPassword;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class ByUserAndPasswordPostController {
    private GetByUserAndPassword service;

    public ByUserAndPasswordPostController(GetByUserAndPassword service) {
        this.service = service;
    }

    @PostMapping("auth")
    public AppUserDto getByPassAndUser(@RequestParam String user, @RequestParam String pass){
        return service.get(user, pass);
    }
}
