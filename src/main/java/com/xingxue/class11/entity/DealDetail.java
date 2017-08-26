package com.xingxue.class11.entity;

import com.xingxue.class11.framework.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Deal详细信息
 */
public class DealDetail extends BaseEntity {

    private Long dealId;

    private String dealDetail; // 商品详细描述信息

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public String getDealDetail() {
        return dealDetail;
    }

    public void setDealDetail(String dealDetail) {
        this.dealDetail = dealDetail;
    }
}
