package com.cartoon.pictures.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.cartoon.pictures.R;
import com.cartoon.pictures.adapters.CategoryAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.tabpageindicator.TabPageIndicator;

import java.util.List;

public class CategoryActivity extends BaseActivity implements CartoonPicturesController.CartoonPicturesCategoryUi {

    private CartoonPicturesController.CartoonPicturesUiCallbacks mCallbacks;
    private TabPageIndicator mTabPageIndicator;
    private ViewPager viewPager;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        viewPager = (ViewPager) findViewById(R.id.tab_layout_viewpager);
        categoryAdapter = new CategoryAdapter(getSupportFragmentManager());
        viewPager.setAdapter(categoryAdapter);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.tab_page_indicator_nav_view_ind);
        mTabPageIndicator.setHorizontalFadingEdgeEnabled(true);
        mTabPageIndicator.setViewPager(viewPager);

        TextView title = (TextView) findViewById(R.id.tool_title);
        title.setText(getCardInfo().getTitle());
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
    public void setData(List<CategoryInfo> data) {
        categoryAdapter.setData(data);
        mTabPageIndicator.notifyDataSetChanged();
    }

    @Override
    public CardInfo getCardInfo() {
        return (CardInfo) getIntent().getSerializableExtra("info");
    }

    @Override
    public void setCallbacks(CartoonPicturesController.CartoonPicturesUiCallbacks callbacks) {
        mCallbacks = callbacks;
    }
}
