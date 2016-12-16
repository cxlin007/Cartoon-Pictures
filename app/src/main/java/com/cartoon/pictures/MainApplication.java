package com.cartoon.pictures;

import android.app.Application;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.common.Constants;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.downloader.DownloaderManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class MainApplication extends Application{

    private CartoonPicturesController cartoonPicturesController;

    public static final String DOWNLOADER_DIR = "cartoon-download";

    @Override
    public void onCreate() {
        super.onCreate();
        cartoonPicturesController = new CartoonPicturesController();
        initConfig();
        initData();
    }

    public CartoonPicturesController getCartoonPicturesController() {
        return cartoonPicturesController;
    }

    private void initConfig() {
        DownloaderManager.instance().setDownloaderDir(DOWNLOADER_DIR);
    }


    private void initData() {
        //初始化数据
        HashMap<String, List<CategoryInfo>> categoryMap = new HashMap<>();
        List<CategoryInfo> categoryInfos1 = new ArrayList<>();
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setDes("真人搞笑");
        categoryInfo.setKey("/ka/zr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("动物");
        categoryInfo.setKey("/ka/dw/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("静态爆笑");
        categoryInfo.setKey("/ka/bx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("小孩");
        categoryInfo.setKey("/ka/xh/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("卡通");
        categoryInfo.setKey("/ka/kt/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("动态趣图");
        categoryInfo.setKey("/ka/qw/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("影视");
        categoryInfo.setKey("/ka/dy/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("体育");
        categoryInfo.setKey("/ka/ty/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("脸部表情");
        categoryInfo.setKey("/ka/bq/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("调皮");
        categoryInfo.setKey("/ka/tp/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("怪相");
        categoryInfo.setKey("/ka/wp/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("搞笑动作");
        categoryInfo.setKey("/ka/dz/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("拜神");
        categoryInfo.setKey("/ka/qs/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("跳舞");
        categoryInfo.setKey("/ka/tw/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("做梦幻想");
        categoryInfo.setKey("/ka/hx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/ka/", categoryInfos1);

//-----------------------------------------------------------------------

        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("进群报到");
        categoryInfo.setKey("/ql/bd/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("问候欢迎");
        categoryInfo.setKey("/ql/hy/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("出来聊天");
        categoryInfo.setKey("/ql/cl/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("路过巡逻");
        categoryInfo.setKey("/ql/lg/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("群主图片");
        categoryInfo.setKey("/ql/qz/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("群员搞笑");
        categoryInfo.setKey("/ql/qy/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("交友征婚");
        categoryInfo.setKey("/ql/jy/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("找人叫人");
        categoryInfo.setKey("/ql/zr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("潜水隐身");
        categoryInfo.setKey("/ql/qs/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("楼上楼下");
        categoryInfo.setKey("/ql/ls/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("公告通知");
        categoryInfo.setKey("/ql/gg/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("下线闪人");
        categoryInfo.setKey("/ql/sr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/ql/", categoryInfos1);

//        -----------------------------------------------------

        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("暴力残忍");
        categoryInfo.setKey("/dm/bl/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("鄙视打击");
        categoryInfo.setKey("/dm/bs/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("仇恨气愤");
        categoryInfo.setKey("/dm/ch/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("教训惩罚");
        categoryInfo.setKey("/dm/cf/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("打人揍人");
        categoryInfo.setKey("/dm/dr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("骂人损人");
        categoryInfo.setKey("/dm/mr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("整人耍人");
        categoryInfo.setKey("/dm/zr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("狂妄傲慢");
        categoryInfo.setKey("/dm/am/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("威胁恐吓");
        categoryInfo.setKey("/dm/wx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("声明警告");
        categoryInfo.setKey("/dm/jg/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("挑衅气人");
        categoryInfo.setKey("/dm/tx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("劝解劝导");
        categoryInfo.setKey("/dm/qj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/dm/", categoryInfos1);

//        -------------------------------------------------
        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("上线来了");
        categoryInfo.setKey("/sx/sl/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("喝茶咖啡");
        categoryInfo.setKey("/sx/hc/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("握手友好");
        categoryInfo.setKey("/sx/ws/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("工作学习");
        categoryInfo.setKey("/sx/gz/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("忙碌困累");
        categoryInfo.setKey("/sx/ml/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("聊天说话");
        categoryInfo.setKey("/sx/lt/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("告别送别");
        categoryInfo.setKey("/sx/gb/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("早午晚好");
        categoryInfo.setKey("/sx/zw/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("问候招呼");
        categoryInfo.setKey("/sx/wh/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("睡觉晚安");
        categoryInfo.setKey("/sx/sj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("挽留难舍");
        categoryInfo.setKey("/sx/wl/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("下线拜拜");
        categoryInfo.setKey("/sx/xx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/sx/", categoryInfos1);

//        --------------------------------------------------------------

        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("浪漫情侣");
        categoryInfo.setKey("/love/ql/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("求爱表白");
        categoryInfo.setKey("/love/qa/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("性感诱人");
        categoryInfo.setKey("/love/xg/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("色男色女");
        categoryInfo.setKey("/love/sn/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("色眼色相");
        categoryInfo.setKey("/love/sy/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("放电媚眼");
        categoryInfo.setKey("/love/my/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("亲热接吻");
        categoryInfo.setKey("/love/qw/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("亲密拥抱");
        categoryInfo.setKey("/love/yb/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("自恋臭美");
        categoryInfo.setKey("/love/cm/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("鲜花送花");
        categoryInfo.setKey("/love/xh/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("帅哥美女");
        categoryInfo.setKey("/love/mn/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("尴尬害羞");
        categoryInfo.setKey("/love/hx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/love/", categoryInfos1);

//        --------------------------------------

        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("开心高兴");
        categoryInfo.setKey("/xq/kx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("疑问");
        categoryInfo.setKey("/xq/sk/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("郁闷心烦");
        categoryInfo.setKey("/xq/ym/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("同意");
        categoryInfo.setKey("/xq/ty/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("反对");
        categoryInfo.setKey("/xq/ky/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("偷笑大笑");
        categoryInfo.setKey("/xq/sx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("生气");
        categoryInfo.setKey("/xq/sq/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("发火");
        categoryInfo.setKey("/xq/dn/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("伤心流泪");
        categoryInfo.setKey("/xq/wq/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("表扬");
        categoryInfo.setKey("/xq/by/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("道歉");
        categoryInfo.setKey("/xq/rc/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("害怕惊讶");
        categoryInfo.setKey("/xq/hp/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("晕倒");
        categoryInfo.setKey("/xq/yd/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("呕吐");
        categoryInfo.setKey("/xq/ot/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("感谢拜托");
        categoryInfo.setKey("/xq/gx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/xq/", categoryInfos1);

//        --------------------------------------

        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("祝福朋友");
        categoryInfo.setKey("/zf/py/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("友谊友情");
        categoryInfo.setKey("/zf/yq/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("互踩互访");
        categoryInfo.setKey("/zf/hc/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("抢发红包");
        categoryInfo.setKey("/zf/fc/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("加油鼓励");
        categoryInfo.setKey("/zf/jy/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("顶帖灌水");
        categoryInfo.setKey("/zf/dt/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("相识缘份");
        categoryInfo.setKey("/zf/yf/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("结婚祝福");
        categoryInfo.setKey("/zf/jh/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("周末愉快");
        categoryInfo.setKey("/zf/zm/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("生日蛋糕");
        categoryInfo.setKey("/zf/sr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("思念想你");
        categoryInfo.setKey("/zf/xn/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("关心安慰");
        categoryInfo.setKey("/zf/gx/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/zf/", categoryInfos1);
//        -------------------
        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("公章证书");
        categoryInfo.setKey("/qt/zs/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("漫画");
        categoryInfo.setKey("/qt/mh/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("新奇");
        categoryInfo.setKey("/qt/qr/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("接打电话");
        categoryInfo.setKey("/qt/dh/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("炒股");
        categoryInfo.setKey("/qt/gp/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("动漫人物");
        categoryInfo.setKey("/qt/dm/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("名车");
        categoryInfo.setKey("/qt/mc/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("恐怖");
        categoryInfo.setKey("/qt/kb/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("搞笑食物");
        categoryInfo.setKey("/qt/su/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("水果");
        categoryInfo.setKey("/qt/sg/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("文字");
        categoryInfo.setKey("/qt/xt/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("风景图片");
        categoryInfo.setKey("/qt/fj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("喝酒");
        categoryInfo.setKey("/qt/hj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);


        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("吃饭美食");
        categoryInfo.setKey("/qt/ms/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/qt/", categoryInfos1);

//        ---------------------------

        categoryInfos1 = new ArrayList<>();
        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("元旦节");
        categoryInfo.setKey("/jr/ydj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("春节");
        categoryInfo.setKey("/jr/cj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("情人节");
        categoryInfo.setKey("/jr/qrj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("元宵节");
        categoryInfo.setKey("/jr/yxj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("愚人节");
        categoryInfo.setKey("/jr/yrj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("动漫人物");
        categoryInfo.setKey("/qt/dm/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("劳动节");
        categoryInfo.setKey("/jr/ldj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("母亲节");
        categoryInfo.setKey("/jr/mqj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("端午节");
        categoryInfo.setKey("/jr/dwj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("父亲节");
        categoryInfo.setKey("/jr/fqj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("七夕节");
        categoryInfo.setKey("/jr/qxj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("中秋");
        categoryInfo.setKey("/jr/zqj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("国庆");
        categoryInfo.setKey("/jr/gqj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);


        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("光棍节");
        categoryInfo.setKey("/jr/ggj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryInfo = new CategoryInfo();
        categoryInfo.setDes("圣诞节");
        categoryInfo.setKey("/jr/sdj/");
        categoryInfo.setRemoteUrl(Constants.DOMAIN + categoryInfo.getKey());
        categoryInfos1.add(categoryInfo);

        categoryMap.put("/jr/", categoryInfos1);

        BusinessManager.getCartoonPicturesState().setSuCategoryInfos(categoryMap);
    }
}
