package com.example.rolebasedpermission.model;

public class PermissionResponseDto extends BaseResponse {
    PermissionResponse data;

    public PermissionResponseDto(Boolean success, String message, PermissionResponse data) {
        super(success, message);
        this.data = data;
    }

    public PermissionResponseDto(PermissionResponse data) {
        this.data = data;
    }

    public PermissionResponse getData() {
        return data;
    }

    public void setData(PermissionResponse data) {
        this.data = data;
    }
}
