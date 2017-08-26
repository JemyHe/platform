package com.xingxue.class11.service;

import com.xingxue.class11.entity.User;
import com.xingxue.class11.entity.UserBasicInfo;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.UserException;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface UserService {

    /**
     * dao 数据库的交互
     * service ----》业务-----》用户的行为 ---》登录、注册、查询
     */

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user) throws UserException,BaseException ;

    /**
     * 注册
     * @param user
     * @return
     */
    User register(User user)throws UserException,BaseException;


    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User getByUsername(String username)throws UserException,BaseException;

    /**
     * 根据条件更新User
     * @param user
     * @return
     */
    int updateByIdSelective(User user);

    /**
     * 根据id查询用户基本信息
     * @param userId
     * @return
     */
    UserBasicInfo getBasicInfoByUserId(Long userId);

    /**
     * 根据条件更新用户基本信息
     * @param basicInfo
     * @return
     */
    int updateBasicInfo(UserBasicInfo basicInfo);

    /**
     * 插入用户基本信息
     * @param basicInfo
     * @return
     */
    int saveBasicInfo(UserBasicInfo basicInfo);




}
