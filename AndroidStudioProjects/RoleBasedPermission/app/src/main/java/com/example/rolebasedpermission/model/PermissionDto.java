package com.example.rolebasedpermission.model;

import java.util.List;

public class PermissionDto {

    List<String> permissions;

    public PermissionDto(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
