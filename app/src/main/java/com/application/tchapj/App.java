package com.application.tchapj;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.bean.HomeBean;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.di.component.AppComponent;
import com.application.tchapj.di.module.AppModule;
import com.application.tchapj.http.APIService;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.bean.StartInitiationDataModel;
import com.application.tchapj.my.service.LocationService;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mobstat.StatService;
import com.king.thread.nevercrash.NeverCrash;
import com.mob.MobSDK;
import com.socks.library.KLog;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.litepal.LitePalApplication;

import java.util.LinkedList;
import java.util.List;

import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;
import static com.application.tchapj.utils2.SDToast.mHandler;

// 程序的基本配置
public class App extends Application {

    public static IWXAPI mWxApi;

    // 程序版本更新ID
    private static final String BUGLY_ID  = "4604451393";

    private AppComponent mAppComponent; // Dagger2依赖注入框架App的接口类对象

    public static HomeBean.HomeData homeData;

    public static String UseToken; // 公用的用户请求参数


    public static String KF; // 客服电话
    public static String Type; // 用户类型
    public static String MyKF; // 我的页面客服电话
    public static String MyID; // 我的页面用户ID
    public static String UseEmail; // 公用的用户请求参数
    public static String SEX; // 公用的用户请求参数
    public static int SubmitState; // 公用的用户请求参数
    public static String PackageName;


    public static String NickName; // 用户类型

    public static List<String> titles;//循环队列

    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列

    public static String Update;

    public static String QiniuToken;

    public static String RSA2_PRIVATE; // 用户支付宝私钥
    public static String authCode; // 用户支付宝权限码

    private List<Activity> activityList=new LinkedList<Activity>();

    private static App instance;
    public static String ordernumber;
/*
    public static String TaskApplyId; // 任务认证id
    public static String LingTaskStatus ="0"; // 达人资料身份认证
    public static String DyState; // 任务认证id
    public static String PyqState; // 任务认证id
    public static String WbState; // 任务认证id
    public static String WsState; // 微视达人认证id
    public static String OtherState; // 其他达人认证id（达人认证的其他媒体资源）*/

    public static String alipay; // 支付宝状态0:未绑定 1:已绑定

    public static String pinglunID;

    public static int money;

    public static List<HomeCircleModel.HomeCircleModelResult.HomeCircle> circles;

    private static Context mContext;

    public static String nickName; // 公用的用户请求参数

    private static SharedPreferencesUtils shared;
    public LocationService locationService;

    public static SharedPreferencesUtils getShared() {
        return shared;
    }

    //单例
    public static App getInstance(){
        return App.AppInstance.INSTANCE;
    }

    private static class AppInstance{
        private static final App INSTANCE = new App();
    }



    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(base);
//        Beta.installTinker();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        shared = new SharedPreferencesUtils(this,"Login");
        //百度sdk初始化
        baiduMapSdkInit();
        //极光推送初始化
        //http://124.133.43.12:8084/whby-api3/app?method=pm.mthoad.aTask&pageSize=10&appKey=002&v=1.0&sign=&format=JSON  发送通知
        JPushInterface.setDebugMode(Constants.IS_DEBUG);
        JPushInterface.init(this);
//        setStyleCustom();

        KLog.init(Constants.IS_DEBUG);//初始化KLog
        LitePalApplication.initialize(getApplicationContext());//初始化litePal

        // 调试时，将第三个参数改为true
        Bugly.init(this,BUGLY_ID,Constants.IS_DEBUG);
        MobSDK.init(this);

        // 接口的根路径
      //  mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this,Constants.BASE_URL2)).build();

        mAppComponent = new AppModule(this);

        // 问题整理
        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                CrashReport.postCatchedException(e);
            }
        });

        // 侧滑返回管理器
        //BGASwipeBackManager.getInstance().init(this);

        DataManager.init(this);

        registToWX();

        /*//初始化logger
        LogUtils.getLogConfig()
                //.configAllowLog(false)
                .configAllowLog(true)                    //是否允许日志输出
                .configTagPrefix("微呼百应")          //日志log的前缀
                .configShowBorders(true)                 //是否显示边界
                .configLevel(LogLevel.TYPE_VERBOSE);     //日志显示等级*/

        /*StatService.autoTrace(this);*/
        StatService.start(this);//百度统计

        getStartInitiationData();


        App.setId(getDataManager().quickGetMetaData(R.string.id,String.class));

        SharedPreferences.getInstance().init(this);

    }

    /**
     * 初始化百度地图sdk
     */
    private void baiduMapSdkInit() {
        // 初始化定位sdk，
        locationService = new LocationService(mContext);
        SDKInitializer.initialize(mContext);
    }

    /**
     * 获取初始化接口数据
     */
    private void getStartInitiationData() {
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getStartInitiation(APIService.APP_KEY,APIService.V,APIService.FORMAT, APIService.OS_TYPE) //得到闪屏页数据
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<StartInitiationDataModel>>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override // 得到数据
                    public void onNext(BaseBean<StartInitiationDataModel> baseBean) {
                        if(baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null){
                            SharedPreferencesUtils.getInstance().setStartInitiationData(baseBean.getData());
                        }

                    }
                });
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    // 添加Activity 到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity 并finish
    public void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    // 微信注册
    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, AppConst.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(AppConst.WEIXIN_APP_ID);
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }


    /**
     * //屏幕高
    public int getScreenHeight(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    //屏幕宽
    public int getScreenWidth(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }*/


    public static String getId() {
        return getDataManager().quickGetMetaData(R.string.id,String.class);

    }

    public static void setId(String id) {
        UserInfo userInfo = SharedPreferencesUtils.getInstance().getUserInfo();
        userInfo.setId(id);
        SharedPreferencesUtils.getInstance().setUserInfo(userInfo);
    }


    public static String getUserName() {
        return SharedPreferencesUtils.getInstance().getUserInfo().getName();
    }

    public static String getNickName() {
        return SharedPreferencesUtils.getInstance().getUserInfo().getNickName();
    }

    public static String getHeadImgUrl() {
        return SharedPreferencesUtils.getInstance().getUserInfo().getHeadimgurl();
    }
    public static String getSex() {
        return SharedPreferencesUtils.getInstance().getUserInfo().getSex();
    }

    public static String getMedia() {
        return SharedPreferencesUtils.getInstance().getUserInfo().getMedia();
    }

    /**
     * 设置通知栏样式 - 定义通知栏Layout
     */
    private void setStyleCustom() {
        //large_icon (目前只支持本地图)传网络图片时，不能超过 30k；传本地资源路径时，只需要填文件名称，不需要任何前缀后缀
        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this, R.layout.layout_notitfication, R.id.layout_notification_iv, R.id.layout_notification_title, R.id.layout_notification_content);
        JPushInterface.setPushNotificationBuilder(2, builder);
//
//        builder.statusBarDrawable = R.mipmap.ic_launcher; // 指定最顶层状态栏小图标
//        builder.layoutIconDrawable = R.mipmap.ic_launcher;// 指定下拉状态栏时显示的通知图标
        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }
}
