package com.identity.roles.aplication;

import com.identity.roles.domain.entity.Role;
import com.identity.roles.domain.value_objects.RoleID;

public class RoleDto {
    private String id;
    private String name;

    public RoleDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDto(Role role) {
        this.id = role.getId().getValue();
        this.name = role.getName();
    }

    public RoleDto() {
    }

    public Role toRole(){
        return new Role(new RoleID(this.id), this.name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
