package com.example.permissionRoleProject.entities;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long roleId;

    @Column(name="role_name", unique = true)
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy ="role")
    private List<UserRole> userRoleList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy ="role")
    private List<RolePermission> rolePermissionList;

    public Role(String name) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role(){}

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<RolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<RolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
    }

    public Role(String roleName, List<UserRole> userRoleList, List<RolePermission> rolePermissionList) {
        this.roleName = roleName;
        this.userRoleList = userRoleList;
        this.rolePermissionList = rolePermissionList;
    }
}
