package com.example.rolebasedpermission.model;

import java.util.List;

public class RolePermissionDto {

    private String roleName;
    private List<String> permissions;

    public RolePermissionDto(String roleName, List<String> permissions) {
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public RolePermissionDto(){}
}
