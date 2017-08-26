package com.xingxue.class11.entity;

import com.xingxue.class11.framework.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * Deal即一个单品
 */
public class Deal extends BaseEntity {

     private Long areaId; // 地区ID

     private String areaName; // 地区

     private Long skuId; // skuID

     private Integer dealClass; // 是否虚拟商品

     private Long merchantId; // 商家ID

     private Integer merchantSku; // 商家SKU 编号

     private String dealTitle; // 商品标题

     private Integer dealPrice; // 商品价格

     private Integer merchantPrice; // 进货价

     private Integer marketPrice; // 市场价

     private Integer settlementPriceMax; // 最大可接受结算价格

     private Integer settlementPrice; // 结算价

     private Integer discount; // 折扣

     private Integer bonusPoints; // 积分

     private Integer dealType; // 商品类型

     private Long imageId; // 对应商品图片

     private String imageGenPath; // 对应商品图片路径

     private Integer dealLevel; // 商品优先级

     private Integer maxPurchaseCount; // 最大购买数量

     private Integer publishStatus; // 发布状态

     private Integer inventoryAmount; // 商品库存数量

     private Integer vendibilityAmount; // 商品可售数量

     private Integer oosStatus; // 是否售罄标识

     private Date startTime; // 商品销售开始时间

     private Date endTime; // 商品销售结束时间

     private Date publishTime; // 商品上架时间

     private String merchantCode; // 商品唯一编码

     private Date createTime; // 商品创建时间

     private Date updateTime; // 商品更新时间

     private DealDetail dealDetail;// 商品对应描述

     private Boolean downShelf; // 是否下架的标识

     private Integer categoryId; // 商品类别ID

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getDealClass() {
        return dealClass;
    }

    public void setDealClass(Integer dealClass) {
        this.dealClass = dealClass;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantSku() {
        return merchantSku;
    }

    public void setMerchantSku(Integer merchantSku) {
        this.merchantSku = merchantSku;
    }

    public String getDealTitle() {
        return dealTitle;
    }

    public void setDealTitle(String dealTitle) {
        this.dealTitle = dealTitle;
    }

    public Integer getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(Integer dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Integer getMerchantPrice() {
        return merchantPrice;
    }

    public void setMerchantPrice(Integer merchantPrice) {
        this.merchantPrice = merchantPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSettlementPriceMax() {
        return settlementPriceMax;
    }

    public void setSettlementPriceMax(Integer settlementPriceMax) {
        this.settlementPriceMax = settlementPriceMax;
    }

    public Integer getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(Integer settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageGenPath() {
        return imageGenPath;
    }

    public void setImageGenPath(String imageGenPath) {
        this.imageGenPath = imageGenPath;
    }

    public Integer getDealLevel() {
        return dealLevel;
    }

    public void setDealLevel(Integer dealLevel) {
        this.dealLevel = dealLevel;
    }

    public Integer getMaxPurchaseCount() {
        return maxPurchaseCount;
    }

    public void setMaxPurchaseCount(Integer maxPurchaseCount) {
        this.maxPurchaseCount = maxPurchaseCount;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(Integer inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    public Integer getVendibilityAmount() {
        return vendibilityAmount;
    }

    public void setVendibilityAmount(Integer vendibilityAmount) {
        this.vendibilityAmount = vendibilityAmount;
    }

    public Integer getOosStatus() {
        return oosStatus;
    }

    public void setOosStatus(Integer oosStatus) {
        this.oosStatus = oosStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
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

    public DealDetail getDealDetail() {
        return dealDetail;
    }

    public void setDealDetail(DealDetail dealDetail) {
        this.dealDetail = dealDetail;
    }

    public Boolean getDownShelf() {
        return downShelf;
    }

    public void setDownShelf(Boolean downShelf) {
        this.downShelf = downShelf;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", skuId=" + skuId +
                ", dealClass=" + dealClass +
                ", merchantId=" + merchantId +
                ", merchantSku=" + merchantSku +
                ", dealTitle='" + dealTitle + '\'' +
                ", dealPrice=" + dealPrice +
                ", merchantPrice=" + merchantPrice +
                ", marketPrice=" + marketPrice +
                ", settlementPriceMax=" + settlementPriceMax +
                ", settlementPrice=" + settlementPrice +
                ", discount=" + discount +
                ", bonusPoints=" + bonusPoints +
                ", dealType=" + dealType +
                ", imageId=" + imageId +
                ", imageGenPath='" + imageGenPath + '\'' +
                ", dealLevel=" + dealLevel +
                ", maxPurchaseCount=" + maxPurchaseCount +
                ", publishStatus=" + publishStatus +
                ", inventoryAmount=" + inventoryAmount +
                ", vendibilityAmount=" + vendibilityAmount +
                ", oosStatus=" + oosStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", publishTime=" + publishTime +
                ", merchantCode='" + merchantCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", dealDetail=" + dealDetail +
                ", downShelf=" + downShelf +
                ", categoryId=" + categoryId +
                '}';
    }
}