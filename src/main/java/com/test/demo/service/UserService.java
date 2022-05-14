package com.test.demo.service;

import com.test.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    Boolean addUser(User user);

    String userType();

}
