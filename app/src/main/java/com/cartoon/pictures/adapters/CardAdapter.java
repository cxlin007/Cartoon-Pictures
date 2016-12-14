package com.cartoon.pictures.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.util.Util;
import com.cartoon.pictures.R;
import com.cartoon.pictures.base.CommonViewHolder;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.common.Utils;
import com.cartoon.pictures.widget.FixHeightGirdView;

import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class CardAdapter extends ABasisAdapter<CartoonPicturesController.CartoonPicturesUiCallbacks, CardInfo> {

    private Context mContext;

    public CardAdapter(Context context) {
        super(context, R.layout.card_item);
        this.mContext = context;
    }

    public void setData(List<CardInfo> data) {
        if (data != null) {
            this.data = data;
        }

        notifyDataSetChanged();
    }

    @Override
    protected void onBindViewHolder(CommonViewHolder holder, int position) {
        final CardInfo cardInfo = getItem(position);
        holder.setText(R.id.title, cardInfo.getTitle());
        holder.setOnClickListener(R.id.title_layout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onCardItemMoreClick(cardInfo);
            }
        });
        FixHeightGirdView gridLayout = (FixHeightGirdView) holder.getView(R.id.gridLayout);
        CardGridAdapter adapter = (CardGridAdapter) gridLayout.getAdapter();
        if (adapter == null) {
            adapter = new CardGridAdapter(mContext);
            gridLayout.setAdapter(adapter);
        }
        adapter.setCallBack(mCallBack);
        adapter.setData((List<GifInfo>) cardInfo.getGifInfos());
        ((ImageView) holder.getView(R.id.title_mark)).setBackgroundColor(mContext.getResources().getColor(R.color
                .colorPrimary));
        ImageView imageView = (ImageView) holder.getView(R.id.title_mark);
        imageView.setBackgroundColor(Utils.getMarkColor(position));
    }
}
