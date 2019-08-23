package com.example.permissionRoleProject.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "permission_name")
    private String permissionName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy ="permission")
    private List<RolePermission> rolePermissionList;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<RolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<RolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
    }

    public Permission(String permissionName, List<RolePermission> rolePermissionList) {
        this.permissionName = permissionName;
        this.rolePermissionList = rolePermissionList;
    }

    public String getName() {
        return permissionName;
    }

    public void setName(String name) {
        this.permissionName = name;
    }

    public Permission(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
