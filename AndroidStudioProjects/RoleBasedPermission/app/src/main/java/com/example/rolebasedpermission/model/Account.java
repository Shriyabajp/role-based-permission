package com.example.rolebasedpermission.model;

import com.google.gson.annotations.SerializedName;

public class Account {

    private String username;

    private String password;

    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String email, String password) {
        this.username = email;
        this.password = password;
    }
    public Account(){}
}
