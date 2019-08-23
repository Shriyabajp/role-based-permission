package com.example.permissionRoleProject.response;

import com.example.permissionRoleProject.dto.RolePermissionDto;

import java.util.List;

public class RolePermissionResponse {

    private List<RolePermissionDto> rolePermissionDtoList;

    public List<RolePermissionDto> getRolePermissionDtoList() {
        return rolePermissionDtoList;
    }

    public void setRolePermissionDtoList(List<RolePermissionDto> rolePermissionDtoList) {
        this.rolePermissionDtoList = rolePermissionDtoList;
    }

    public RolePermissionResponse(List<RolePermissionDto> rolePermissionDtoList) {
        this.rolePermissionDtoList = rolePermissionDtoList;
    }

    public RolePermissionResponse(){}
}
