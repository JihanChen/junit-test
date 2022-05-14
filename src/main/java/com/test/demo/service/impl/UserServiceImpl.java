package com.test.demo.service.impl;

import com.test.demo.entity.User;
import com.test.demo.mapper.UserMapper;
import com.test.demo.service.UserService;
import com.test.demo.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Value("${my.user.type}")
    private String type;

    @Override
    public List<User> getUserList() {
        return userMapper.getAll();
    }

    @Override
    public Boolean addUser(User user) {
        Assert.notNull(user.getUserName(), "用户名不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");
        user.setPassword(Md5Util.getMD5(user.getPassword(), Md5Util.ENCODING));
        int cnt = userMapper.addUser(user);
        return cnt > 0;
    }

    @Override
    public String userType() {
        return type;
    }
}
