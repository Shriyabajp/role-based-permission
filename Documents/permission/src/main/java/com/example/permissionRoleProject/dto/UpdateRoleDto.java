package com.example.permissionRoleProject.dto;

public class UpdateRoleDto {
    private String username;
    private String rolename;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public UpdateRoleDto(String username, String rolename) {
        this.username = username;
        this.rolename = rolename;
    }
}
