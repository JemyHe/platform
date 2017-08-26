package com.xingxue.class11.framework.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */
public class BaseEntity {

    //泛型 T

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 把一个User的集合中的主键id抽取出来
     * @param userList
     * @return
     */
    public static <T extends BaseEntity> List<Long> getIdList(List<T> userList){
        List<Long> idList = new ArrayList<Long>();
        for(T user:userList){
            idList.add(user.getId());
        }
        return idList;
        //java8
        /*idList.forEach(entity ->idList.add(entity.getId));*/
        /*idList.forEach(entity -> idList.add(getId()));*/
    }

    /**
     * 将一个User的集合转换成一个Map<主键，User>
     * @param userList
     * @return
     */
    public static <T extends BaseEntity> Map<Long,T> getEntityMap(List<T> userList){
        Map<Long,T> map = new HashMap<Long, T>();
        for (T user:userList){
             map.put(user.getId(), user);
        }
        return  map;
    }


}
