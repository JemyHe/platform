package com.xingxue.class11.service;


import com.xingxue.class11.entity.Area;

import java.util.List;

/**
 * AreaService
 */
public interface AreaService {


    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Area getByName(String name);

    /**
     * 根据父节点ID查询下级
     * @param parentId
     * @return
     */
    List<Area> getByParentId(Long parentId);



    /**********************后台**************************/
    /**
     * 保存
     * @param area
     */
    public void save(Area area);

    /**
     * 更新
     * @param area
     */
    public void update(Area area);

    /**
     * 查询全部
     * @return
     */
    public List<Area> getAll() ;

    /**
     * 分页查询
     * @param search
     * @return
     */
   /*public PagingResult<Area> getPage(Search search);*/



}
