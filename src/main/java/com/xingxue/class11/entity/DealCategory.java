package com.xingxue.class11.entity;

import com.xingxue.class11.framework.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Deal分类
 */
public class DealCategory extends BaseEntity {

    private Long parentId; // 父类别ID

    private String name; // 类别名称

    private String urlName; // 类别Url名称

    private Integer publishStatus; // 发布状态

    private Date createTime; // 创建时间

    private Integer orderNum; // 排序序号

    private Integer deep; // 层级深度

    private List<DealCategory> children;

    /**
     * 取出所有子孙分类的id和自身id
     * @return
     */
    public List<Long> getSelfAndChildrenIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(this.getId());
        if(!CollectionUtils.isEmpty(children)){
            ids.addAll(getIdList(children));
            for(DealCategory dealCategory:children){
                getSub(dealCategory.getChildren(),ids);
            }
        }
        return ids;
    }

    private void getSub(List<DealCategory> list, List<Long> ids) {
        if (null != list && !list.isEmpty()) {
            ids.addAll(getIdList(list));
            for(DealCategory dealCategory:list){
                getSub(dealCategory.getChildren(),ids);
            }
        }
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public List<DealCategory> getChildren() {
        return children;
    }

    public void setChildren(List<DealCategory> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DealCategory{" +
                "parentId=" + parentId +
                ", name='" + name + '\'' +
                ", urlName='" + urlName + '\'' +
                ", publishStatus=" + publishStatus +
                ", createTime=" + createTime +
                ", orderNum=" + orderNum +
                ", deep=" + deep +
                ", children=" + children +
                '}';
    }




}
