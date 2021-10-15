package com.identity.users.infrastructure;

import com.identity.roles.infrastructure.RoleModel;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.value_objects.AppUserID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class AppUserModel {
    @Id
    private String id;
    private String name;
    private String user;
    private String password;

    @ManyToMany
    private List<RoleModel> rolesList;

    public AppUserModel(String id, String name, String user, String password, List<RoleModel> rolesList) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.rolesList = rolesList;
    }

    public AppUserModel(AppUser user) {
        this.id = user.getId().getValue();
        this.name = user.getName();
        this.user = user.getUser();
        this.password = user.getPassword();
        this.rolesList = user.getRolesList()
                .stream().map(RoleModel::new)
                .collect(Collectors.toList());
    }

    public AppUserModel() {
    }

    public AppUser toAppUser(){
        return new AppUser(
                new AppUserID(this.id),
                this.name,
                this.user,
                this.password,
                this.rolesList
                        .stream().map(RoleModel::toRole)
                        .collect(Collectors.toList())
        );
    }

    public void update(AppUser user){
        this.name = user.getName();
        this.user = user.getUser();
        this.password = user.getPassword();
        this.rolesList = user.getRolesList()
                .stream().map(RoleModel::new)
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public List<RoleModel> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<RoleModel> rolesList) {
        this.rolesList = rolesList;
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
}
