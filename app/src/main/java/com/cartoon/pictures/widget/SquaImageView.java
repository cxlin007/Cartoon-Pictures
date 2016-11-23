package com.cartoon.pictures.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by chenxunlin01 on 2016/11/22.
 */
public class SquaImageView  extends ImageView{
    public SquaImageView(Context context) {
        super(context);
    }

    public SquaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
