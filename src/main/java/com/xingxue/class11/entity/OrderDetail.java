package com.xingxue.class11.entity;

import com.xingxue.class11.framework.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * OrderDetail
 */
public class OrderDetail extends BaseEntity {

    private Long orderId;//订单ID

    private Long userId;//用户ID

    private Integer merchantSku;//商品商家SKU

    private Long merchantId;

    private String merchantCode;//商家唯一商品品编码

    private Long dealId;

    private Long dealSkuId;

    private Long dealImgId;

    private String dealTitle;//商品名称

    private Integer dealCount;//Deal数量

    private Integer dealPrice;//Deal单价

    private Integer totalPrice;//Deal总价

    private Integer settlementPrice;//结算价

    private Integer totalSettlementPrice;//结算总价

    private Integer detailStatus;

    private Date createTime;

    private Date updateTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMerchantSku() {
        return merchantSku;
    }

    public void setMerchantSku(Integer merchantSku) {
        this.merchantSku = merchantSku;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public Long getDealSkuId() {
        return dealSkuId;
    }

    public void setDealSkuId(Long dealSkuId) {
        this.dealSkuId = dealSkuId;
    }

    public Long getDealImgId() {
        return dealImgId;
    }

    public void setDealImgId(Long dealImgId) {
        this.dealImgId = dealImgId;
    }

    public String getDealTitle() {
        return dealTitle;
    }

    public void setDealTitle(String dealTitle) {
        this.dealTitle = dealTitle;
    }

    public Integer getDealCount() {
        return dealCount;
    }

    public void setDealCount(Integer dealCount) {
        this.dealCount = dealCount;
    }

    public Integer getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(Integer dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(Integer settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public Integer getTotalSettlementPrice() {
        return totalSettlementPrice;
    }

    public void setTotalSettlementPrice(Integer totalSettlementPrice) {
        this.totalSettlementPrice = totalSettlementPrice;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
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
}
