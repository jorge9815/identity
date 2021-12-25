package com.identity.roles.infrastructure;

import com.identity.roles.domain.entity.Role;
import com.identity.roles.domain.value_objects.RoleID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class RoleModel {
    @Id
    private String id;
    private String name;

    public RoleModel(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public RoleModel(Role role){
        this.id = role.getId().getValue();
        this.name = role.getName();
    }

    public RoleModel(){}

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
