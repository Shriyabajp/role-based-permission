package com.example.permissionRoleProject.response;

import java.util.List;

public class RoleResponse {
    private boolean status;
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public RoleResponse(boolean status, List<String> roles) {
        this.status = status;
        this.roles = roles;
    }
}
