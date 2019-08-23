package com.example.rolebasedpermission.model;

public class PermissionListResponse extends BaseResponse {
    PermissionDto data;

    public PermissionListResponse(Boolean success, String message, PermissionDto data) {
        super(success, message);
        this.data = data;
    }

    public PermissionListResponse(PermissionDto data) {
        this.data = data;
    }

    public PermissionDto getData() {
        return data;
    }

    public void setData(PermissionDto data) {
        this.data = data;
    }
}
