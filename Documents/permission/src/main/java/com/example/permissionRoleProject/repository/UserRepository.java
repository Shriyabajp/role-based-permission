package com.example.permissionRoleProject.repository;

import com.example.permissionRoleProject.entities.User;
import com.example.permissionRoleProject.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface UserRepository extends JpaRepository<User,Long> {

        User findByUserName(String username);

        public User findByUserNameAndPassword(String username, String password);

}
