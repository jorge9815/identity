package com.identity.users.infrastructure.controllers;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.aplication.services.UpdateUser;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UpdateUserController {
    private UpdateUser service;

    public UpdateUserController(UpdateUser service) {
        this.service = service;
    }

    @PutMapping
    public void update(@RequestBody AppUserDto userDto){
        service.update(userDto);
    }
}
