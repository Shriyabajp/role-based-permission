package com.example.rolebasedpermission.model;

import com.google.gson.annotations.SerializedName;

public class Role {

    private Integer roleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.name = name;
    }

    public Role(){}
}


