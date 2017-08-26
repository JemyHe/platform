package com.xingxue.class11.service.impl;

import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.service.DealCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class DealCategoryServiceImplTest {

    @Autowired
    private DealCategoryService dealCategoryService;

    @Test
    public void getCategories() throws Exception {

        List<DealCategory> categories = dealCategoryService.getCategories();
        for(DealCategory dealCategory:categories){
            System.out.println(dealCategory);
        }
    }

    @Test
    public void getCategoriesByPage() throws Exception{
        Map<String,Object> params = new HashMap<>();
        PagingResult<DealCategory> categoriesByPage = dealCategoryService.getCategoriesByPage(2, 3,params);
        System.out.println(categoriesByPage);
    }

}