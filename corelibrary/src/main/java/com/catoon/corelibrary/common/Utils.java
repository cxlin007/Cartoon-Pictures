package com.catoon.corelibrary.common;

import android.app.Activity;
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

    public static boolean isEmptyStr(String str){
        if(str == null || "".equals(str)){
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

}
