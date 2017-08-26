package com.xingxue.class11.dao;

import com.xingxue.class11.entity.User;
import com.xingxue.class11.framework.dao.BaseMyBatisDAO;
import com.xingxue.class11.framework.entity.BaseEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/21.
 */
@Repository
public class UserDAO extends BaseMyBatisDAO{

    /**
     * MyBatis的Mapper映射文件命名空间
     */
    private static final String MAPPER_NAMESPACE = "com.xingxue.class11.entity.mapper.UserMapper";


    /**
     * 通过ID获取用户
     * @param id
     * @return
     */
    public User getById(Long id){
        return super.findOne(MAPPER_NAMESPACE + ".selectByPrimaryKey", id);
    }

    /**
     * 通过用户名查询用户
     * @param name 用户名
     * @return
     */
    public User getByName(String name) {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("name", name);
        return super.findOne(MAPPER_NAMESPACE + ".selectByName", params);
    }

    /**
     * 保存用户信息
     * @param user 用户
     * @return id
     */
    public int save(User user) {
        return super.save(MAPPER_NAMESPACE + ".insertSelective", user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateById(User user) {
        return super.update(MAPPER_NAMESPACE + ".updateByPrimaryKeySelective", user);
    }

    /**
     *
     * @param search
     * @return
     */
    /*public PagingResult<User> findUserForPage(Search search) {
        return this.findForPage(MAPPER_NAMESPACE + ".countPageUsers", MAPPER_NAMESPACE + ".selectPageUsers", search);
    }*/

}
