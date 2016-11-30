package com.catoon.corelibrary.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.Collection;

/**
 * Created by chenxunlin01 on 2016/3/16.
 */
public class Utils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static boolean isEmptyStr(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }

        return false;
    }

    public static boolean isImg(String url) {
        int imgType = url.lastIndexOf(".");
        String imgTypeStr = url.substring(imgType);
        if (imgTypeStr.equals(".jpg") || imgTypeStr.equals(".jpeg") || imgTypeStr.equals(".png")) {
            return true;
        }

        return false;
    }

    public static void showBrower(Activity activity, String url) {
        if (Utils.isEmptyStr(url)) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean isGif(String url) {
        return url.endsWith(".gif");
    }

}
