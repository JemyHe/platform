package com.xingxue.class11.service.impl;


import com.sun.javafx.collections.MappingChange;
import com.xingxue.class11.constants.DealConstant;
import com.xingxue.class11.constants.ExceptionContants;
import com.xingxue.class11.dao.DealCategoryDAO;
import com.xingxue.class11.dao.cache.DealCategoryCacheOperator;
import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;
import com.xingxue.class11.service.DealCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/7/6.
 */
@Service
public class DealCategoryServiceImpl implements DealCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(DealCategoryServiceImpl.class);

    @Autowired
    private DealCategoryDAO dealCategoryDAO;

    @Autowired
    private DealCategoryCacheOperator dealCategoryCacheOperator;

    @Override
    public DealCategory getByUrlName(String urlName) throws DealException,BaseException{
        try {
            if(StringUtils.isEmpty(urlName)){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            DealCategory byUrlName = dealCategoryDAO.getByUrlName(urlName);
            //TODO:缓存位置
            List<DealCategory> all = dealCategoryCacheOperator.getAllDealCategories();
            if(CollectionUtils.isEmpty(all)){
                all = dealCategoryDAO.getAllWithoutDeleted();
                for (DealCategory dealCategory:all){
                    dealCategoryCacheOperator.putDealCategory(dealCategory);
                }
            }
            bulidSubs(byUrlName,all);
            return byUrlName;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public List<DealCategory> getCategories() throws BaseException{


        try {
            //查询出所有的商品分类;
            List<DealCategory> all = dealCategoryCacheOperator.getAllDealCategories();
            if(CollectionUtils.isEmpty(all)){
                all = dealCategoryDAO.getAllWithoutDeleted();
                for (DealCategory dealCategory:all){
                    dealCategoryCacheOperator.putDealCategory(dealCategory);
                }
            }
            //用java代码调整数据格式
            //1.找出所有的第一级大类//2.找出所有的子孙分类
            List<DealCategory> roots = new ArrayList<>();
            List<DealCategory> subs = new ArrayList<>();
            for(DealCategory root:all){
                if(root.getParentId() == 0){
                    roots.add(root);
                }else{
                    subs.add(root);
                }
            }
            //3.把第一级分类的children（子类别）填充上
            for(DealCategory root:roots){
                //拿到所有一级分类，把其对应的子类别填充到children属性上
                bulidSubs(root,subs);
            }

            return roots;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public PagingResult<DealCategory> getCategoriesByPage(int rows, int page, Map<String,Object> params) throws DealException,BaseException{
        try {
            if(rows <= 0 || page <= 0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            PagingResult<DealCategory> dataForPage = dealCategoryDAO.findDataForPage(page, rows, params);
            return dataForPage;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public PagingResult<DealCategory> getCategoriesByPageWithSearch(Search search) throws DealException,BaseException{
        try {
            if(search == null || search.getRows() <=0|| search.getPage() <=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            PagingResult<DealCategory> dataForPage = dealCategoryDAO.findDataForPage(search);
            return dataForPage;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public DealCategory getCategoryById(Long categoryId) throws DealException,BaseException{
        try {
            if (categoryId == null || categoryId <= 0) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            //TODO 缓存位置
            DealCategory dealCategory = dealCategoryCacheOperator.getDealCategory(categoryId);
            if(dealCategory == null){
                dealCategory = dealCategoryDAO.getById(categoryId);
                dealCategoryCacheOperator.putDealCategory(dealCategory);
            }
            return dealCategory;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int saveCategory(DealCategory category) throws DealException,BaseException{

        try {
            //TODO 应该判断所有非空字段的值
            if(category == null){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            category.setCreateTime(new Date());
            int save = dealCategoryDAO.save(category);
            //TODO 缓存位置
            if(save == 1){
                dealCategoryCacheOperator.putDealCategory(category);
            }
            return save;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int updateCategoryById(DealCategory category) throws DealException,BaseException{
        try {
            if(category == null || category.getId() == null || category.getId() <=0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            int update =  dealCategoryDAO.updateById(category);
            //TODO 缓存位置
            if(update == 1){
                dealCategoryCacheOperator.putDealCategory(dealCategoryDAO.getById(category.getId()));
            }
            return update;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int deleteCategoryById(Long categoryId) throws DealException,BaseException{
        try {
            if(categoryId == null || categoryId <= 0){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            DealCategory category = new DealCategory();
            category.setId(categoryId);
            category.setPublishStatus(DealConstant.DEAL_PUBLISH_STATUS_DELETED);
            int delete = dealCategoryDAO.updateById(category);
            //TODO 缓存位置
            if(delete == 1){
                dealCategoryCacheOperator.deleteDealCategory(categoryId);
            }
            return delete;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public DealCategory getParentCategory(Long categoryId) throws DealException,BaseException{
        try {
            if (categoryId <= 0) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            //TODO 缓存位置
            DealCategory category = getCategoryById(categoryId);
            return getCategoryById(category.getParentId());
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public List<DealCategory> getSubCategories(Long categoryId) throws DealException,BaseException{
        try {
            if (categoryId <= 0) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            //TODO 缓存位置
            List<DealCategory> subs = dealCategoryCacheOperator.getSubCategories(categoryId);
            if(CollectionUtils.isEmpty(subs)){
                subs = dealCategoryDAO.getDirectSubs(categoryId);
                dealCategoryCacheOperator.putSubCategories(categoryId,subs);
            }
            return subs;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public List<DealCategory> getLaterGenerationCategories(Long categoryId) throws DealException,BaseException{
        try {
            if (categoryId <= 0) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            List<DealCategory> categories = new ArrayList<>();
            List<DealCategory> subCategories = getSubCategories(categoryId);
            for (DealCategory category : subCategories) {
                categories.add(category);
                categories.addAll(getLaterGenerationCategories(category.getId()));
            }
            return categories;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public List<DealCategory> getAncestorCategories(Long categoryId) throws DealException,BaseException{
        try {
            if (categoryId <= 0) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            List<DealCategory> categories = new ArrayList<>();
            DealCategory parentCategory = getParentCategory(categoryId);
            if (parentCategory != null) {
                categories.add(parentCategory);
                categories.addAll(getAncestorCategories(parentCategory.getId()));
            }
            return categories;
        } catch (DealException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 构建树形结构
     * @param parent
     * @param subs
     */
    public void bulidSubs(DealCategory parent,List<DealCategory> subs){
        List<DealCategory> children = new ArrayList<>();
        for(DealCategory sub:subs){
            //子类的parentid如果等于父类的id，说明其属于该父类
            if(parent.getId() == sub.getParentId()){
                children.add(sub);
            }
        }
        if(!CollectionUtils.isEmpty(children)){
            parent.setChildren(children);
            for(DealCategory root:children){
                bulidSubs(root,subs);
            }
        }
    }
}
