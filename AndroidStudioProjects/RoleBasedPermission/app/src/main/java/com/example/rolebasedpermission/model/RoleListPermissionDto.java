package com.example.rolebasedpermission.model;

import java.util.List;

public class RoleListPermissionDto {
    private List<RolePermissionDto> rolePermissionDtoList;

    public List<RolePermissionDto> getRolePermissionDtoList() {
        return rolePermissionDtoList;
    }

    public void setRolePermissionDtoList(List<RolePermissionDto> rolePermissionDtoList) {
        this.rolePermissionDtoList = rolePermissionDtoList;
    }

    public RoleListPermissionDto(List<RolePermissionDto> rolePermissionDtoList) {
        this.rolePermissionDtoList = rolePermissionDtoList;
    }
}
