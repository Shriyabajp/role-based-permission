package com.example.permissionRoleProject.repository;

import com.example.permissionRoleProject.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {

    Permission findByPermissionName(String permissionname);


}
