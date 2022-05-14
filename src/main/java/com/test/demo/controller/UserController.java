package com.test.demo.controller;

import com.test.demo.entity.User;
import com.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "getUserList")
    public List<User> getUserList(){
        return userService.getUserList();
    }
    @PostMapping(value = "addUser")
    public Boolean addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping(value = "getType")
    public String getType(){
        return userService.userType();
    }
}
