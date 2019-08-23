package com.example.rolebasedpermission.model;


import java.io.Serializable;
import java.util.List;

public class  User extends BaseResponse implements Serializable {

    private String userName;
    private String email;
    private String password;
    private List<String> roleNames;

    public User(Boolean success, String message, String userName, String email, String password, List<String> roleNames) {
        super(success, message);
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleNames = roleNames;
    }

    public User(String userName, String email, String password, List<String> roleNames) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleNames = roleNames;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public  User(){}
}
