package com.test.demo.mapper.two;

import com.test.demo.entity.User;
import com.test.demo.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class UserMapperMybatisTest extends BaseMybatisTest{
    @Autowired
    private UserMapper userMapper;

    @Test
    @Sql(scripts = "/user-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAll() {
        List<User> all = userMapper.getAll();
        assertEquals(6, all.size());
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserId("111");
        user.setUserName("111_name");
        user.setPassword("111_pwd");
        int i = userMapper.addUser(user);
        assertEquals(1, i);
    }
}