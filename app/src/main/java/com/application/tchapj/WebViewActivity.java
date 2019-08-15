package com.application.tchapj;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.login.activity.LoginMainActivity;
import com.application.tchapj.main.bean.FlashScreenBean;
import com.application.tchapj.utils2.AppManager;
import com.application.tchapj.utils2.ProgressActivity;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.net.NetChangeObserver;
import com.application.tchapj.utils2.net.NetWorkUtil;
import com.application.tchapj.utils2.net.NetworkStateReceiver;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.OnekeyShare;
import com.application.tchapj.widiget.LogUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import io.reactivex.annotations.Nullable;

// WebView
public class WebViewActivity extends BaseActvity implements IWXAPIEventHandler, NetChangeObserver {


    @BindView(R.id.toolbar_share_title)
    TextView title;         // 标题
    //    @BindView(R.id.iv_back)
//    ImageView iv_bback;     // 返回控件
    @BindView(R.id.toolbar)
    Toolbar toolbar; // 标题栏布局控件
    @BindView(R.id.toolbar_share_img)
    ImageView toolbarShareIv;


    private static final int REQUESTCODE = 8;

    protected AgentWeb mAgentWeb;              // AgentWeb 是一个高度封装的 WebView
    private String userInfo;                   // 传递的接口参数
    private ProgressActivity progressActivity; // 显示加载Layout
    private long mExitTime;                    // 得到更新时间
    /**
     * 传递的数据
     */
    public static String URL_KEY = "URL_KEY";
    public static String TITLE = "TITLE";
    public static String URL_TYPE = "URL_TYPE";
    public static String SHARE_CONTENT = "SHARE_CONTENT";
    public static String SHARE_SMALL_IMG = "SHARE_SMALL_IMG";
    public static String SHOW_SHARE = "SHOW_SHARE";
    public static String SHOW_TITLE_BAR = "SHOW_TITLE_BAR";

    public static final String head = "https:";

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
        }
    }; // 回调管理器

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            LogUtils.d("BaseWebActivity onPageStarted");
        }
    }; // WebView对象

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }
    }; // WebChrome对象
    private String title_name;//标题
    private String shareContent;//分享内容
    private String loadUrl;//传递的url
    private String shareSmallImg;//分享缩略图
    private boolean showShareBol = true;//是否显示分享按钮
    private boolean showTitleBarBol = false;//是否显示标题栏


    @Override // 得到传递的参数
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        userInfo = intent.getStringExtra(URL_KEY);
        LogUtils.d("info=" + userInfo);

        // 无参数调用
        //mAgentWeb.getJsEntraceAccess().quickCallJs("userLogin('" +userInfo+"')");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this); // 绑定注释的控件

        initView();

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    // 初始化页面
    private void initView() {

        Intent intent = getIntent();
        shareContent = intent.getStringExtra(SHARE_CONTENT);
        loadUrl = intent.getStringExtra(URL_KEY);
        shareSmallImg = intent.getStringExtra(SHARE_SMALL_IMG);
        title_name = intent.getStringExtra(TITLE);
        showShareBol = intent.getBooleanExtra(SHOW_SHARE, true);
        showTitleBarBol = intent.getBooleanExtra(SHOW_TITLE_BAR, false);

        if (!TextUtils.isEmpty(title_name)) {
            title.setText(title_name);
        }

        if (showTitleBarBol) {
            //如果showTitleBar == true就算title_name没有值也显示标题栏
            toolbar.setVisibility(View.VISIBLE);
        } else {
            //否则就根据title_name是否有值判断展示
            if (!TextUtils.isEmpty(title_name)) {
                toolbar.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }


        if (showShareBol) {
            toolbarShareIv.setVisibility(View.VISIBLE);
        } else {
            toolbarShareIv.setVisibility(View.GONE);
        }

        // 当前系统时间
        long p = System.currentTimeMillis();
        mExitTime = System.currentTimeMillis();// 更新mExitTime
        progressActivity = (ProgressActivity) findViewById(R.id.progress); // 进度条View

        // 网络状态服务器
        NetworkStateReceiver.registerNetworkStateReceiver(this);
        NetworkStateReceiver.registerObserver(this);

        // WebView 初始化
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(progressActivity, new RelativeLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(getUrl());

        long n = System.currentTimeMillis();
        LogUtils.d("Info", "init used time:" + (n - p)); // WebView后面的时间

        if (mAgentWeb != null) {
            //Android 端 ， AndroidInterface 是一个注入类 ，里面有一个无参数方法：callAndroid
            mAgentWeb.getJsInterfaceHolder()
                    .addJavaObject("jsObj", new AndroidInterface(mAgentWeb, this));
            // mAgentWeb.getJsInterfaceHolder().addJavaObject("androidProxy", new AndroidInterface(mAgentWeb, this));
        }

        // 得到微信意图
        App.mWxApi.handleIntent(getIntent(), this);
    }

    // 得到webView请求 URL 地址
    public String getUrl() {

        if (getIntent().getStringExtra(URL_TYPE) != null && getIntent().getStringExtra(URL_TYPE).equals("All")) {
            //不需要拼接url
            Log.e("H5连接路径：", "url===A" + getIntent().getStringExtra(URL_KEY));
            return getIntent().getStringExtra(URL_KEY);
        } else {
            Log.e("H5连接路径：", "url===B" + getIntent().getStringExtra(URL_KEY));
            return Constants.h5zhengshiUrl + getIntent().getStringExtra(URL_KEY);
        }


        //TODO 这是临时测试用的地址
        //  Log.e("DOAING", "http://192.168.15.70:8080/dist#/pages/famousDetail/famousDetail?id=" + getIntent().getStringExtra(URL_KEY) + "&memberId=" + App.getId() + "&type=1");
        // return "http://192.168.15.70:8080/dist#/pages/famousDetail/famousDetail?id=" + getIntent().getStringExtra(URL_KEY) + "&memberId=" + App.getId() + "&type=1";
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d("错误码 : " + resp.errCode + "");
        switch (resp.errCode) {
            // 发送成功
            case BaseResp.ErrCode.ERR_OK:
                // 获取code
                String code = ((SendAuth.Resp) resp).code;
                LogUtils.d("code=" + code);
                // 通过code获取授权口令access_token
//                getAccessToken(code);
                break;
        }
    }

    @Override // 连接
    public void onConnect(NetWorkUtil.NetType type) {
        progressActivity.showContent();
    }

    @Override // 断开连接
    public void onDisConnect() {
        progressActivity.showError2(ContextCompat.getDrawable(WebViewActivity.this, R.mipmap.no_net),
                "您的网络不在线，请稍后重试", "您的网络不在线，请稍后重试", "重新加载",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAgentWeb.getWebLifeCycle().onResume();
                    }
                });
    }

    // JavascriptInterface调用Android界面类
    class AndroidInterface {

        private Handler deliver = new Handler(Looper.getMainLooper());
        private AgentWeb agent;
        private Context context;

        public AndroidInterface(AgentWeb agent, Context context) {
            this.agent = agent;
            this.context = context;
        }


        @JavascriptInterface
        public void webLoginWithData() {
            setWXLogin();
        }

        @JavascriptInterface
        public void shareTitleContentImgUrlWebUrl(String shareTitle, String Content, String ImgUrl, String WebUrl, String id) {
            Log.e("分享路径：", "titlesss=" + shareTitle + ";Contentsss=" + Content + ";ImgUrlsss=" + ImgUrl + ";WebUrlsss=" + WebUrl + ";IDsss=" + id);
            showShare(shareTitle, Content, ImgUrl, WebUrl);


          /*  OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();

            // title标题，微信、QQ和QQ空间等平台使用
            oks.setTitle("测试");
            // titleUrl QQ和QQ空间跳转链接
            oks.setTitleUrl("http://sharesdk.cn");
            // text是分享文本，所有平台都需要这个字段
            oks.setText("我是分享文本");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url在微信、微博，Facebook等平台中使用
            oks.setUrl("http://sharesdk.cn");
            // comment是我对这条分享的评论，仅在人人网使用
            oks.setComment("我是测试评论文本");
            // 启动分享GUI
            oks.show(WebViewActivity.this);*/

        }

        @JavascriptInterface
        public void loginpage() {
          //  BindingPhoneActivity.start(WebViewActivity.this);
            finish();
        }


        // TODO: 2018/4/9 待完善
        @JavascriptInterface
        public void fanhui(String msg) {
            runOnUiThread(new TimerTask() {
                @Override
                public void run() {
                    if (!agent.back()) {
                        finish();
                    }
                }
            });

            /*runOnUiThread(() -> {
                if (!agent.back()) {
                    finish();
                }
            });*/

        }

        @JavascriptInterface
        public void fanhui1(String msg) {

            runOnUiThread(new TimerTask() {
                @Override
                public void run() {
                    finish();
                }
            });

            /*runOnUiThread(() -> {
                finish();
            });*/
        }

        @JavascriptInterface
        public void backMain() {
            finish();
            AppManager.getAppManager().finishAllActivity();
            startActivity(new Intent(WebViewActivity.this, MainActivity.class));
        }

        @JavascriptInterface
        public void renwu(String id) {
            String member = SharedPreferences.getInstance().getString("id", "");
            startWeb(H5UrlData.TASK_DETAIL + "&id=" + id + "&member=" + member, "");
        }


        /**
         * 拨打电话
         *
         * @param phone
         */
        @JavascriptInterface
        public void call2(String phone) {

            //检查权限
            if (ContextCompat.checkSelfPermission(WebViewActivity.this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(WebViewActivity.this,
                        Manifest.permission.CALL_PHONE)) {

                    new AlertDialog.Builder(WebViewActivity.this)
                            .setMessage("app需要开启权限才能使用此功能")
                            .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package:" + getPackageName()));
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create()
                            .show();
                } else {

                    //申请权限
                    ActivityCompat.requestPermissions(WebViewActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUESTCODE);

                    //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13345122570"));
                    if (ActivityCompat.checkSelfPermission(WebViewActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }

            } else {
                //已经拥有权限进行拨打
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13345122570"));
                if (ActivityCompat.checkSelfPermission(WebViewActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }

        }

    }

    // 微信登录
    private void setWXLogin() {
        IWXAPI api = WXAPIFactory.createWXAPI(this, AppConst.WEIXIN_APP_ID, true);
        api.registerApp(AppConst.WEIXIN_APP_ID);
        LogUtils.d("aaa1");
        if (api != null && api.isWXAppInstalled()) {
            LogUtils.d("aaa2");
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
            LogUtils.d("aaa3");
        } else {
            Toast.makeText(this, "您尚未安装微信", Toast.LENGTH_SHORT).show();
        }
    }


    // 显示分享
    private void showShare(String shareTitle, String Content, String ImgUrl, String WebUrl) {



        Log.e("DOAING",WebUrl);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用

        oks.setTitle(shareTitle);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(WebUrl);

        // text是分享文本，所有平台都需要这个字段
        oks.setText(Content);
        String im = ImgUrl;

        String[] line = im.split(":");

        im = head + line[1];

        oks.setImageUrl(im);
        oks.setSite(Content);
        oks.setSiteUrl(WebUrl);

        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(WebUrl);

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                Log.e("DOAING", "成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

                Log.e("DOAING", "错误信息：" + throwable.getCause().getMessage());
                Log.e("DOAING", "错误信息：" + throwable.getMessage());
                Log.e("DOAING", "错误信息：" + throwable.getLocalizedMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("DOAING", "取消");
            }
        });

        // 启动分享GUI
        oks.show(this);


    }

    // 分享的返回结果
    class OneKeyShareCallback implements PlatformActionListener {

        protected AgentWeb mAgentWeb;              // AgentWeb 是一个高度封装的 WebView

        @Override
        public void onComplete(Platform plat, int action,
                               HashMap res) {

            // 在这里添加分享成功的处理代码
            // 无参数调用
            mAgentWeb.getJsEntraceAccess().quickCallJs("shareSuccess");
        }

        @Override
        public void onError(Platform plat, int action, Throwable t) {
            t.printStackTrace();

            // 在这里添加分享失败的处理代码
            // 无参数调用
            mAgentWeb.getJsEntraceAccess().quickCallJs("shareError('" + "分享失败" + "')");
//            mAgentWeb.loadUrl("javascript:shareError('" +"分享失败"+"')");
        }

        @Override
        public void onCancel(Platform plat, int action) {
            // 在这里添加取消分享的处理代码

            Log.e("DOAING", "取消：");
        }
    }

    // Web页面跳转
    public void startWeb(String url, String title) {
        Intent intent = new Intent(WebViewActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL_KEY, url);
        intent.putExtra(WebViewActivity.TITLE, title);
        startActivity(intent);
    }


    @Override // 保存页面状态
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        LogUtils.d("result:" + requestCode + " result:" + resultCode);
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 状态栏返回键
    @OnClick({R.id.toolbar_share_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_share_img:
                showShare(title_name, shareContent, shareSmallImg, loadUrl);
                break;
            default:
                break;
        }
    }

    @Override // 键盘返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            if (!mAgentWeb.back()) {// true表示AgentWeb处理了该事件
                this.finish();
            }
//            showDialog();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override // 暂停
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override // 重启
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override // 销毁
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
        //mAgentWeb.destroy();
        NetworkStateReceiver.unregisterNetworkStateReceiver(this);
        NetworkStateReceiver.unregisterObserver(this);
        NetworkStateReceiver.close();
    }

    public static void startShare(Context context, String urlType, String url, String shareName, String shareContent, String shareSmallImg) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(WebViewActivity.URL_TYPE, urlType);
        starter.putExtra(WebViewActivity.URL_KEY, url);
        starter.putExtra(WebViewActivity.TITLE, shareName);
        starter.putExtra(WebViewActivity.SHARE_CONTENT, shareContent);
        starter.putExtra(WebViewActivity.SHARE_SMALL_IMG, shareSmallImg);
        context.startActivity(starter);
    }

    public static void start(Context context, String urlType, String url, boolean showShare, boolean showTitleBar) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(WebViewActivity.URL_TYPE, urlType);
        starter.putExtra(SHOW_SHARE, showShare);
        starter.putExtra(SHOW_TITLE_BAR, showTitleBar);
        if (!url.contains("http")) {
            url = "http://" + url;
        }

        starter.putExtra(WebViewActivity.URL_KEY, url);
        context.startActivity(starter);
    }

}
