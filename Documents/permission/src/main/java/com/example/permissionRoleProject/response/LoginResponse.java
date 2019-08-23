package com.example.permissionRoleProject.response;

import java.util.List;
import java.util.Set;

public class LoginResponse {
    String username;
    List<String> roleSet;
    Set<String> permissions;

    public LoginResponse(String username, List<String> roleSet, Set<String> permissions) {
        this.username = username;
        this.roleSet = roleSet;
        this.permissions = permissions;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(List<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}


