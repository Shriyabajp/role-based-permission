package com.example.permissionRoleProject.model;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;

//@Entity
//@Table(name = "user_role")
public class UserRoleEnum {
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name= "role_name")
    @Enumerated(EnumType.STRING)
    private EnumRoles roleName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EnumRoles getRoleName() {
        return roleName;
    }

    public void setRoleName(EnumRoles roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
