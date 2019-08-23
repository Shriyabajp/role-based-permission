package com.example.permissionRoleProject.repository;

import com.example.permissionRoleProject.entities.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {
}
