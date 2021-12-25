package com.identity.roles.infrastructure.controllers;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.aplication.services.SaveRole;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class SaveRolesPostController {
    private final SaveRole service;

    public SaveRolesPostController(SaveRole service) {
        this.service = service;
    }

    @PostMapping
    public void save(@RequestBody RoleDto role){
        service.save(role);
    }
}
