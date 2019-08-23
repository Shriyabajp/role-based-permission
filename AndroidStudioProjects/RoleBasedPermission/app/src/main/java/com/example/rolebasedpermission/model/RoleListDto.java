package com.example.rolebasedpermission.model;

import java.util.List;

public class RoleListDto {
    List<String> rolenames;

    public RoleListDto(List<String> rolenames) {
        this.rolenames = rolenames;
    }

    public List<String> getRolenames() {
        return rolenames;
    }

    public void setRolenames(List<String> rolenames) {
        this.rolenames = rolenames;
    }
}
