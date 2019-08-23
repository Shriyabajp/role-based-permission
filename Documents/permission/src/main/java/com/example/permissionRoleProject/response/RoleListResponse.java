package com.example.permissionRoleProject.response;

import java.util.List;

public class RoleListResponse {
    List<String> rolenames;

    public List<String> getRolenames() {
        return rolenames;
    }

    public void setRolenames(List<String> rolenames) {
        this.rolenames = rolenames;
    }

    public RoleListResponse(List<String> rolenames) {
        this.rolenames = rolenames;
    }
}
