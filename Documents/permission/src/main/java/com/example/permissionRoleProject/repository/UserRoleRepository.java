package com.example.permissionRoleProject.repository;

import com.example.permissionRoleProject.entities.Role;
import com.example.permissionRoleProject.entities.User;
import com.example.permissionRoleProject.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

     public UserRole findByUserAndRole(User user, Role role);



}
