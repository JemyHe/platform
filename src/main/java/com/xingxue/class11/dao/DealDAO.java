package com.xingxue.class11.dao;


import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.framework.dao.BaseMyBatisDAO;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Deal DAO
 */
@Repository
public class DealDAO extends BaseMyBatisDAO {


    private final String MAPPER_NAMESPACE = "com.xingxue.class11.entity.mapper.DealMapper";

    /**
     *  根据地区，一组商品类别，状态查询商品
     * @param categoryIds
     * @param areaId
     * @param publishStatus
     * @return
     */
    public List<Deal> selectOnIndex(List<Long> categoryIds,Long areaId,int publishStatus){
        Map<String,Object> params = new HashMap<>();
        params.put("categoryIds",categoryIds);
        params.put("areaId",areaId);
        params.put("publishStatus",publishStatus);
        return super.findAll(MAPPER_NAMESPACE+".selectOnIndex",params);
    }

    /**
     *  根据一组商品id查询
     * @param ids
     * @return
     */
    public List<Deal> selectForCart(List<Long> ids){
        Map<String,Object> params = new HashMap<>();
        params.put("ids",ids);
        return super.findAll(MAPPER_NAMESPACE+".selectForCart",params);
    }

    /**
     * 根据ID查询商品信息
     * @param id
     * @return
     */
    public Deal getById(Long id) {
        if (null == id) {
            return new Deal();
        }
        return this.findOne(MAPPER_NAMESPACE + ".selectByPrimaryKey", id);
    }

    /**
     * 根据SkuId获取存在的商品记录
     * @param skuId
     * @return
     */
    public Deal findBySkuId(Long skuId) {
        return findOne(MAPPER_NAMESPACE + ".selectBySkuId", skuId);
    }


    /**
     * 根据SkuId获取存在的商品ID
     * @param skuId
     * @return
     */
    public Long getIdBySkuId(Long skuId) {
        return super.count(MAPPER_NAMESPACE + ".selectIdBySkuId", skuId);
    }

    /**
     * 根据skuId查询可以显示在前台的商品
     * @param skuId
     * @return
     */
    public Deal getBySkuIdForShowOnView(Long skuId) {
        return findOne(MAPPER_NAMESPACE + ".selectBySkuIdForShowOnView", skuId);
    }

    /**
     * 根据类别查询商品
     * @param categoryId
     * @return
     */
    public List<Deal> getByCategoryId(Long categoryId) {
        return findAll(MAPPER_NAMESPACE + ".selectByCategoryId", categoryId);
    }

    /**
     * 根据类别查询商品数量
     * @param categoryId
     * @return
     */
    public long countByCategoryId(Long categoryId) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);
        return count(MAPPER_NAMESPACE + ".countByCategoryId", params);
    }

    /**
     * 根据商家编码查询商品
     * @param merchantCode
     * @return
     */
    public Deal getByMerchantCode(String merchantCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("merchantCode", merchantCode);
        return findOne(MAPPER_NAMESPACE + ".selectByMerchantCode", params);
    }

    /**
     * 查询最新发布的商品
     * @return
     */
    public Deal getLatestPublishedDeal() {
        return findOne(MAPPER_NAMESPACE + ".selectLatestPublishedDeal");
    }

    /**
     * 根据条件分页查询商品信息
     * @param search
     * @return
     */
    public PagingResult<Deal> getPageDeals(Search search) {
        return this.findForPage(MAPPER_NAMESPACE + ".countPageDeals", MAPPER_NAMESPACE + ".selectPageDeals", search);
    }

    public PagingResult<Deal> getPageDeals(int page,int rows,Map<String,Object> params) {
        return this.findForPage(MAPPER_NAMESPACE + ".countPageDeals", MAPPER_NAMESPACE + ".selectPageDeals",page,rows, params);
    }

    /**
     * 更新商品信息
     * @param deal
     * @return
     */
    public int updateById(Deal deal) {
        return this.update(MAPPER_NAMESPACE + ".updateById", deal);
    }

    /**
     * 设置已卖光和可售
     * @param deal
     * @return
     */
    public int modifyOosStatusById(Deal deal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", deal.getId());
        params.put("oosStatus", deal.getOosStatus());
        params.put("updateTime", new Date());
        return this.update(MAPPER_NAMESPACE + ".modifyOosStatusById", params);
    }

    /**
     * 下单减库存
     * @param id
     * @param inventoryAmount 更新库存数量
     * @param vendibilityAmount 更新可购买数量
     * @return
     */
    public int updateForPlaceOrder(Long id, int inventoryAmount, int vendibilityAmount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("inventoryAmount", inventoryAmount);
        params.put("vendibilityAmount", vendibilityAmount);
        return update(MAPPER_NAMESPACE + ".updateForPlaceOrder", params);
    }

    /**
     * 保存商品信息
     * @param deal
     * @return
     */
    public int saveDeal(Deal deal) {
        return this.save(MAPPER_NAMESPACE + ".insertDeal", deal);
    }






    /*************************** 补充 ************************/

    /**
     * 根据一组SkuId获取存在的商品记录
     * @param skuIds
     * @return
     */
    public List<Deal> findBySkuIds(List<Long> skuIds) {
        Map<String, Object> params = new HashMap<>();
        params.put("skuIds", skuIds);
        return findAll(MAPPER_NAMESPACE + ".selectBySkuIds", params);
    }

    /**
     * 查询SkuId最大的商品
     * @return
     */
    public Deal getMaxSkuId() {
        return findOne(MAPPER_NAMESPACE + ".selectMaxSkuId");
    }


    /**
     * 获取要下单的商品信息
     * @param id
     * @return
     */
    public Deal getInfoForPlaceOrder(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return findOne(MAPPER_NAMESPACE + ".selectForPlaceOrder", params);
    }

}
