package com.example.rolebasedpermission.model;

import com.google.gson.annotations.SerializedName;

public class Permission {
    @SerializedName("permissionId")
    private Integer permissionId;
    @SerializedName("name")
    private String name;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Permission(Integer permissionId, String name) {
        this.permissionId = permissionId;
        this.name = name;
    }

    public Permission(){}
}
