package com.xingxue.class11.dao;


import com.xingxue.class11.entity.Address;
import com.xingxue.class11.framework.dao.BaseMyBatisDAO;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地址
 */
@Repository
public class AddressDAO extends BaseMyBatisDAO{

    private final String MAPPER_NAMESPACE = "com.xingxue.class11.entity.mapper.AddressMapper";

    /**
     * 保存
     * @param address
     */
    public void save(Address address) {
        super.save(MAPPER_NAMESPACE + ".insertSelective", address);
    }

    /**
     * 查询全部
     * @return
     */
    public List<Address> findByUserId(Long userId) {
        return super.findAll(MAPPER_NAMESPACE + ".selectByUserId", userId);
    }

    /**
     * 查询
     * @return
     */
    public Address findById(Long id) {
        return super.findOne(MAPPER_NAMESPACE + ".selectById", id);
    }


    /**
     * 更新收货地址
     * @param address
     */
    public void updateAddress(Address address){
        super.update(MAPPER_NAMESPACE+".updateAddress",address);
    }

    /**
     * 删除收货地址
     * @param id
     */
    public void deleteAddress(Integer id){
        super.delete(MAPPER_NAMESPACE+".deleteAddress",id);
    }

    /**
     * 更新所有默认地址为0
     */
    public void updateAllType(Long userId){
       super.update(MAPPER_NAMESPACE+".updateAllType",userId);
    }

    /**
     * 分页查询
     * @param search
     * @return
     */
   /* public PagingResult<Address> findPage(Search search) {
        return super.findForPage(MAPPER_NAMESPACE + ".countPage", MAPPER_NAMESPACE + ".selectPage", search);
    }*/

}
