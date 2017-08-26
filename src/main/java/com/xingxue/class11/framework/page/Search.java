package com.xingxue.class11.framework.page;

import com.sun.javafx.collections.MappingChange;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Search {

    private int page;

    private int rows;

    private List<Condition> params;

    public int getFirstRows(){
        return (page-1)*rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Condition> getParams() {
        return params;
    }

    public void setParams(List<Condition> params) {
        this.params = params;
    }

    public Search(int page, int rows, List<Condition> params) {
        this.page = page;
        this.rows = rows;
        this.params = params;
    }
}
