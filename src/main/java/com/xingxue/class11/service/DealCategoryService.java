package com.xingxue.class11.service;

import com.sun.javafx.collections.MappingChange;
import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/6.
 */
public interface DealCategoryService {

    /**
     * 根据urlName查询
     * @param urlName
     * @return
     **/
    DealCategory getByUrlName(String urlName) throws DealException,BaseException;


    /**
     * 查询所有分类
     * 返回给前台展示的商品分类的树形数据结构（三层）
     * @return
     */
    List<DealCategory> getCategories() throws BaseException;

    /**
     * 根据条件分页查询
     */
    PagingResult<DealCategory> getCategoriesByPage(int rows, int page, Map<String,Object> params) throws DealException,BaseException;

    /**
     * 根据条件分页查询
     */
    PagingResult<DealCategory> getCategoriesByPageWithSearch(Search search) throws DealException,BaseException;

    /**
     * 根据ID查询
     * @param categoryId
     * @return
     */
    DealCategory getCategoryById(Long categoryId) throws DealException,BaseException;

    /**
     * 添加商品类别
     * @param category 类别信息
     * @return
     **/
    int saveCategory(DealCategory category) throws DealException,BaseException;

    /**
     * 修改商品类别
     * @param category 类别信息
     * @return
     **/
    int updateCategoryById(DealCategory category) throws DealException,BaseException;

    /**
     * 删除商品类别 (逻辑删除)
     * @param categoryId 类别ID
     * @return
     **/
    int deleteCategoryById(Long categoryId) throws DealException,BaseException;

    /**
     * 获取商品父类别
     * @param categoryId 类别ID
     * @return
     **/
    DealCategory getParentCategory(Long categoryId) throws DealException,BaseException;

    /**
     * 获取某个商品类别的所有子类别
     * @param categoryId 商品类别ID
     * @return
     **/
    List<DealCategory> getSubCategories(Long categoryId) throws DealException,BaseException;

    /**
     * 获取某个商品类别的所有后代类别
     * @param categoryId 商品类别ID
     * @return
     **/
    List<DealCategory> getLaterGenerationCategories(Long categoryId) throws DealException,BaseException;

    /**
     * 获取某个商品类别所有祖先类别
     * @param categoryId
     * @return
     **/
    List<DealCategory> getAncestorCategories(Long categoryId) throws DealException,BaseException;


}
