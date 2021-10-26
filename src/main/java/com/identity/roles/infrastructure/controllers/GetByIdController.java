package com.identity.roles.infrastructure.controllers;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.aplication.services.GetByID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class GetByIdController {
    private GetByID service;

    public GetByIdController(GetByID service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public RoleDto get(@PathVariable("id") String id){
        return service.get(id);
    }
}
