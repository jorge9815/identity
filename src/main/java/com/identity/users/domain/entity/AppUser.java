package com.identity.users.domain.entity;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.domain.entity.Role;
import com.identity.users.domain.value_objects.AppUserID;

import java.util.List;

public class AppUser {
    private AppUserID id;
    private String name;
    private String user;
    private String password;
    private List<Role> rolesList;

    public AppUser(AppUserID id,String name, String user, String password, List<Role> rolesList) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.rolesList = rolesList;
    }

    public AppUser(AppUserID id, String name, String user, String password) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
    }

    public AppUser() {}

    public AppUserID getId() {
        return id;
    }

    public void setId(AppUserID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }
}
