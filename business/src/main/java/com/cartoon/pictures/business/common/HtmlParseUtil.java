package com.cartoon.pictures.business.common;


import android.util.Log;

import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.EmotionInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.EmotionPageResult;
import com.cartoon.pictures.business.bean.GifPageResult;
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

    public static List<CardInfo> parseCardInfos(Document document) {
        List<CardInfo> cardInfos = new ArrayList<CardInfo>();
        //解析qq表情包数据
        Element photoList = document.getElementsByClass("a_photo_list").get(0);
        Elements thumbs = photoList.getElementsByClass("pe_u_thumb");
        CardInfo cardInfo = new CardInfo();
        cardInfo.setTitle("表情包");
        cardInfo.setKey("/zjbq/");
        cardInfo.setRemoteUrl(Constants.DOMAIN + cardInfo.getKey());
        int size = 3;
        int index = 0;
        List<EmotionInfo> emotionInfos = new ArrayList<EmotionInfo>();
        for (Element lel : thumbs) {
            if (index == size) {
                break;
            }
            Element aEle = lel.getElementsByTag("a").get(0);
            EmotionInfo emotionInfo = new EmotionInfo();
            emotionInfo.setKey(aEle.attr("href").trim());
            Element imgEle = lel.getElementsByTag("img").get(0);
            emotionInfo.setRemoteUrl(Constants.DOMAIN + imgEle.attr("src").trim());
            emotionInfo.setDes(imgEle.attr("alt").trim());
            emotionInfos.add(emotionInfo);
            index++;
        }
        cardInfo.setGifInfos(emotionInfos);
        cardInfos.add(cardInfo);

        //添加其他分类
        Elements eles = document.getElementsByClass("c_main_one");
        for (Element ele : eles) {
            cardInfo = new CardInfo();
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
        Elements liEles = listEle.getElementsByTag("dl").get(0).getElementsByTag("li");
        GifPageResult gifPageResult = new GifPageResult();
        List<GifInfo> gifs = new ArrayList<>();
        for (Element ele : liEles) {
            GifInfo gifInfo = new GifInfo();
            Element imgEle = ele.getElementsByTag("img").get(0);
            gifInfo.setDes(imgEle.attr("alt").trim());
            gifInfo.setRemoteUrl(Constants.DOMAIN + imgEle.attr("src".trim()));
            gifs.add(gifInfo);
        }
        gifPageResult.items = gifs;
        Element pageEle = document.getElementsByAttributeValue("style", "font-weight:bold;color:#ff3300").get(0);
        int total = Integer.valueOf(pageEle.text().trim());
        int totalPage = total / gifs.size();
        int yu = total % gifs.size();
        totalPage += (yu == 0 ? 0 : 1);
        gifPageResult.totalPage = totalPage;

        return gifPageResult;
    }

    /**
     * 解析表情包列表
     *
     * @param document
     * @return
     */
    public static EmotionPageResult parseEmotionList(Document document) {
        Element content = document.getElementsByClass("b_content").get(0);
        Elements liEles = content.getElementsByClass("pe_u_thumb");
        EmotionPageResult emotionPageResult = new EmotionPageResult();
        List<EmotionInfo> emotionInfos = new ArrayList<>();
        for (Element ele : liEles) {
            Element aEle = ele.getElementsByTag("a").get(0);
            EmotionInfo emotionInfo = new EmotionInfo();
            emotionInfo.setKey(aEle.attr("href").trim());
            Element imgEle = ele.getElementsByTag("img").get(0);
            emotionInfo.setRemoteUrl(Constants.DOMAIN + imgEle.attr("src").trim());
            emotionInfo.setDes(imgEle.attr("alt").trim());
            emotionInfos.add(emotionInfo);
        }
        emotionPageResult.items = emotionInfos;
        Element span = document.getElementsByClass("c_bot_fy").get(0);
        String text = span.text().trim();
        String totalStr = text.substring(text.indexOf("/") + 1, text.lastIndexOf("页")).trim();
        emotionPageResult.totalPage = Integer.valueOf(totalStr);

        return emotionPageResult;
    }

    /**
     * 解析表情包子列表
     *
     * @param document
     * @return
     */
    public static GifPageResult parseSuEmotionList(Document document) {
        Element fontzoom = document.getElementById("fontzoom");
        Elements liEles = fontzoom.getElementsByTag("img");
        GifPageResult gifPageResult = new GifPageResult();
        List<GifInfo> gifs = new ArrayList<>();
        for (Element ele : liEles) {
            GifInfo gifInfo = new GifInfo();
            gifInfo.setRemoteUrl(Constants.DOMAIN + ele.attr("src".trim()));
            gifs.add(gifInfo);
        }
        gifPageResult.items = gifs;
        try{
            Element span = document.getElementsByClass("c_bot_fy").get(0);
            String text = span.text().trim();
            String totalStr = text.substring(text.indexOf("/") + 1, text.lastIndexOf("页")).trim();
            gifPageResult.totalPage = Integer.valueOf(totalStr);
        }catch(Exception e){
            e.printStackTrace();
            gifPageResult.totalPage = 1;
        }
        return gifPageResult;
    }
}
