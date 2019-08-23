package com.example.permissionRoleProject.response;
import com.example.permissionRoleProject.dto.UserDto;

import java.util.List;

public class UserListResponse {
    private List<UserDto> userList;

    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
    }

    public UserListResponse(List<UserDto> userList) {
        this.userList = userList;
    }

    public UserListResponse(){}
}
