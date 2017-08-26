package com.xingxue.class11.dao;

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
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void getById() throws Exception {
        User byId = userDAO.getById(1L);
        System.out.println(byId+"---------------"+byId.getId());

        /*
        User{, password='098F6BCD4621D373CADE4E832627B4F6', name='road', loginTime=Sun Dec 18 06:34:17 CST 2016,
        createTime=Sat Sep 10 11:26:04 CST 2016, updateTime=Sun Dec 18 06:34:17 CST 2016}
         */
    }

    @Test
    public void getByName() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void updateById() throws Exception {
        User user = new User();
        user.setId(6L);
        user.setName("hahahhaha");
        int i = userDAO.updateById(user);
        System.out.println(i);
    }

}