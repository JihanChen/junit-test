package com.test.demo.mapper.one;

import com.test.demo.entity.User;
import com.test.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserMapper.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserMapperTest{
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