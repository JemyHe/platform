package com.xingxue.class11.service;

import com.xingxue.class11.entity.Address;
import com.xingxue.class11.entity.Cart;
import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.Order;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.OrderException;
import com.xingxue.class11.framework.entity.Pair;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public interface OrderService {

    /**
     * 保存一个订单以及订单商品信息
     * @param userId
     * @param cartDTOs
     * @param address
     * @param totalPrice
     * @param payType
     * @return
     * @throws OrderException
     * @throws BaseException
     */
    Long order(Long userId, List<Pair<Cart, Deal>> cartDTOs, Address address, Integer totalPrice, Integer payType) throws OrderException,BaseException;

    /**
     * 根据用户ID查询该用户的订单列表
     * @param userId	用户ID
     * @return	订单集合
     */
    List<Order> getOrderByUserId(Long userId) throws OrderException,BaseException;

    /**
     * 更新订单状态
     * @param orderId
     */
    void payed(long orderId);
}
