package com.example.permissionRoleProject.dto;

import com.example.permissionRoleProject.model.EnumRoles;

public class NewRoleDto {

    private String userName;
    private EnumRoles roleName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Enum getRoleName() {
        return roleName;
    }

    public void setRoleName(EnumRoles roleName) {
        this.roleName = roleName;
    }

    public NewRoleDto(String userName, EnumRoles roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }
}
