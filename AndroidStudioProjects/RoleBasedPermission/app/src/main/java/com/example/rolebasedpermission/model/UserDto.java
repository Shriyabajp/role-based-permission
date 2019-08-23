package com.example.rolebasedpermission.model;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
    private String userName;
    private String email;
    private String password;
    private List<String> roleNames;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public UserDto(String userName, String email, String password, List<String> roleNames) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleNames = roleNames;
    }
}
