package com.xingxue.class11.service.impl;

import com.xingxue.class11.constants.DealConstant;
import com.xingxue.class11.dao.AddressDAO;
import com.xingxue.class11.entity.Address;

import com.xingxue.class11.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * AddressService
 */
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired private AddressDAO addressDAO;

    /**********************网站**************************/



    /**********************后台**************************/



    /**********************混用**************************/
    /**
     * 保存
     * @param address
     */
    public void save(Address address) {
        Date now = new Date();
        address.setCreateTime(now);
        address.setUpdateTime(now);
        this.addressDAO.save(address);
    }

    /**
     * 根据用户ID查询全部
     * @return
     */
    public List<Address> getByUserId(Long userId) {
        return this.addressDAO.findByUserId(userId);
    }

    /**
     * 根据ID查询
     * @return
     */
    public Address getById(Long id) {
        return this.addressDAO.findById(id);
    }


    /**
     * 设置默认地址
     * @param id
     */
    public void updateType(Integer id,Long userId){
        Address address = new Address();
        address.setId(Long.valueOf(id));
        address.setType(DealConstant.DEFAULT_ADDRESS);
        address.setUpdateTime(new Date());
        addressDAO.updateAllType(userId);
        addressDAO.updateAddress(address);
    }

    /**
     * 删除地址
     * @param id
     */
    public void deleteAddress(Integer id){
        Address address = new Address();
        address.setId(Long.valueOf(id));
        Address byId = addressDAO.findById(Long.valueOf(id));
        if(byId!=null){
            addressDAO.deleteAddress(id);
        }
    }

    /**
     * 分页查询
     * @param search
     * @return
     */
    /*public PagingResult<Address> getPage(Search search) {
        return this.addressDAO.findPage(search);
    }*/



}
