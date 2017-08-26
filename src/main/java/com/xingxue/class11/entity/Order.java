package com.xingxue.class11.entity;

import com.xingxue.class11.framework.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Order
 */
public class Order extends BaseEntity {

    private Integer merchantOrderPrice;//商家订单价格

    private Long userId;//用户ID

    //1:待付款;2:已付款;3:待发货;4:配送中;5:完成;6:已取消;
    private Integer orderStatus;//订单状态

    private Integer totalPrice;//订单总价

    private Integer totalSettlementPrice;//订单结算总价

    private List<OrderDetail> orderDetails;//订单详细信息

    private String address;//收货地址

    private String receiver;//收货人

    private String phone;//收货联系电话

    private Date createTime;

    private Date updateTime;

    private Integer payType;//支付方式 1.微信;2.支付宝;3.货到付款

    public Integer getMerchantOrderPrice() {
        return merchantOrderPrice;
    }

    public void setMerchantOrderPrice(Integer merchantOrderPrice) {
        this.merchantOrderPrice = merchantOrderPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalSettlementPrice() {
        return totalSettlementPrice;
    }

    public void setTotalSettlementPrice(Integer totalSettlementPrice) {
        this.totalSettlementPrice = totalSettlementPrice;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
