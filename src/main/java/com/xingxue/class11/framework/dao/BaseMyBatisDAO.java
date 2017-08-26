package com.xingxue.class11.framework.dao;

import com.xingxue.class11.framework.entity.BaseEntity;

import com.xingxue.class11.framework.page.Condition;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
public class BaseMyBatisDAO {

    @Autowired
    private SqlSessionTemplate template;

    /**
     * 查询所有 没有参数
     * @param sqlId
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> List<T> findAll(String sqlId){
        List<T> objects = template.selectList(sqlId);
        return objects;
    }

    /**
     * 查询所有 有Map参数
     * @param sqlId
     * @param params
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> List<T> findAll(String sqlId,Map<String,Object> params){
        List<T> objects = template.selectList(sqlId,params);
        return objects;
    }

    /**
     * 查询所有 只有一个参数
     * @param sqlId
     * @param param
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> List<T> findAll(String sqlId,Object param){
        List<T> objects = template.selectList(sqlId,param);
        return objects;
    }

    /**
     * 查询单个数据 没有参数
     * @param sqlId
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> T findOne(String sqlId){
        return template.selectOne(sqlId);
    }
    /**
     * 查询单个数据 只有一个参数
     * @param sqlId
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> T findOne(String sqlId,Object param){
        return template.selectOne(sqlId,param);
    }
    /**
     * 查询单个数据 map参数
     * @param sqlId
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> T findOne(String sqlId,Map<String,Object> params){
        return template.selectOne(sqlId,params);
    }

    /**
     *  返回数量
     *  1.查询一下购物车的商品数量
     *  2。查询XXXX的个数 count
     */
    public Long count(String sqlId,Map<String,Object> params){
        return template.selectOne(sqlId,params);
    }

    public Long count(String sqlId,Object param){
        return template.selectOne(sqlId,param);
    }

    /**
     * 单个插入
     * @param sqlId
     * @param entity
     * @param <T>
     * @return
     */
    public <T extends BaseEntity>int save(String sqlId,T entity){
        return template.insert(sqlId,entity);
    }

    /**
     * 批量插入
     * @param sqlId
     * @param entities
     * @param <T>
     * @return
     */
    public <T extends BaseEntity>int save(String sqlId,List<T> entities){
        return template.insert(sqlId,entities);
    }

    /**
     * 根据id去删除
     * @param sqlId
     * @param param
     * @return
     */
    public int delete(String sqlId,Object param){
        return template.delete(sqlId,param);
    }

    /**
     * 根据一组条件删除
     * @param sqlId
     * @param params
     * @return
     */
    public int delete(String sqlId,Map<String,Object> params){
        return template.delete(sqlId,params);
    }

    /**
     * 根据实体更新
     * @param sqlId
     * @param entity
     * @param <T>
     * @return
     */
    public <T extends BaseEntity>int update(String sqlId,T entity){
        return template.update(sqlId,entity);
    }

    /**
     * 根据参数更新
     * @param sqlId
     * @param param
     * @param <T>
     * @return
     */
    public <T extends BaseEntity>int update(String sqlId,Object param){
        return template.update(sqlId,param);
    }


    /**
     * 根据Map更新
     * @param sqlId
     * @param params
     * @param <T>
     * @return
     */
    public <T extends BaseEntity>int update(String sqlId,Map<String,Object> params){
        return template.update(sqlId,params);
    }

    /**
     * 根据条件分页查询
     * @param countId  查询总数量
     * @param sqlId  查询实体
     * @param page 当前页数
     * @param rows 一页显示多少条
     * @param params 可选条件
     * @param <T> 返回的实体类型
     * @return
     */
    public <T extends BaseEntity>PagingResult<T> findForPage(String countId,String sqlId,int page,int rows,Map<String,Object> params){
        //MyBatis分页查询,RowsBounds()
        //offset:当前页数开始的第一条数据的下标，从0开始
        // 1---->0   2---->10  3--->20
        // (page-1)*rows
        //limit:每个页面显示的条数
        RowBounds  rowBounds = new RowBounds((page-1)*rows,rows);
        List<T> data = template.selectList(sqlId, params, rowBounds);
        Long count = count(countId,params);

        return new PagingResult<T>(rows,page,count,data);
    }


    /**
     * 根据Search条件分页查询
     * @param countId  查询总数量
     * @param sqlId  查询实体
     * @param search  查询条件封装实体
     * @param <T> 返回的实体类型
     * @return
     */
    public <T extends BaseEntity>PagingResult<T> findForPage(String countId, String sqlId, Search search){
        //MyBatis分页查询,RowsBounds()
        //offset:当前页数开始的第一条数据的下标，从0开始
        // 1---->0   2---->10  3--->20
        // (page-1)*rows
        //limit:每个页面显示的条数
        RowBounds  rowBounds = new RowBounds(search.getFirstRows(),search.getRows());
        List<Condition> params = search.getParams();
        List<T> data = template.selectList(sqlId, getConditionMap(params), rowBounds);
        Long count = count(countId,getConditionMap(params));

        return new PagingResult<T>(search.getRows(),search.getPage(),count,data);
    }

    /**
     *  把参数list转换为参数map
     * @param list
     * @return
     */
    private Map<String,Object> getConditionMap(List<Condition> list){
        Map<String,Object> params = new HashMap<>();
        for(Condition condition:list){
            params.put(condition.getKey(),condition.getParam());
        }
        return params;
    }


    /**
     * 批量插入指定SQL的数据
     * @param sqlId	SQL语句ID
     * @param entities	要插入的实体集合
     * @return
     */
    public <T extends BaseEntity> int saveBatch(String sqlId, List<T> entities) {
        return template.insert(sqlId, entities);
    }


}
