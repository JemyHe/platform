package com.xingxue.class11.dao;

import com.xingxue.class11.entity.DealDetail;
import com.xingxue.class11.framework.dao.BaseMyBatisDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * DealDetailDAO
 */
@Repository
public class DealDetailDAO extends BaseMyBatisDAO{

    /**
     * Mapper映射命名空间
     */
    private static final String MAPPER_NAMESPACE = "com.xingxue.class11.entity.mapper.DealDetailMapper";


    public DealDetail getByDealId(Long dealId) {
        return findOne(MAPPER_NAMESPACE + ".selectByDealId", dealId);
    }

    /**
     * 根据ID查询商品对应的描述
     * @param id
     * @return
     */
    public DealDetail getById(Long id) {
        return findOne(MAPPER_NAMESPACE + ".selectById", id);
    }

    /**
     * 保存商品描述
     * @param dealDetail
     * @return
     */
    public int save(DealDetail dealDetail) {
        return save(MAPPER_NAMESPACE + ".insertDealDetail", dealDetail);
    }

    /**
     * 更新商品描述信息
     * @param dealDetail
     * @return
     */
    public int updateById(DealDetail dealDetail) {
        return update(MAPPER_NAMESPACE + ".updateById", dealDetail);
    }

    public int deleteById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return delete(MAPPER_NAMESPACE + ".deleteById", params);
    }

}
