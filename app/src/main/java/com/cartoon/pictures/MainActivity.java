package com.cartoon.pictures;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cartoon.pictures.adapters.CardAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.MProgressView;
import com.cartoon.pictures.widget.CommonListView;
import com.catoon.corelibrary.common.Utils;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends BaseActivity implements CartoonPicturesController.CartoonPicturesMainUi,
        MProgressView.MProgressViewLinstener {

    private CommonListView commonListView;
    private CardAdapter mainAdapter;
    private CartoonPicturesController.CartoonPicturesUiCallbacks mCallbacks;
    private int line = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        mainAdapter = new CardAdapter(this);
        commonListView = new CommonListView(this);
        commonListView.setDivider(new ColorDrawable(getResources().getColor(R.color.common_background)));
        commonListView.setDividerHeight(Utils.dip2px(this, line));
        commonListView.setListAdapter(mainAdapter).setMProgressViewLinstener(this);
        rootLayout.addView(commonListView.getRootContent());
    }

    @Override
    public void onResume() {
        super.onResume();
        getController().attachUi(this);
    }

    @Override
    public void onPause() {
        getController().detachUi(this);
        super.onPause();
    }

    @Override
    protected CartoonPicturesController getController() {
        return imContext.getCartoonPicturesController();
    }

    @Override
    public void setData(List<CardInfo> data) {
        mainAdapter.setData(data);
    }

    @Override
    public void showLoadingProgress(boolean visible) {
        if (!visible) {
            commonListView.setContentShown(false);
        } else {
            commonListView.setContentShown(true);
        }
    }

    @Override
    public void showError(Exception e) {
        commonListView.showErrorView();
    }

    @Override
    public void showSecondaryLoadingProgress(boolean visible) {
        commonListView.setSecondaryProgressShown(visible);
    }

    @Override
    public void showRefreshProgress(boolean successOrFail) {

    }

    @Override
    public void setCallbacks(CartoonPicturesController.CartoonPicturesUiCallbacks callbacks) {
        mCallbacks = callbacks;
        mainAdapter.setCallBack(callbacks);
    }

    @Override
    public void onErrorRetry() {
        mCallbacks.onErrorRetry();
    }


}
