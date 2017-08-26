package com.xingxue.class11.service;


import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.DealDetail;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;

import java.util.List;

public interface DealService {



    /**
     * 根据SkuID查询商品信息,用于在网站详情页显示
     * @param skuId skuId
     * @return 商品信息
    **/
    Deal getBySkuIdForDetailViewOnSite(Long skuId) throws DealException,BaseException;


    /**
     * 查询首页显示的商品
     * @param areaId
     * @param categoryIds
     * @return
    **/
    List<Deal> getDealsForIndex(Long areaId, List<Long> categoryIds) throws DealException,BaseException;


    /**
     * 查询在购物车显示的商品
     * @param dealIds 商品ID
     * @return
    **/

    List<Deal> getDealsForCart(List<Long> dealIds)throws DealException,BaseException;

    /**
     * 根据类别查询商品
     * @param categoryId
     * @return
     **/

    List<Deal> getByCategoryId(Long categoryId) throws DealException,BaseException;

    /**
     * 根据类别查询商品数量
     * @param categoryId
     * @return
     **/

    long countByCategoryId(Long categoryId) throws DealException,BaseException;


    /**
     * 根据条件查询商品列表
     * @param search
     * @return
    **/
    PagingResult<Deal> getDealList(Search search);

    /**
     * 搜索分页查询
     * @param name
     * @param page
     * @param rows
     * @return
     **/
    PagingResult<Deal> searchByName(String name, int page, int rows);


    /**
     * 根据商品id更新
     * @param deal
     * @return
    **/
    int modifyStatusById(Deal deal) throws DealException,BaseException;

    /**
     * 设置已卖光
     * @param deal
     * @return
    **/
    int modifyOosStatusById(Deal deal) throws DealException,BaseException;


    /**
     * 保存商品时检查是否已经存在
     * @return
    **/
    Deal getBySkuId(Long skuId) throws DealException,BaseException;

    /**
     * 根据一组商品skuid查询
     * @param skuIds
     * @return
     */
     List<Deal> getBySkuIds(List<Long> skuIds) throws DealException,BaseException;


    /**
     * 下单减库存
     * @param deal 商品信息
     * @param amount 删减数量
     * @return
    **/
    int reduceInventory(Deal deal, int amount) throws DealException,BaseException;

    /**
     * 下单时验证商品信息合法性
     * @param deal 商品信息
     * @return 0：合法，其他非法
    **/

    Boolean verifyDealForOrder(Deal deal, int userHasBoughtAmount) throws DealException;

    /**
     * 根据DealID查询商品详情
     * @param dealId DealId
     * @return 商品详情
     */
    public DealDetail getDetailByDealId(Long dealId) throws DealException,BaseException;


}
