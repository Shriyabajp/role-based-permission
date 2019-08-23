package com.example.rolebasedpermission.model;


public class PermissionResponse {
    private String name;

    public PermissionResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PermissionResponse(){}
}
