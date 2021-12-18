package com.identity.resources.infrastructure;

import com.identity.resources.domain.entity.Resource;
import com.identity.resources.domain.value_objects.ResourceId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="resources")
public class ResourceModel {
    @Id
    private String id;
    private String name;
    private String url;

    public ResourceModel(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public ResourceModel(Resource resource){
        this.id = resource.getId().getValue();
        this.name = resource.getName();
    }

    public ResourceModel() {}

    public Resource toRole(){
        return new Resource(new ResourceId(this.id), this.name);
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