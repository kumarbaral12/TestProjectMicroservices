package com.example.user.controller;

import com.example.user.common.UserDepartment;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/getUsersWithDepartments")
    public List<UserDepartment> getUsersWithDepartments(){
        log.info("Inside getUserWithDepartment method of UserController");
        return userService.getUsersWithDepartments();
    }

    @GetMapping("/getUserWithDepartment/{id}")
    public UserDepartment getUserWithDepartment(@PathVariable Long id){
        log.info("Inside getUserWithDepartment method of UserController");
        return userService.getUserWithDepartment(id);
    }

}
