package com.xingxue.class11.service.impl;

import com.xingxue.class11.dao.AreaDAO;
import com.xingxue.class11.entity.Area;
import com.xingxue.class11.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * AreaService
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired private AreaDAO areaDAO;

    /**********************网站**************************/

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public Area getByName(String name) {
        return this.areaDAO.getByName(name);
    }

    /**
     * 根据父节点ID查询下级
     * @param parentId
     * @return
     */
    public List<Area> getByParentId(Long parentId) {
        return this.areaDAO.getByParentId(parentId);
    }



    /**********************后台**************************/
    /**
     * 保存
     * @param area
     */
    public void save(Area area) {
        this.areaDAO.save(area);
    }

    /**
     * 更新
     * @param area
     */
    public void update(Area area) {
        this.areaDAO.update(area);
    }

    /**
     * 查询全部
     * @return
     */
    public List<Area> getAll() {
        List<Area> areas = this.areaDAO.findAll();
        List<Area> roots = new ArrayList<>();
        List<Area> subs = new ArrayList<>();
        for(Area area:areas){
            if(area.getParentId() == 0){
                roots.add(area);
            }else{
                subs.add(area);
            }
        }
        for (Area area:roots){
            buildSubs(area,subs);
        }
        return roots;
       /*
        //java8写法
        List<Area> roots = areas.stream().filter(area -> (area.getParentId() == 0)).collect(Collectors.toList());
        List<Area> subs = areas.stream().filter(area -> (area.getParentId() != 0)).collect(Collectors.toList());
        roots.forEach(root -> buildSubs(root, subs));
        return roots;*/
    }

    /**
     * 递归构建
     * @param parent
     * @param subs
     */
    private void buildSubs(Area parent, List<Area> subs) {
        List<Area> children = new ArrayList<>();
        for (Area area:subs){
            if(parent.getId() == area.getParentId()){
                children.add(area);
            }
        }
        if(!CollectionUtils.isEmpty(children)){
            parent.setChildren(children);
            for(Area area:children){
                buildSubs(area,subs);
            }
        }
        /*
        //java8 写法
        List<Area> children = subs.stream().filter(sub -> (Objects.equals(sub.getParentId(), parent.getId()))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(children)) {
            parent.setChildren(children);
            children.forEach(child -> buildSubs(child, subs));
        }*/
    }

    /**
     * 分页查询
     * @param search
     * @return
     */
    /*public PagingResult<Area> getPage(Search search) {
        return this.areaDAO.findPage(search);
    }*/



}
