package com.identity.users.infrastructure.controllers;

import com.identity.users.aplication.AppUserDto;
import com.identity.users.aplication.services.GetByUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class GetByUserController {
    private GetByUser getByUser;

    public GetByUserController(GetByUser getByUser) {
        this.getByUser = getByUser;
    }

    @GetMapping("{id}")
    public AppUserDto get(@PathVariable("id") String id){
        return getByUser.get(id);
    }
}
