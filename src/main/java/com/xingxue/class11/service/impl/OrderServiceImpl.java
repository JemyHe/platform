package com.xingxue.class11.service.impl;

import com.xingxue.class11.constants.ExceptionContants;
import com.xingxue.class11.constants.OrderConstant;
import com.xingxue.class11.dao.OrderDAO;
import com.xingxue.class11.entity.*;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.OrderException;
import com.xingxue.class11.framework.entity.Pair;
import com.xingxue.class11.service.DealService;
import com.xingxue.class11.service.OrderService;
import com.xingxue.class11.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * OrderService
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired private OrderDAO orderDAO;

    @Autowired private DealService dealService;

    @Autowired private UserService userService;

    public Long order(Long userId, List<Pair<Cart, Deal>> cartDTOs, Address address, Integer totalPrice, Integer payType) throws OrderException,BaseException{
        try {
            if (null == userId) {
                throw new OrderException(ExceptionContants.USER_DATA_ERROR);
            }
            //构造Order对象
            Order order = initOrder(userId, totalPrice, address, payType, cartDTOs);
            if (null == order) {
                throw new OrderException(ExceptionContants.ORDER_EMPTY);
            }

            if (CollectionUtils.isEmpty(order.getOrderDetails())) {
                throw new OrderException(ExceptionContants.ORDER_DETAIL_EMPTY);
            }

            orderDAO.saveOrder(order);
            for (OrderDetail detail : order.getOrderDetails()) {
                if (null == detail) {
                    throw new OrderException(ExceptionContants.ORDER_DETAIL_EMPTY);
                }
                detail.setOrderId(order.getId());
            }
            orderDAO.saveOrderDetails(order.getOrderDetails());

            //TODO 将购物车中数据删除

            return order.getId();
        } catch (OrderException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 构造订单及订单详细信息
     * @param userId
     * @param totalPrice
     * @param address
     * @param payType
     * @param cartDTOs
     * @return
     */
    private Order initOrder(Long userId, Integer totalPrice, Address address, Integer payType,
                            List<Pair<Cart, Deal>> cartDTOs) throws BaseException{
        try {
            Date now = new Date();
            Order order = new Order();
            order.setUserId(userId);
            order.setTotalPrice(totalPrice);
            order.setOrderStatus(OrderConstant.STATUS_WAITING_PAY);
            order.setCreateTime(now);
            order.setUpdateTime(now);
            order.setReceiver(address.getReceiver());
            order.setAddress(address.getArea() + " " + address.getDetail());
            order.setPhone(address.getPhone());
            order.setPayType(payType);

            List<OrderDetail> details = new ArrayList<>();
            Integer totalSettlementPrice = 0;
            for (Pair<Cart, Deal> pair : cartDTOs) {
                OrderDetail detail = new OrderDetail();
                detail.setDealCount(pair.getHead().getCount());
                detail.setDealPrice(pair.getEnd().getDealPrice());
                detail.setDealTitle(pair.getEnd().getDealTitle());
                detail.setDealId(pair.getEnd().getId());
                detail.setDealSkuId(pair.getEnd().getSkuId());
                detail.setDealImgId(pair.getEnd().getImageId());
                detail.setDetailStatus(pair.getEnd().getPublishStatus());
                detail.setMerchantCode(pair.getEnd().getMerchantCode());
                detail.setCreateTime(now);
                detail.setUpdateTime(now);
                detail.setUserId(userId);
                detail.setSettlementPrice(pair.getEnd().getSettlementPrice());
                detail.setTotalSettlementPrice(pair.getEnd().getSettlementPrice() * detail.getDealCount());
                detail.setTotalPrice(detail.getDealPrice() * detail.getDealCount());
                totalSettlementPrice += detail.getTotalSettlementPrice();
                detail.setMerchantId(pair.getEnd().getMerchantId());
                details.add(detail);
            }
            order.setOrderDetails(details);
            order.setTotalSettlementPrice(totalSettlementPrice);
            return order;
        } catch (Exception e) {
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    public List<Order> getOrderByUserId(Long userId) throws OrderException,BaseException{
        try {
            if (0 == userId) {
                throw new OrderException(ExceptionContants.USER_DATA_ERROR);
            }
            List<Order> orders = this.orderDAO.getByUserId(userId);
            List<Long> orderIds = new ArrayList<>();
            for(Order order:orders){
                orderIds.add(order.getId());
            }
            List<OrderDetail> details = orderDAO.getOrderDetailsByOrderIds(orderIds);
            //构造Map<订单id，List<订单商品信息>>
            Map<Long, List<OrderDetail>> detailMap = new HashMap<>();
            for(OrderDetail detail:details){
                //如果map中不存在当前订单id，添加一项
                if(!detailMap.containsKey(detail.getOrderId())){
                    detailMap.put(detail.getOrderId(), new ArrayList<OrderDetail>());
                }
                //将每个订单商品信息添加到对应的map元素中
                detailMap.get(detail.getOrderId()).add(detail);
            }
            //将订单商品信息放入对应的订单实体中
            for(Order order:orders){
                order.setOrderDetails(detailMap.get(order.getId()));
            }
        /*List<Long> orderIds = orders.stream().map(order -> order.getId()).collect(Collectors.toList());
        List<OrderDetail> details = orderDAO.getOrderDetailsByOrderIds(orderIds);
        Map<Long, List<OrderDetail>> detailMap = new HashMap<>();
        details.forEach(detail -> {
            if (!detailMap.containsKey(detail.getOrderId())) {
                detailMap.put(detail.getOrderId(), new ArrayList<>());
            }
            detailMap.get(detail.getOrderId()).add(detail);
        });
        orders.forEach(order -> order.setOrderDetails(detailMap.get(order.getId())));*/
            return orders;
        } catch (OrderException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    public void payed(long orderId) {
        this.orderDAO.updateOrderStatus(orderId, OrderConstant.STATUS_WAITING_DELIVER);
    }

}