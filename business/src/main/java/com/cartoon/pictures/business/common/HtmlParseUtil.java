package com.cartoon.pictures.business.common;


import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.catoon.corelibrary.common.Utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class HtmlParseUtil {

    public static List<ImageInfo> parseMainImageInfos(Document document) {
        List<ImageInfo> imgeInfos = new ArrayList<ImageInfo>();
        Elements imgs = document.getElementsByClass("img");
        for (Element element : imgs) {
            ImageInfo imageInfo = new ImageInfo();
            Elements ele = element.getElementsByTag("img");
            if (ele.size() > 0) {
                String src = ele.get(0).attr("src").trim();
                imageInfo.setUrl(src);
            }
            ele = element.getElementsByTag("a");
            if (ele.size() > 0) {
                String url = ele.get(0).attr("href").trim();
                imageInfo.setDetailUrl(url);
            }
            imgeInfos.add(imageInfo);
        }

        return imgeInfos;
    }

    public static int parseMainTotalPage(Document document) {
        Elements pages = document.getElementsByClass("page");
        Element pageEle = pages.get(0);
        String text = pageEle.text();
        int endIndex = text.indexOf(" ");
        int startIndex = text.indexOf("/") + 1;
        text = text.substring(startIndex, endIndex);
        return Integer.valueOf(text.replace(" ", ""));
    }

    public static List<ImageDetailInfo> parseDetailImageInfos(Document document) {
        List<ImageDetailInfo> imgeInfos = new ArrayList<ImageDetailInfo>();
        Elements eles = document.getElementsByAttributeValue("style", "text-align: center");
        for (Element ele : eles) {
            Elements elements = ele.getElementsByTag("a");
            for (Element element : elements) {
                String href = element.attr("href").trim();
                String url = href.substring(href.indexOf("http")).trim();
                ImageDetailInfo imageDetailInfo = new ImageDetailInfo();
                imageDetailInfo.setUrl(url);
                imgeInfos.add(imageDetailInfo);
            }
        }
        return imgeInfos;
    }

    public static List<CardInfo> parseCardInfos(Document document) {
        List<CardInfo> cardInfos = new ArrayList<CardInfo>();
        //添加其他分类
        Elements eles = document.getElementsByClass("c_main_one");
        for (Element ele : eles) {
            CardInfo cardInfo = new CardInfo();
            Element titleEle = ele.getElementsByClass("childclass_title").get(0);
            Elements aEles = titleEle.getElementsByTag("a");
            Element aEle = aEles.get(aEles.size() - 1);
            cardInfo.setTitle(aEle.text().trim());
            String href = aEle.attr("href").trim();
            cardInfo.setKey(href);
            cardInfo.setRemoteUrl(Constants.DOMAIN + href);

            List<GifInfo> oGifInfos = new ArrayList<>();
            Elements lis = ele.getElementsByTag("li");
            for (Element liEle : lis) {
                Element img = liEle.getElementsByTag("img").get(0);
                GifInfo gifInfo = new GifInfo();
                gifInfo.setRemoteUrl(Constants.DOMAIN + img.attr("src").trim());
                gifInfo.setDes(img.attr("alt").trim());
                oGifInfos.add(gifInfo);
            }
            cardInfo.setGifInfos(oGifInfos);
            cardInfos.add(cardInfo);
        }
        return cardInfos;
    }

    public static void parseSuCategoryInfos(HashMap<String, List<CategoryInfo>>
                                                                                   hashMap, Document document) {
        Element menu = document.getElementById("menu_con");
        Elements menus = menu.getElementsByTag("ul");
        Element ele = menus.get(0);
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        Elements aEles = ele.getElementsByTag("a");
        String key = "";
        for (Element aEle : aEles) {
            CategoryInfo categoryInfo = new CategoryInfo();
            Element span = aEle.getElementsByTag("span").get(0);
            String href = aEle.attr("href").trim();
            if (Utils.isEmptyStr(key)) {
                key = href.substring(0, href.indexOf("/", 2) + 1);
            }
            categoryInfo.setKey(href);
            categoryInfo.setDes(span.text().trim());
            categoryInfo.setRemoteUrl(Constants.DOMAIN + href);
            categoryInfos.add(categoryInfo);
        }
        hashMap.put(key, categoryInfos);
    }

    public static GifPageResult parseGifInfoList(Document document) {
        Element listEle = document.getElementsByClass("p_class_list").get(0);
        Elements liEles = listEle.getElementsByTag("li");
        GifPageResult gifPageResult = new GifPageResult();
        List<GifInfo> gifs = new ArrayList<>();
        for (Element ele : liEles) {
            GifInfo gifInfo = new GifInfo();
            Element imgEle = ele.getElementsByTag("img").get(0);
            gifInfo.setDes(imgEle.attr("alt").trim());
            gifInfo.setDes(Constants.DOMAIN + imgEle.attr("src".trim()));
            gifs.add(gifInfo);
        }
        gifPageResult.items = gifs;
        Element pageEle = document.getElementById("pe100_page_infolist");
        gifPageResult.totalPage = Integer.valueOf(pageEle.getElementsByClass("font-weight:bold;color:#ff3300").get(0)
                .text().trim());


        Element menu = document.getElementById("menu_con");
        Elements menus = menu.getElementsByAttributeValue("style", "display: none");
        HashMap<String, List<CategoryInfo>> hashMap = new HashMap<>();
        for (int i = 2; i < menus.size(); i++) {
            Element ele = menus.get(i);
            List<CategoryInfo> categoryInfos = new ArrayList<>();
            Elements aEles = ele.getElementsByTag("a");
            String key = "";
            for (Element aEle : aEles) {
                CategoryInfo categoryInfo = new CategoryInfo();
                Element span = aEle.getElementsByTag("span").get(0);
                String href = aEle.attr("href").trim();
                if (Utils.isEmptyStr(key)) {
                    key = href.substring(0, href.indexOf("/", 2) + 1);
                }
                categoryInfo.setKey(href);
                categoryInfo.setDes(span.text().trim());
                categoryInfo.setRemoteUrl(Constants.DOMAIN + href);
                categoryInfos.add(categoryInfo);
            }
            hashMap.put(key, categoryInfos);
        }

        return gifPageResult;
    }
}
