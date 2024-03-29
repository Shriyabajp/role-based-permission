package com.example.permissionRoleProject.entities;

import javax.persistence.*;

@Entity
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name= "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name= "permission_id")
    private Permission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public RolePermission(){}
}
