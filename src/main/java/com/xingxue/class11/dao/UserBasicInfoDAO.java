package com.xingxue.class11.dao;

import com.xingxue.class11.entity.UserBasicInfo;
import com.xingxue.class11.framework.dao.BaseMyBatisDAO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/21.
 */
@Repository
public class UserBasicInfoDAO extends BaseMyBatisDAO{


    /**
     * MyBatis的Mapper映射文件命名空间
     */
    private static final String MAPPER_NAMESPACE = "com.xingxue.class11.entity.mapper.UserBasicInfoMapper";


    /**
     * 通过ID获取用户基本信息
     * @param id
     * @return
     */
    public UserBasicInfo getById(Long id) {
        return super.findOne(MAPPER_NAMESPACE + ".selectByPrimaryKey", id);
    }

    /**
     * 保存用户基本信息
     * @param info 用户
     * @return id
     */
    public int save(UserBasicInfo info) {
        return super.save(MAPPER_NAMESPACE + ".insertSelective", info);
    }

    /**
     * 更新用户基本信息
     * @param info
     * @return
     */
    public int updateById(UserBasicInfo info) {
        return super.update(MAPPER_NAMESPACE + ".updateByPrimaryKeySelective", info);
    }

}
