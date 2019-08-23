package com.example.rolebasedpermission.model;

public class LoginResponse extends BaseResponse {
    RolesDto data;
    public LoginResponse(Boolean success, String message, RolesDto data) {
        super(success, message);
        this.data = data;
    }

    public LoginResponse(RolesDto data) {
        this.data = data;
    }

    public RolesDto getData() {
        return data;
    }

    public void setData(RolesDto data) {
        this.data = data;
    }
}
