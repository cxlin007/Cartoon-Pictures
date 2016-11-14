package com.cartoon.pictures.business.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public abstract class PageInfo<T> {

    private int currPage = -1;
    private int totalPage = 0;
    private List<T> data = new ArrayList<>();

    public boolean hasNextPage() {
        return currPage < (totalPage - 1);
    }

    public int nextPage() {
        if (currPage == -1 || hasNextPage()) {
            return currPage + 1;
        } else {
            return -1;
        }
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
    }
}
