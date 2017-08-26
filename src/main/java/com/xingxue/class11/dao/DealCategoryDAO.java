package com.xingxue.class11.dao;



import com.xingxue.class11.constants.DealConstant;
import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.framework.dao.BaseMyBatisDAO;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Deal分类
 */
@Repository
public class DealCategoryDAO extends BaseMyBatisDAO {

    /**
     * Mapper映射命名空间
     */
    private static final String MAPPER_NAMESPACE = "com.xingxue.class11.entity.mapper.DealCategoryMapper";

    /**
     * 查询所有的商品类别
     * @return
     */
    public List<DealCategory> getAllWithoutDeleted() {
        return super.findAll(MAPPER_NAMESPACE + ".selectAllWithoutDeleted");
    }

    /**
     * 查询直接下级类别
     * @param parentId
     * @return
     */
    public List<DealCategory> getDirectSubs(Long parentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        return findAll(MAPPER_NAMESPACE + ".selectDirectSubs", params);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public DealCategory getById(Long id) {
        return findOne(MAPPER_NAMESPACE + ".selectByPrimaryKey", id);
    }


    /**
     * 根据urlName查询
     * @param urlName
     * @return
     */
    public DealCategory getByUrlName(String urlName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("urlName", urlName);
        return findOne(MAPPER_NAMESPACE + ".selectByUrlName", params);
    }

    /**
     * 分页查询
     */
    public PagingResult<DealCategory> findDataForPage(int page,int rows,Map<String,Object> params){
        return super.findForPage(MAPPER_NAMESPACE+".countPageCategories",MAPPER_NAMESPACE+".selectPageCategories",page,rows,params);
    }

    /**
     * 分页查询
     */
    public PagingResult<DealCategory> findDataForPage(Search search){
        return super.findForPage(MAPPER_NAMESPACE+".countPageCategories",MAPPER_NAMESPACE+".selectPageCategories",search);
    }

    /**
     * 更新
     * @param category
     * @return
     */
    public int updateById(DealCategory category) {
        return update(MAPPER_NAMESPACE + ".updateByPrimaryKeySelective", category);
    }

    /**
     * 保存
     * @param category
     * @return
     */
    public int save(DealCategory category) {
        return save(MAPPER_NAMESPACE + ".insertSelective", category);
    }


    /**
     * 根据ID删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
        return delete(MAPPER_NAMESPACE + ".deleteByPrimaryKey", id);
    }


}
