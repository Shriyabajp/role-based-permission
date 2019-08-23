package com.example.rolebasedpermission.model;

import java.util.List;
public class RolesDto {
    private String username;
    private List<String> roleSet;
    private List<String> permissions;

    public List<String> getRoles() {
        return roleSet;
    }

    public void setRoles(List<String> roles) {
        this.roleSet = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public RolesDto(String username, List<String> roles, List<String> permissions) {
        this.username = username;
        this.roleSet = roles;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RolesDto(){}

}
