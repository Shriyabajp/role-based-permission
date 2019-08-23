package com.example.rolebasedpermission.model;

public class SignUpResponse extends BaseResponse {

    User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public SignUpResponse(Boolean success, String message, User data) {
        super(success, message);
        this.data = data;
    }

    public SignUpResponse(User data) {
        this.data = data;
    }
}
