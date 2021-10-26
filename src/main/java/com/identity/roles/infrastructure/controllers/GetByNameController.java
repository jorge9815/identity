package com.identity.roles.infrastructure.controllers;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.aplication.services.GetByName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class GetByNameController {
    private GetByName service;

    public GetByNameController(GetByName service) {
        this.service = service;
    }

    @GetMapping
    public RoleDto get(@RequestParam String name){
        return service.get(name);
    }
}
