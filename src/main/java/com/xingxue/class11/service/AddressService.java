package com.xingxue.class11.service;


import com.xingxue.class11.entity.Address;

import java.util.List;

/**
 * AddressService
 */
public interface AddressService {


    /**
     * 保存
     */
    void save(Address address);

    /**
     * 根据用户ID查询全部
     * @return
     */
    List<Address> getByUserId(Long userId);
    /**
     * 根据ID查询
     * @return
     */
    Address getById(Long id);


    /**
     * 设置默认地址
     * @param id
     * @return
     */
    void updateType(Integer id,Long userId);

    /**
     * 删除地址
     * @param id
     * @return
     */
    void deleteAddress(Integer id);

    /**
     * 分页查询
     * @param search
     * @return
     */
    /*public PagingResult<Address> getPage(Search search);*/

}
