package com.example.permissionRoleProject.controller;

import com.example.permissionRoleProject.exception.CustomException;
import com.example.permissionRoleProject.model.Account;
import com.example.permissionRoleProject.model.ResponseBean;
import com.example.permissionRoleProject.response.*;
import com.example.permissionRoleProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionGetController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/get-all-users", method = RequestMethod.GET)
    public ResponseBean<UserListResponse> getAllUsers() throws CustomException {
        ResponseBean<UserListResponse> responseBean=userService.getUsers();
        return responseBean;
    }

    @RequestMapping(value = "/get-all-roles-for-user", method = RequestMethod.GET)
    public RoleResponse getroles(@RequestParam("username") String username) throws CustomException {
        RoleResponse roleResponse=userService.getAllRoles(username);
        return roleResponse;
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.DELETE)
    public ResponseBean<?> deleteUser(@RequestParam("username") String username) throws CustomException {
        return userService.deleteUser(username);
    }

    @RequestMapping(value = "/get-all-permissions", method = RequestMethod.GET)
    public ResponseBean<PermissionResponse> getAllPermissions() throws CustomException {
        return userService.getPermissions();
    }

    @RequestMapping(value = "/get-all-roles", method = RequestMethod.GET)
    public ResponseBean<RoleListResponse> getAllRoles() throws CustomException {
        return userService.getRoles();
    }


    @RequestMapping(value = "/get-all-roles-permissions", method = RequestMethod.GET)
    public ResponseBean<RolePermissionResponse> getAllRolesWithPermission() throws CustomException {
        ResponseBean<RolePermissionResponse> responseBean=userService.getRolesWithPermission();
        return responseBean;
    }


}
