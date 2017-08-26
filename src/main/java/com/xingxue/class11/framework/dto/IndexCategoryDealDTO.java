package com.xingxue.class11.framework.dto;

import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.DealCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页显示商品
 */
public class IndexCategoryDealDTO {

    private DealCategory category;
    private List<Deal> deals;

    public DealCategory getCategory() {
        return category;
    }

    public void setCategory(DealCategory category) {
        this.category = category;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public IndexCategoryDealDTO(DealCategory category, List<Deal> deals) {
        this.category = category;
        if (CollectionUtils.isEmpty(deals)) {
            this.deals = new ArrayList<>();
        }else {
            this.deals = new ArrayList<>(deals);
        }
    }

}