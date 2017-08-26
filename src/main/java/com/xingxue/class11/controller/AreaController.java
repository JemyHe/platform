package com.xingxue.class11.controller;

import com.xingxue.class11.entity.Area;
import com.xingxue.class11.framework.entity.Result;
import com.xingxue.class11.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
public class AreaController {

    private Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    /**
     * 根据父id查询所有地区
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/area/query/root",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Area>> getAreas(Long parentId){

        try {
            List<Area> root = areaService.getByParentId(parentId);
            Result<List<Area>> result = new Result<>();
            result.setSuccess(true);
            result.setData(root);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            Result<List<Area>> result = new Result<>();
            result.setSuccess(false);
            result.setError(e.getMessage());
            return result;
        }
    }


}
