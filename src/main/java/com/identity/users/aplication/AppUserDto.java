package com.identity.users.aplication;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.domain.entity.Role;
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
    private String salt;

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
        this.password = user.getPassword().getEncryptedPassword();
        this.rolesList = user.getRolesList()
                .stream().map(RoleDto::new)
                .collect(Collectors.toList());
        this.salt = user.getPassword().getSalt();
    }

    public AppUserDto() {
    }

    public AppUser toAppUser() {
        return new AppUser(
                new AppUserID(this.id),
                this.name,
                this.user,
                this.password
        );
    }

    private List<Role> getRoleList() {
        if(this.rolesList != null) {
            return this.rolesList
                    .stream().map(RoleDto::toRole)
                    .collect(Collectors.toList());
        }

        return null;
    }

    public String getId() {
        return id;
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

    public String getSalt() {
        return salt;
    }
}
