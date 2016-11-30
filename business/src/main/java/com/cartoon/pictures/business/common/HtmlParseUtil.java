package com.cartoon.pictures.business.common;


import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
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
            cardInfo.setRemoteUrl(Constants.DOMAIN+aEle.attr("href").trim());

            List<GifInfo> oGifInfos = new ArrayList<>();
            Elements lis = ele.getElementsByTag("li");
            for (Element liEle :lis) {
                Element img = liEle.getElementsByTag("img").get(0);
                GifInfo gifInfo = new GifInfo();
                gifInfo.setRemoteUrl(Constants.DOMAIN+img.attr("src").trim());
                gifInfo.setDes(img.attr("alt").trim());
                oGifInfos.add(gifInfo);
            }
            cardInfo.setGifInfos(oGifInfos);
            cardInfos.add(cardInfo);
        }
        return cardInfos;
    }
}
