package com.xingxue.class11.service;

import com.xingxue.class11.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void login() throws Exception {
        User user = new User();
        user.setName("maobo");
        user.setPassword("123456");
        User login = userService.login(user);
        System.out.println(login);
    }

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setName("bxy");
        user.setPassword("123456");
        User register = userService.register(user);
        System.out.println(register);
    }

    @Test
    public void getByUsername() throws Exception {
        String name = "bxy";
        User byUsername = userService.getByUsername(name);
        System.out.println(byUsername);
    }

    @Test
    public void updateByIdSelective() throws Exception {
    }

    @Test
    public void getBasicInfoByUserId() throws Exception {
    }

    @Test
    public void updateBasicInfo() throws Exception {
    }

    @Test
    public void saveBasicInfo() throws Exception {
    }

}