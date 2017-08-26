package com.xingxue.class11.service;

import com.xingxue.class11.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class AreaServiceTest {

    @Autowired private AreaService areaService;

    @Test
    public void getByParentId() throws Exception {
        List<Area> byParentId = areaService.getByParentId(0L);

    }

}