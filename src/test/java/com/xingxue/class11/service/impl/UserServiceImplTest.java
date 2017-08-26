package com.xingxue.class11.service.impl;

import com.xingxue.class11.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UserServiceImplTest {

    @Autowired private UserService userService;

    @Test
    public void login() throws Exception {
    }

    @Test
    public void regist() throws Exception {
    }

    @Test
    public void findByName() throws Exception {
    }

    @Test
    public void updateByParams() throws Exception {
    }

    @Test
    public void findBasicInfoByID() throws Exception {
    }

    @Test
    public void updateBasicInfo() throws Exception {
    }

    @Test
    public void saveBasicInfo() throws Exception {
    }

}