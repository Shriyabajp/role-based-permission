package com.example.permissionRoleProject.controller;

import com.example.permissionRoleProject.dto.*;
import com.example.permissionRoleProject.exception.CustomException;
import com.example.permissionRoleProject.model.Account;
import com.example.permissionRoleProject.model.ResponseBean;
import com.example.permissionRoleProject.response.LoginResponse;
import com.example.permissionRoleProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionPostController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/signup", method = RequestMethod.POST, produces = "application/json")
    public ResponseBean<?> signup(@RequestBody UserDto userDto)  {
        ResponseBean<?> responseBean=userService.signupPage(userDto);
        return responseBean;
    }
    @RequestMapping(value="/saveLocation", method = RequestMethod.POST, produces = "application/json")
    public ResponseBean<?> saveLocation(@RequestBody LocationDto locationDto)  {
        ResponseBean<?> responseBean=userService.saveLocationPage(locationDto);
        return responseBean;
    }

    @RequestMapping(value="/update-new-role", method = RequestMethod.PUT, produces = "application/json")
    public ResponseBean<?> update(@RequestBody UpdateRoleDto updateRoleDto) throws CustomException {
        ResponseBean<?> responseBean=userService.updateUserRole(updateRoleDto);
                return responseBean;
    }

    @RequestMapping(value="/update-new-password", method = RequestMethod.PUT, produces = "application/json")
    public ResponseBean<?> updatePassword(@RequestBody UpdatePaswwordDto updatePaswwordDto) throws CustomException {
        ResponseBean<?> responseBean=userService.updateUserPassword(updatePaswwordDto);
        return responseBean;
    }

    @RequestMapping(value = "create-permissions",method = RequestMethod.POST)
    public ResponseBean<?> createPermission(@RequestBody PermissionDto permissionDto){
        return userService.createPermissions(permissionDto);
    }

    @RequestMapping(value = "create-roles",method = RequestMethod.POST)
    public ResponseBean<RolePermissionDto> createRoles(@RequestBody RolePermissionDto rolePermissionDto){
        return userService.createRoles(rolePermissionDto);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json")
    public ResponseBean<LoginResponse> login(@RequestBody Account account) throws CustomException {
        return userService.loginPage(account);
    }

//        @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    public ResponseBean<?> updateUser(@RequestBody  UserDto userDto) {
//        if (userDto.getEmail() == null || userDto.getUserName() == null || userDto.getPassword() == null ||userDto.getRoleName() == null) {
//            return new ResponseBean<>(false, "Fill in the empty fields");
//        }
//            User user = new User();
//            BeanUtils.copyProperties(userDto, user);
//            user.set
//            userServices.saveUser(user);
//            user.getUserId();
//
//            return new ResponseBean<>(true, "user saved successfully");
//
//    }


//    @RequestMapping(value="/update", method = RequestMethod.PUT, produces = "application/json")
//    public ResponseBean<?> updateUsers(@RequestBody UserDto userDto)  {
//        ResponseBean<?> responseBean=userService.updateUser(userDto);
//        return responseBean;
//    }



}
