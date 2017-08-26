package com.xingxue.class11.framework.page;

import com.xingxue.class11.framework.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public class PagingResult<T extends BaseEntity> {

    //每页有多少条数据
    private int rows;

    //当前在第几页
    private int page;

    //总数
    private long total;

    //返回数据
    private List<T> data;

    //显示多少页
    private int totalPage;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public PagingResult(int rows, int page, long total, List<T> data) {
        this.rows = rows;
        this.page = page;
        this.total = total;
        this.data = data;
        if(this.total%this.rows == 0){//21 4   ==== 5.12312
            this.totalPage = new Long(this.total).intValue()/this.rows;
        }else{
            this.totalPage = new Long(this.total).intValue()/this.rows+1;
        }
    }

    @Override
    public String toString() {
        return "PagingResult{" +
                "rows=" + rows +
                ", page=" + page +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
