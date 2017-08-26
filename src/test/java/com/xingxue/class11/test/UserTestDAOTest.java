package com.xingxue.class11.test;

import com.xingxue.class11.entity.User;
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
public class UserTestDAOTest {

    @Autowired
    private UserTestDAO userTestDAO;

    @Test
    public void selectByPrimaryKey() throws Exception {
        User user = userTestDAO.selectByPrimaryKey(1L);
        System.out.println(user);
    }

    @Test
    public void selectByName() throws Exception {
    }

    @Test
    public void insertSelective() throws Exception {
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
    }

}