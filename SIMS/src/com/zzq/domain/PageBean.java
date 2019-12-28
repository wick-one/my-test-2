package com.zzq.domain;

import java.util.List;

/**
 * 分页对象
 */
public class PageBean<T> {
    //总记录数
    private long totalCount;
    //总页数
    private int totalPageNum;
    //分页查询的一页数据
    private List<T> list;
    //当前的页面
    private int currentPageNum;
    //每页显示的行数
    private int rows;

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPageNum=" + totalPageNum +
                ", list=" + list +
                ", currentPageNum=" + currentPageNum +
                ", rows=" + rows +
                '}';
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
