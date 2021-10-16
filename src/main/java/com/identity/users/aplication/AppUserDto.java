package com.identity.users.aplication;

import com.identity.roles.aplication.RoleDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.value_objects.AppUserID;

import java.util.List;
import java.util.stream.Collectors;

public class AppUserDto {
    private String id;
    private String name;
    private String user;
    private String password;
    private List<RoleDto> rolesList;

    public AppUserDto(String id, String name, String user, String password, List<RoleDto> rolesList) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.rolesList = rolesList;
    }

    public AppUserDto(AppUser user) {
        this.id = user.getId().getValue();
        this.name = user.getName();
        this.user = user.getUser();
        this.password = user.getPassword();
        this.rolesList = user.getRolesList()
                .stream().map(RoleDto::new)
                .collect(Collectors.toList());
    }

    public AppUserDto() {
    }

    public AppUser toAppUser() {
        return new AppUser(
                new AppUserID(this.id),
                this.name,
                this.user,
                this.password,
                this.rolesList
                        .stream().map(RoleDto::toRole)
                        .collect(Collectors.toList())
        );
    }

    public void addRoles(RoleDto roleDto) {
        rolesList.add(roleDto);
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

    public List<RoleDto> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<RoleDto> rolesList) {
        this.rolesList = rolesList;
    }
}
