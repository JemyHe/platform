package com.xingxue.class11.service;

import com.xingxue.class11.entity.Cart;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
public interface CartService {

    /**
     * 将Deal加入购物车
     * @param skuId 商品SkuId
     * @param userId 用户ID
     * @param count 数量
     */
    void addDeal(Long skuId, Long userId, Integer count) throws DealException,BaseException;

    /**
     * 查询购物车商品
     * @param userId
     * @return
     */
    List<Cart> getCartByUserId(Long userId)throws DealException,BaseException;

    /**
     * 查询购物车商品数量
     * @param userId
     * @return
     */
    Long getCartSize(Long userId) throws DealException,BaseException;

    /**
     * 购物车商品数量加1
     * @param cartId
     */
    void increaseCartDealCount(Long cartId) throws DealException,BaseException;

    /**
     * 购物车商品数量减1
     * @param cartId
     */
    void decreaseCartDealCount(Long cartId) throws DealException,BaseException;

    /**
     * 根据id集合查询
     * @param ids
     * @return
     */
    List<Cart> getCartsByIds(List<Long> ids) throws DealException,BaseException;


}
