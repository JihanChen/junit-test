package com.test.demo.mapper;

import com.test.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAll();

    int addUser(User user);
}
