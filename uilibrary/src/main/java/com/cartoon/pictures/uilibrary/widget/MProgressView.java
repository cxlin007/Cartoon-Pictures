package com.cartoon.pictures.uilibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cartoon.pictures.uilibrary.R;

/**
 * 有进度条功能的视图
 * Created by chenxunlin01 on 2016/5/26.
 */
public class MProgressView extends FrameLayout {

    private View mProgressView;
    private View content;
    private TextView error;
    private MProgressViewLinstener progressViewLinstener;


    public MProgressView(Context context) {
        super(context);
        init(context);
    }

    public MProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

        if(getChildCount()>0){
            content = getChildAt(0);
        }else{
            View view = new View(context);
            view.setId(R.id.INTERNAL_PROGRESS_CONTAINER_ID);
            setContentView(view);
        }
        content.setVisibility(View.VISIBLE);

        mProgressView = LayoutInflater.from(context).inflate(R.layout.mui__loading_view,null);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        mProgressView.setVisibility(View.GONE);
        addView(mProgressView,lp);

        error = new TextView(context);
        error.setText(R.string.error_btn_text);
        error.setVisibility(View.GONE);
        LayoutParams errorLp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        addView(error,errorLp);
    }

    public void setContentView(View content){
        if(this.content!=null){
            removeView(this.content);
        }

        this.content =content;
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        addView(content,lp);
    }

    public void setContentShown(boolean shown){
        if(shown){
            mProgressView.setVisibility(View.GONE);
            error.setVisibility(GONE);
            content.setVisibility(View.VISIBLE);

        }else{
            mProgressView.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
            error.setVisibility(GONE);
        }
    }

    public void setProgressViewLinstener(MProgressViewLinstener progressViewLinstener) {
        this.progressViewLinstener = progressViewLinstener;
        if(progressViewLinstener!=null){
            error.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    MProgressView.this.progressViewLinstener.onErrorRetry();
                }
            });
        }
    }

    public void showErrorView(){
        mProgressView.setVisibility(View.GONE);
        error.setVisibility(VISIBLE);
        content.setVisibility(View.GONE);
    }

    public static interface MProgressViewLinstener{

        public void onErrorRetry();
    }

}
