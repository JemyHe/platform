package com.xingxue.class11.dao;

import com.xingxue.class11.entity.Area;
import com.xingxue.class11.framework.dao.BaseMyBatisDAO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 地区
 */
@Repository
public class AreaDAO extends BaseMyBatisDAO {

    private final String MAPPER_NAMESPACE = "com.xingxue.class11.entity.mapper.AreaMapper";

    /**
     * 保存
     * @param area
     */
    public void save(Area area) {
        super.save(MAPPER_NAMESPACE + ".insertSelective", area);
    }

    /**
     * 更新
     * @param area
     */
    public void update(Area area) {
        super.save(MAPPER_NAMESPACE + ".updateByPrimaryKeySelective", area);
    }

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public Area getByName(String name) {
        return super.findOne(MAPPER_NAMESPACE + ".selectByName", name);
    }

    /**
     * 根据父节点ID查询下级
     * @param parentId
     * @return
     */
    public List<Area> getByParentId(Long parentId) {
        return super.findAll(MAPPER_NAMESPACE + ".selectByParentId", parentId);
    }

    /**
     * 查询全部
     * @return
     */
    public List<Area> findAll() {
        return super.findAll(MAPPER_NAMESPACE + ".selectAll");
    }

    /**
     * 分页查询
     * @param search
     * @return
     */
    /*public PagingResult<Area> findPage(Search search) {
        return super.findForPage(MAPPER_NAMESPACE + ".countPage", MAPPER_NAMESPACE + ".selectPage", search);
    }*/

}
