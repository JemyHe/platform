package com.xingxue.class11.test;

import com.xingxue.class11.entity.User;


/**
 * Created by Administrator on 2017/6/29.
 */
public interface UserTestDAO {

    public User selectByPrimaryKey(Long id);

    /**
     * 通过用户名查询用户
     * @param name 用户名
     * @return
     */
    public User selectByName(String name);

    /**
     * 保存用户信息
     * @param user 用户
     * @return id
     */
    public int insertSelective(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateByPrimaryKeySelective(User user);
}
