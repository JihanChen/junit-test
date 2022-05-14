package com.test.demo.service.impl;

import com.test.demo.entity.User;
import com.test.demo.mapper.UserMapper;
import com.test.demo.util.Md5Util;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 * Service层进行单元测试 Mockito
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    UserServiceImpl userServiceImpl;
    private User user;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserId("111");
        user.setPassword("111");
        user.setUserName("111");
    }


    /**
     * 验证获取用户列表方法
     */
    @Test
    public void testGetUserList() {
        List<User> list = new ArrayList<>();
        list.add(user);
        when(userServiceImpl.getUserList()).thenReturn(list);
        assertEquals(1, list.size());
    }


    /**
     * 验证用户新增方法
     */
    @Test
    public void testAddUser() {
        // 静态方法进行mock
        MockedStatic<Md5Util> md5UtilMockedStatic = mockStatic(Md5Util.class);
        md5UtilMockedStatic.when(() -> Md5Util.getMD5(any(), any())).thenReturn("32bf0e6fcff51e53bd74e70ba1d622b2");
        when(userMapper.addUser(any())).thenReturn(1);
        Boolean addCnt = userServiceImpl.addUser(user);
        assertTrue(addCnt);
    }


    /**
     * 验证用户名为空的异常场景
     */
    @Test
    public void testUserNameIsNull() {
        user.setUserName(null);
        when(userMapper.addUser(any())).thenReturn(1);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("用户名不能为空");
        userServiceImpl.addUser(user);
    }

    /**
     * 验证密码为空的异常场景
     */
    @Test
    public void testPwdIsNull() {
        user.setPassword(null);
        when(userMapper.addUser(any())).thenReturn(1);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("密码不能为空");
        userServiceImpl.addUser(user);
    }

    /**
     *  实现中包含@Value等私有成员变量
     *  使用ReflectionTestUtils对私有的方法或属性进行注解赋值
     *  非spring项目可以写个反射工具类
     */
    @Test
    public void testUserType(){
        ReflectionTestUtils.setField(userServiceImpl, "type","ttt");
        String type = userServiceImpl.userType();
        assertEquals("ttt", type);
    }
}