package com.example.permissionRoleProject.repository;

import com.example.permissionRoleProject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

   Role findByRoleName(String rolename);


   List<Role> findAll();
}
