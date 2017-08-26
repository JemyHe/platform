package com.xingxue.class11.dao;

import com.xingxue.class11.entity.Deal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class DealDAOTest {

    @Autowired
    private DealDAO dealDAO;

    @Test
    public void selectOnIndex() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(3L);
        ids.add(5L);
        List<Deal> deals = dealDAO.selectOnIndex(ids, 367L, 3);
        for (Deal deal:deals){
            System.out.println(deal);
        }
    }

    @Test
    public void selectForCart() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(7L);
        ids.add(5L);
        List<Deal> deals = dealDAO.selectForCart(ids);
        for (Deal deal:deals){
            System.out.println(deal);
        }
    }

}