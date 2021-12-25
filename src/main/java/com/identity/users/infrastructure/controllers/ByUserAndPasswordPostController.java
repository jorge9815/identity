package com.identity.users.infrastructure.controllers;

import com.identity.annotations.Anonymous;
import com.identity.users.aplication.services.GetByUserAndPassword;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class ByUserAndPasswordPostController {
    private final GetByUserAndPassword service;

    public ByUserAndPasswordPostController(GetByUserAndPassword service) {
        this.service = service;
    }

    @PostMapping("auth")
    @Anonymous
    public String getByPassAndUser(@RequestParam String user, @RequestParam String pass) throws Exception {
        return service.get(user, pass);
    }
}
