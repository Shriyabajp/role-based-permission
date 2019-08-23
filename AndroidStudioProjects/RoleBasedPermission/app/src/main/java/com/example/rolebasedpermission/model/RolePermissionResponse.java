package com.example.rolebasedpermission.model;


public class RolePermissionResponse extends BaseResponse {

    RoleListPermissionDto data;

    public RolePermissionResponse(Boolean success, String message, RoleListPermissionDto data) {
        super(success, message);
        this.data = data;
    }

    public RolePermissionResponse(RoleListPermissionDto data) {
        this.data = data;
    }

    public RoleListPermissionDto getData() {
        return data;
    }

    public void setData(RoleListPermissionDto data) {
        this.data = data;
    }
}
