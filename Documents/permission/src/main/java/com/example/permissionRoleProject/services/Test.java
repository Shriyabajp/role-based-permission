package com.example.permissionRoleProject.services;

import com.example.permissionRoleProject.dto.UserDto;
import com.example.permissionRoleProject.entities.User;
import com.example.permissionRoleProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;

@Service
public class Test {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void test(UserDto userDto) {
        User user1 = new User();
        user1.setUserName(userDto.getUserName());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(userDto.getPassword());
        userRepository.save(user1);

        int i = 0;
        int j= 1;
        int z = j/i;

        User user2 = new User();
        user2.setUserName(userDto.getUserName());
        user2.setEmail(userDto.getEmail());
        user2.setPassword(userDto.getPassword());
        userRepository.save(user2);

    }

}
