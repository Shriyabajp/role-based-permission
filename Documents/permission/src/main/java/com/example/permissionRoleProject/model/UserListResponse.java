package com.example.permissionRoleProject.model;

import com.example.permissionRoleProject.entities.User;

import java.util.List;

public class UserListResponse  {

       private List<User> userList;

       public List<User> getUserList() {
        return userList;
    }

       public void setUserList(List<User> userList) {
        this.userList = userList;
    }

       public UserListResponse(List<User> userList) {
        this.userList = userList;
    }
}
