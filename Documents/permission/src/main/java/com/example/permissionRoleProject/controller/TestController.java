package com.example.permissionRoleProject.controller;

import com.example.permissionRoleProject.dto.UserDto;
import com.example.permissionRoleProject.model.ResponseBean;
import com.example.permissionRoleProject.response.UserListResponse;
import com.example.permissionRoleProject.services.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @Autowired
    private Test test;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public ResponseBean<UserListResponse> test(@RequestBody UserDto userDto)  {
        test.test(userDto);
        return new ResponseBean<>();
    }
}
