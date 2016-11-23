package com.cartoon.pictures.uilibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.GridView;


/**
 * Created by chenxunlin01 on 2016/5/26.
 */
public class MGridView extends MListView{


    public MGridView(Context context) {
        super(context);
    }

    public MGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected AbsListView createListView(Context context) {
        return new GridView(context);
    }

    public void setColumn(int column){
        ((GridView)content).setNumColumns(column);
    }
}
