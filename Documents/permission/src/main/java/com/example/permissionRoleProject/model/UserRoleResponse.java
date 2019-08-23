package com.example.permissionRoleProject.model;

import java.util.Set;

public class UserRoleResponse {

   private String username;
   private String  emailId;
   private Set<EnumRoles> rolesSet;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<EnumRoles> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(Set<EnumRoles> rolesSet) {
        this.rolesSet = rolesSet;
    }

    public UserRoleResponse(String username, String emailId, Set<EnumRoles> rolesSet) {
        this.username = username;
        this.emailId = emailId;
        this.rolesSet = rolesSet;
    }
}
