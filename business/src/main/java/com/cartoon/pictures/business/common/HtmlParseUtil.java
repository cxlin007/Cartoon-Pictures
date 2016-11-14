package com.cartoon.pictures.business.common;

import android.content.Intent;
import android.util.Log;

import com.cartoon.pictures.business.bean.ImageInfo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class HtmlParseUtil {

    public static List<ImageInfo> parseMainImageInfos(Document document) {
        List<ImageInfo> imgeInfos = new ArrayList<ImageInfo>();
        Elements imgs = document.getElementsByClass("img");
        for (Element element : imgs) {
            Elements ele = element.getElementsByTag("img");
            if (ele.size() > 0) {
                String src = ele.get(0).attr("src");
                imgeInfos.add(new ImageInfo(src));
            }
        }

        return imgeInfos;
    }

    public static int parseMainTotalPage(Document document) {
        Elements pages = document.getElementsByClass("page");
        Element pageEle = pages.get(0);
        String text = pageEle.text();
        int index = text.indexOf(" ");
        text = text.substring(5, index);
        return Integer.valueOf(text.replace(" ", ""));
    }
}
