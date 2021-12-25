package com.identity.resources.domain.entity;

import com.identity.resources.domain.value_objects.ResourceId;

public class Resource {
    private ResourceId id;
    private String name;
    private String url;

    public Resource(ResourceId id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Resource() {
    }



    public ResourceId getId() {
        return id;
    }

    public void setId(ResourceId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
