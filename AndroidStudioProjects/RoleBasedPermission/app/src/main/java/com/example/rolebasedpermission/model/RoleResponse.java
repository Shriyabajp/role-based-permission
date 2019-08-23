package com.example.rolebasedpermission.model;

public class RoleResponse extends BaseResponse {

    RoleListDto data;

    public RoleResponse(Boolean success, String message, RoleListDto data) {
        super(success, message);
        this.data = data;
    }

    public RoleResponse(RoleListDto data) {
        this.data = data;
    }

    public RoleListDto getData() {
        return data;
    }

    public void setData(RoleListDto data) {
        this.data = data;
    }
}
