package com.identity.roles.infrastructure.controllers;

import com.identity.roles.aplication.services.DeleteRole;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class DeleteRoleController {
    private DeleteRole service;

    public DeleteRoleController(DeleteRole service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }
}
