package com.catoon.corelibrary.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/3/18.
 */
public abstract class PageResult<T> implements Serializable {

    public List<T> items;
    public int totalPage;
    public int currPage;

    public int nextPage() {
        return currPage + 1;
    }

    public boolean hasNextPage() {
        return !(currPage >= totalPage);
    }

}
