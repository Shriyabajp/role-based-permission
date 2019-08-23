package com.example.permissionRoleProject.model;


import javax.persistence.*;

//@Entity
//@Table(name = "role_permission")
public class RolePermissionEnum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name= "role_name")
    @Enumerated(EnumType.STRING)
    private EnumRoles roleName;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public EnumRoles getRoleName() {
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(EnumRoles roleName) {
        this.roleName = roleName;
    }

}
