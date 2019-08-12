package com.application.tchapj;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

// 常量类
public final class Constants {


    public static final String PHONE_LOGON_FRAGMENT_BROADCAST_NAME = "com.application.tchapj.phone.logon.fragment.BROADCAST.NAME";
    public static String deviceId = "";
    public static final String LOGIN_STATE = "";
    public static final String LOGIN_NAME = "user_name";
    public static final String LOGIN_PWD = "user_pwd";


    public static final String DEBUG_TAG = "AndroidPicker";// LogCat的标记
    public static final boolean IS_DEBUG = true;

    // 正式库
//    public static final String BASE_URL2 = "http://124.133.43.12:8081/whby-api/";

    // 测试库
    // public static final String BASE_URL2 = "http://124.133.43.12:8084/whby-api3/";
    public static final String BASE_URL2 = "http://192.168.15.149:8080/whby-api/";


    //public static final String BASE_URL2 = "http://192.168.0.91:8080/whby-api/";//测试库，文文电脑IP

//    public static final String BASE_URL2 = "http://192.168.0.108:8080/whby-api/";//测试库，郭老师电脑IP


    //h5链接正式地址
    public static final String h5zhengshiUrl = "http://wx.whby.ctrl.cn";

    //Debug: h5链接测试地址
    public static final String h5zhengshiUrl2 = "http://124.133.43.12:8087";

    //缓存值
    public static final String SERVER_MOBILE = "SERVER_MOBILE"; //客服手机号
    public static final String USER_ID = "id"; //用户id
    public static final String HOME_TYPE = "type"; //客户类型

    public static final String TOKEN = "TOKEN";
    public static String APPKEY = "appKey";//用用程序的key
    public static String APPKEY_VALUE = "002";//用用程序的key
    public static String SECRET = "secret";//密匙
    public static String SECRET_VALUE = "abc";//密匙
    public static String VERSION = "v";//版本
    public static String VERSION_VALUE = "1.0";//版本
    public static String FORMAT = "format";//输出格式 JSON
    public static String FORMAT_VALUE = "JSON";//输出格式 JSON
    /**
     * 1:android  2:IOS  3:web
     */
    public static String TYPE = "type";//1:android  2:IOS  3:web
    /**
     * 1:android  2:IOS  3:web
     */
    public static String TYPE_VALUE = "1";//1:android  2:IOS  3:web
    /**
     * 方法名
     */
    public static String METHOD = "method";//方法名

    /**
     * 微呼百应号运营协议
     */
    public static String ACCOUNT_NUMBER_AGREEMENT = "https://docs.qq.com/doc/DVWtpV2xVb3lHREJj";

    /**
     * 我发的任务详情页H5地址
     */
    public static String MY_RELEASE_TASK_DETAIL_URL = "http://api.whby.ctrl.cn/weihubaiying/tubiao.html?memberId=";

    /**
     * 系统级参数
     */
    public static Map<String, String> getSystemParams() {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.APPKEY, Constants.APPKEY_VALUE);
        //params.put(ConstantsData.SECRET, ConstantsData.SECRET_VALUE);
        params.put(Constants.VERSION, Constants.VERSION_VALUE);
        params.put(Constants.FORMAT, Constants.FORMAT_VALUE);
        params.put(Constants.TYPE, Constants.TYPE_VALUE);
        return params;
    }

    /**
     * 已选中频道的json
     */
    public static String SELECTED_CHANNEL_JSON = "selectedChannelJson";
    /**
     * w未选频道的json
     */
    public static String UNSELECTED_CHANNEL_JSON = "unselectChannelJson";

    /**
     * 频道对应的请求参数
     */
    public static final String CHANNEL_CODE = "channelCode";
    public static final String IS_VIDEO_LIST = "isVideoList";

    public static final String ARTICLE_GENRE_VIDEO = "video";
    public static final String ARTICLE_GENRE_AD = "ad";

    public static final String TAG_MOVIE = "video_movie";

    public static final String URL_VIDEO = "/video/urls/v/1/toutiao/mp4/%s?r=%s";

    /**
     * 获取评论列表每页的数目
     */
    public static final int COMMENT_PAGE_SIZE = 20;

    public static final String DATA_SELECTED = "dataSelected";
    public static final String DATA_UNSELECTED = "dataUnselected";

    public static final String user_name = "user_name";
    public static final String user_code = "user_code";

    public static final String task_squareinfo_iv = "task_squareinfo_iv";
    public static final String task_squareinfo_tv = "task_squareinfo_tv";
    public static final String task_squareinfo_vip = "task_squareinfo_vip";
    public static final String task_squareinfo_tvip = "task_squareinfo_tvip";
    public static final String start_time_tv = "start_time_tv";
    public static final String start_end_tv = "start_end_tv";
    public static final String crowd_fl = "crowd_fl";
    public static final String channel_tv = "channel_tv";
    public static final String guidance_tv = "guidance_tv";
    public static final String requirements_tv = "requirements_tv";
    public static final String money_iv = "money_iv";
    public static final String money_tv = "money_tv";
    public static final String conditions_tv = "conditions_tv";

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    public static final String AuthCode = "AuthCode"; //

    public static final String Task_ApplyId = "TaskApplyId"; //

    public static final String RSA2_PRIVATE = "RSA2_PRIVATE"; // 私钥

    public static final String WEISHI_PACKAGE_NAME = "com.tencent.weishi";


}
