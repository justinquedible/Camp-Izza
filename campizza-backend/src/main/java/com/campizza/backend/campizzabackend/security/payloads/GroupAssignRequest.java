package com.campizza.backend.campizzabackend.security.payloads;

public class GroupAssignRequest {
    Long id;
    String group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
