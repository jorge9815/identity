package com.identity.roles.domain.entity;

import com.identity.roles.domain.value_objects.RoleID;

public class Role {
    private RoleID id;
    private String name;

    public Role(RoleID id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleID getId() {
        return id;
    }

    public void setId(RoleID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
