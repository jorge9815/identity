package com.identity.roles.infrastructure.controllers;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.aplication.services.UpdateRole;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class UpdateRolePutController {
    private UpdateRole service;

    public UpdateRolePutController(UpdateRole service) {
        this.service = service;
    }

    @PutMapping("update")
    public void update(@RequestBody RoleDto role){
        service.update(role);
    }
}
