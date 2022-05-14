package com.test.demo.controller;

import com.test.demo.entity.User;
import com.test.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller层进行单元测试
 * @see {https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-framework}
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    MockMvc mockMvc;
    /**
     * controller 中依赖注入的service进行mock
     */
    @Mock
    UserService userService;
    /**
     * 需要测试的controller类
     */
    @InjectMocks
    UserController userController;

    @Before
    public void setUp() throws Exception {
        // 对userController进行mockmvc
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUserList() throws Exception {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUserId("110");
        user.setUserName("110_name");
        user.setPassword("110_pwd");
        userList.add(user);
        when(userService.getUserList()).thenReturn(userList);  // service 调用方法结果设置
        // 使用mockmvc进行接口验证
        mockMvc.perform(
                get("/user/getUserList")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())  // 输出信息
                // 验证返回是200状态
                .andExpect(status().isOk())
                // 验证返回对list第一个对象对userId 等于 110，多层结构嵌套如下
                // example：
                // {"code":200,"message":”ok“,"data":{"total":1,"list":[{"userId":110,"userName":null,"password":null}]},"other":0}
                // ==> jsonPath("$.data.list[0].userId").value(110)
                .andExpect(jsonPath("$.[0].userId").value(110)); // [{"userId":"110","userName":"110_name","password":"110_pwd"}]
    }

    @Test
    public void addUser() throws Exception {
        // 对service对调用方法进行设置结果
        when(userService.addUser(any())).thenReturn(true);
        // 使用mockmvc进行接口验证
        String jsonContent = "{\"userId\":\"111\",\"userName\":\"111_name\",\"password\":\"111_pwd\"}";
        MvcResult mvcResult = mockMvc.perform(
                post("/user/addUser")
                        // contentType表示具体请求中的媒体类型为json数据格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // content 指定body内容
                        .content(jsonContent))
                .andDo(MockMvcResultHandlers.print())
                // 验证返回是200状态
                .andExpect(status().isOk())
                .andReturn();
        // 根据上面调用获取到的结果自定义断言
        assertEquals("true", mvcResult.getResponse().getContentAsString());
    }
}