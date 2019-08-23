package com.example.permissionRoleProject.repository;

import com.example.permissionRoleProject.entities.Permission;
import com.example.permissionRoleProject.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {

     Location findByLatAndLng(double lat, double lng);

}
