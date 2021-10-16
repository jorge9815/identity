package com.identity.roles.infrastructure.controllers;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.aplication.services.GetRoles;
import com.identity.shared.PaginatedList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class GetRolesControllers {
    private GetRoles service;

    public GetRolesControllers(GetRoles service) {
        this.service = service;
    }

    @GetMapping
    public PaginatedList<RoleDto> getRoles(@RequestParam int page, @RequestParam int perPage) {
        return service.get(page, perPage);
    }
}
