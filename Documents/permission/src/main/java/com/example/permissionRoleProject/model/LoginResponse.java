package com.example.permissionRoleProject.model;

import java.util.ArrayList;
import java.util.Set;

public class LoginResponse  {

 String username;
 Set<String> roleSet;
 Set<String> permissions;
// String roleName;
// ArrayList<String> permissions;

//    public LoginResponse(String username, String roleName, ArrayList<String> permissions) {
//        this.username = username;
//        this.roleName = roleName;
//        this.permissions = permissions;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//    public ArrayList<String> getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(ArrayList<String> permissions) {
//        this.permissions = permissions;
//    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public LoginResponse(String username, Set<String> roleSet, Set<String> permissions) {
        this.username = username;
        this.roleSet = roleSet;
        this.permissions = permissions;
    }
}
