package com.xingxue.class11.service.impl;

import com.xingxue.class11.constants.DealConstant;
import com.xingxue.class11.constants.ExceptionContants;
import com.xingxue.class11.dao.DealCategoryDAO;
import com.xingxue.class11.dao.DealDAO;
import com.xingxue.class11.dao.DealDetailDAO;
import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.DealDetail;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;
import com.xingxue.class11.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7.
 */
@Service
public class DealServiceImpl implements DealService {

    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    @Autowired
    private DealDAO dealDAO;

    @Autowired
    private DealDetailDAO dealDetailDAO;

    @Override
    public Deal getBySkuIdForDetailViewOnSite(Long skuId) throws DealException,BaseException{
        try {
            if(skuId<=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.getBySkuIdForShowOnView(skuId);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public List<Deal> getDealsForIndex(Long areaId, List<Long> categoryIds) throws BaseException{
        try {
            if(areaId <=0){
                areaId = 367L;
            }
            return dealDAO.selectOnIndex(categoryIds,areaId,DealConstant.DEAL_PUBLISH_STATUS_PUBLISH);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }

    }


    @Override
    public List<Deal> getDealsForCart(List<Long> dealIds) throws BaseException{
        try {
            List<Deal> result = dealDAO.selectForCart(dealIds);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public List<Deal> getByCategoryId(Long categoryId) throws DealException,BaseException{
        try {
            if(categoryId<=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.getByCategoryId(categoryId);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public long countByCategoryId(Long categoryId) throws DealException,BaseException {
        try {
            if(categoryId<=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.countByCategoryId(categoryId);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public PagingResult<Deal> getDealList(Search search) throws DealException,BaseException{
        try {
            if(search == null || search.getRows()<=0 || search.getPage()<=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            PagingResult<Deal> pageDeals = dealDAO.getPageDeals(search);
            return pageDeals;
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public PagingResult<Deal> searchByName(String name, int page, int rows) throws DealException,BaseException{
        try {
            if(StringUtils.isEmpty(name) || page <=0 || rows<=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            Map<String,Object> params = new HashMap<>();
            params.put("dealTitle",name);
            PagingResult<Deal> pageDeals = dealDAO.getPageDeals(page, rows, params);
            return pageDeals;
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int modifyStatusById(Deal deal) throws DealException,BaseException{
        try {
            if (null == deal || deal.getId() == null || deal.getId()<=0) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.updateById(deal);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int modifyOosStatusById(Deal deal)throws DealException,BaseException {
        try {
            if (null == deal || deal.getId() == null || deal.getId()<=0) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.modifyOosStatusById(deal);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public Deal getBySkuId(Long skuId) throws DealException,BaseException{
        try {
            if(skuId <=0 ){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.findBySkuId(skuId);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public List<Deal> getBySkuIds(List<Long> skuIds)throws DealException,BaseException {
        try {
            if(CollectionUtils.isEmpty(skuIds)){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.findBySkuIds(skuIds);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int reduceInventory(Deal deal, int amount) throws DealException,BaseException{
        try {
            if(deal == null || deal.getId() == null || deal.getId() <=0 || amount <=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return dealDAO.updateForPlaceOrder(deal.getId(), deal.getInventoryAmount() - amount, deal.getVendibilityAmount() - amount);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public Boolean verifyDealForOrder(Deal deal, int userHasBoughtAmount) throws DealException{
        // 订单商品信息非法
        if (deal == null) {
            throw new DealException(ExceptionContants.USER_DATA_ERROR);
        }
        // 库存不够
        if (deal.getInventoryAmount() == 0) {
            throw new DealException(ExceptionContants.DEAL_NOT_ENOUGH);
        }
        // 库存不够，已经售罄
        if (deal.getVendibilityAmount() == 0 || deal.getOosStatus() == 1) {
            throw new DealException(ExceptionContants.DEAL_OUT_SEAL);
        }
        // 超出最大可购买数量
        if (deal.getMaxPurchaseCount() <= userHasBoughtAmount) {
            throw new DealException(ExceptionContants.DEAL_OUT_MAXBUY);
        }
        return true;
    }

    @Override
    public DealDetail getDetailByDealId(Long dealId) throws DealException,BaseException {
        try {
            if(dealId == null ){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            DealDetail byDealId = dealDetailDAO.getByDealId(dealId);
            return byDealId;
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }
}
