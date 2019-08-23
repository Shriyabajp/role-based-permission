package com.example.rolebasedpermission.model;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse extends BaseResponse {
     UserListDto data;

    public UserListDto getData() {
        return data;
    }

    public void setData(UserListDto data) {
        this.data = data;
    }

    public UserListResponse(Boolean success, String message, UserListDto data) {
        super(success, message);
        this.data = data;
    }

    public UserListResponse(UserListDto data) {
        this.data = data;
    }
}
