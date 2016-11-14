package com.catoon.corelibrary.bean;

import java.util.List;

/**
 * Created by chenxunlin01 on 2016/3/18.
 */
public abstract class PageResult<T> {

    public List<T> items;
    public int totalCount;

}
