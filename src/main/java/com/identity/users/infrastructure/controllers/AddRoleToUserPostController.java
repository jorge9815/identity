package com.identity.users.infrastructure.controllers;

import com.identity.roles.domain.value_objects.RoleID;
import com.identity.users.aplication.services.AddRoleToUser;
import com.identity.users.domain.value_objects.AppUserID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class AddRoleToUserPostController {
    private AddRoleToUser service;

    public AddRoleToUserPostController(AddRoleToUser service) {
        this.service = service;
    }

    @PostMapping("add-role")
    public void addRole(@RequestParam String userId, @RequestParam String roleId){
        service.addRole(new AppUserID(userId), new RoleID(roleId));
    }

}