package com.example.permissionRoleProject.model;

import com.example.permissionRoleProject.entities.Role;

import java.util.List;

public class RoleListResponse {
    List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public RoleListResponse(List<Role> roles) {
        this.roles = roles;
    }
}
