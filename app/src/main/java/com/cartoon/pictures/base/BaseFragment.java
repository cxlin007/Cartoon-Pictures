package com.cartoon.pictures.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartoon.pictures.AndroidDisplay;
import com.cartoon.pictures.MainApplication;
import com.catoon.corelibrary.controllers.BaseUiController;

/**
 * Created by chenxunlin01 on 2016/12/2.
 */
public abstract class BaseFragment extends Fragment {

    protected AndroidDisplay mDisplay;
    protected MainApplication imContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mDisplay = new AndroidDisplay(getActivity());
        imContext = (MainApplication) getActivity().getApplicationContext();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract BaseUiController getController();
}
