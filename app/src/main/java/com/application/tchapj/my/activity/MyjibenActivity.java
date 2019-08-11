package com.application.tchapj.my.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.alipay.AuthResult;
import com.application.tchapj.alipay.OrderInfoUtil2_0;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.main.bean.MemberInfoWhbyBean;
import com.application.tchapj.my.bean.AlipayPowerBean;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UpMyInfoBean;
import com.application.tchapj.my.presenter.MyAlipayPresenter;
import com.application.tchapj.my.view.IMyAlipayView;
import com.application.tchapj.rxbus.ChangeAnswerEvent;
import com.application.tchapj.rxbus.RxBus;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.pickers.AddressPickTask;
import com.application.tchapj.utils2.pickers.entity.City;
import com.application.tchapj.utils2.pickers.entity.County;
import com.application.tchapj.utils2.pickers.entity.Province;
import com.application.tchapj.utils2.pickers.listeners.OnItemPickListener;
import com.application.tchapj.utils2.pickers.listeners.OnSingleWheelListener;
import com.application.tchapj.utils2.pickers.picker.DatePicker;
import com.application.tchapj.utils2.pickers.picker.SinglePicker;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.KV;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

// 基本资料
public class MyjibenActivity extends BaseMvpActivity<IMyAlipayView, MyAlipayPresenter> implements IMyAlipayView {

    @BindView(R.id.touxiang_rl)
    RelativeLayout touxiang_rl;
    @BindView(R.id.nc_rl)
    RelativeLayout nc_rl;
    @BindView(R.id.xb_rl)
    RelativeLayout xb_rl;
    @BindView(R.id.diqu_rl)
    RelativeLayout diqu_rl;
    @BindView(R.id.sr_rl)
    RelativeLayout sr_rl;

    @BindView(R.id.touxiang_iv)
    ImageView touxiang_iv;
    @BindView(R.id.nicheng_iv)
    TextView nicheng_iv;
    @BindView(R.id.xingbie_iv)
    TextView xingbie_iv;
    @BindView(R.id.diqu_iv)
    TextView diqu_iv;

    @BindView(R.id.shengri_tv)
    TextView shengri_tv;

    @BindView(R.id.alipay_rl)
    RelativeLayout alipay_rl;
    @BindView(R.id.layout_common_loading_framelayout)
    FrameLayout loadingFl;
    @BindView(R.id.layout_common_loading_iv)
    ImageView loadingIv;

    private KV kv;                 // 保存缓存数据的对象

    // 支付宝支付业务：入参app_id
    public String APPID = "2018062560414948";

    // 支付宝账户登录授权业务：入参pid值
    public String PID = "2088721541076382";

    // 支付宝商户唯一标识：入参target_id值
    public String TARGET_ID = "2088721541076382";

    // RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE
    public String RSA2_PRIVATE = "";

    public String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private String qiniuHeadimageUrl;  // 七牛图片路径
    private String imageurl;
    private int maxSelectNum = 1;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    private List<String> imagelist = new ArrayList<>();

    private String sex;      // 性别
    private String headimgurl;      // 头像
    private String nickName;      // 昵称
    private String provincename;      // 省份
    private String cityname;          // 城市
    private String birthday;      // 生日

    private String city;

    private QiniuBean.QiniuBeanResult qiniuBeans = new QiniuBean.QiniuBeanResult();

    private AlipayPrivateKeyBean.AlipayPrivateKeyResult alipayPrivateKeyResult = new AlipayPrivateKeyBean.AlipayPrivateKeyResult();

    private AlipayPowerBean.AlipayBeanResult alipayBeanResult = new AlipayPowerBean.AlipayBeanResult();

    AnimationDrawable animationDrawable;

    // 消息机制刷新页面
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        App.authCode = authResult.getAuthCode();
                        getPresenter().onGetAlipayPowerBeanResult(App.getId(), App.authCode);

                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(MyjibenActivity.this, "授权失败", Toast.LENGTH_LONG).show();
                        kv.put(Constants.AuthCode, "").commit();

                    }

                    break;
                }
                default:
                    break;
            }
        }
    };


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        UserInfo userInfo = SharedPreferencesUtils.getInstance().getUserInfo();

        headimgurl = userInfo.getHeadimgurl();
        nickName = userInfo.getNickName();
        sex = userInfo.getSex();
        city = userInfo.getCity();
        birthday = userInfo.getBirthday();

        toolbarHelper.setTitle("基本资料");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_jiben;
    }

    @Override
    public void initUI() {

        kv = new KV(this);             // 保存基础数据

        // 获得七牛token
        getPresenter().onGetQiniuResult();

        // 获得支付宝私钥
        getPresenter().onGetAlipayPrivateKeyBeanResult();

        //chooseMode = PictureMimeType.ofAll();
        chooseMode = PictureMimeType.ofImage();
        //chooseMode = PictureMimeType.ofVideo();
        //chooseMode = PictureMimeType.ofAudio();
        themeId = R.style.picture_default_style;
        //themeId = R.style.picture_white_style;
        //themeId = R.style.picture_QQ_style;
        //themeId = R.style.picture_Sina_style;

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .placeholder(R.mipmap.ic_media_head_default)
                .error(R.mipmap.ic_media_head_default)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(MyjibenActivity.this)
                .asBitmap()
                .apply(options)
                .load(headimgurl)
                .into(new BitmapImageViewTarget(touxiang_iv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(MyjibenActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        touxiang_iv.setImageDrawable(circularBitmapDrawable);
                    }
                });

        nicheng_iv.setText(nickName);

        if(sex != null && sex.equals("1")){
            xingbie_iv.setText("男");
        }else {
            xingbie_iv.setText("女");
        }

        shengri_tv.setText(birthday);

        if (city != null) {
            diqu_iv.setText(city);
        } else {
            diqu_iv.setText("未确认");
        }

        touxiang_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyjibenActivity.this, "头像", Toast.LENGTH_LONG).show();

                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (position == 0) {
                            // 单独拍照
                            PictureSelector.create(MyjibenActivity.this)
                                    .openCamera(chooseMode)
                                    .theme(themeId)
                                    .maxSelectNum(maxSelectNum)
                                    .minSelectNum(1)
                                    .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                                    .previewImage(true)
                                    .previewVideo(false)
                                    .compressGrade(Luban.THIRD_GEAR)
                                    .isCamera(true)
                                    .glideOverride(160, 160)
                                    .isGif(true)
                                    .openClickSound(false)
                                    .selectionMedia(selectList)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        } else {
                            //打开选择,本次允许选择的数量
                            // 进入相册
                            PictureSelector.create(MyjibenActivity.this)
                                    .openGallery(chooseMode)
                                    .theme(themeId)
                                    .maxSelectNum(maxSelectNum)
                                    .minSelectNum(1)
                                    .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                                    .previewImage(true)
                                    .previewVideo(false)
                                    .compressGrade(Luban.THIRD_GEAR)
                                    .isCamera(true)
                                    .glideOverride(160, 160)
                                    .previewEggs(true)
                                    .isGif(true)
                                    .openClickSound(false)
                                    .selectionMedia(selectList)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }

                    }
                }, names);

            }
        });

        nc_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MyjibenActivity.this, "昵称", Toast.LENGTH_LONG).show();

                LayoutInflater factoryInflater = LayoutInflater
                        .from(MyjibenActivity.this);
                final View view = factoryInflater.inflate(R.layout.my_dialog,
                        null);
                final EditText nc_name = (EditText) view
                        .findViewById(R.id.nc_name);

                AlertDialog.Builder set = new AlertDialog.Builder(
                        MyjibenActivity.this);
                set.setTitle(""); // 标题
                set.setCancelable(false);
                set.setView(view);
                set.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nicheng_iv.setText(nc_name.getText());
                        nickName = String.valueOf(nc_name.getText());
                    }
                });
                set.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                set.show();
            }
        });

        xb_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyjibenActivity.this, "性别", Toast.LENGTH_LONG).show();
                ArrayList<String> list = new ArrayList<>();
                list.add("男");
                list.add("女");
                SinglePicker<String> picker = new SinglePicker<>(MyjibenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setSelectedIndex(8);
                picker.setWheelModeEnable(false);
                //启用权重 setWeightWidth 才起作用
                picker.setLabel("");
                picker.setWeightEnable(true);
                picker.setWeightWidth(1);
                picker.setSelectedTextColor(0xFFEE0000);//前四位值是透明度
                picker.setUnSelectedTextColor(0xFF999999);
                picker.setOnSingleWheelListener(new OnSingleWheelListener() {
                    @Override
                    public void onWheeled(int index, String item) {
                        //showToast("index=" + index + ", item=" + item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        sex = index + 1 + "";
                        Log.e("qiniu++++", "complete: " + index+item);
                        xingbie_iv.setText(item);
                    }
                });
                picker.show();
            }
        });

        diqu_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyjibenActivity.this,"地区",Toast.LENGTH_LONG).show();
                AddressPickTask task = new AddressPickTask(MyjibenActivity.this);
                task.setHideCounty(true);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        diqu_iv.setText("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {

                        provincename = province.getAreaName();
                        cityname = city.getAreaName();
                        diqu_iv.setText(province.getAreaName() + " " + city.getAreaName());

                    }
                });
                task.execute("山东", "济南");
            }
        });

        sr_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyjibenActivity.this,"生日",Toast.LENGTH_LONG).show();
                final DatePicker picker = new DatePicker(MyjibenActivity.this);
                picker.setCanLoop(true);
                picker.setWheelModeEnable(true);
                picker.setTopPadding(15);
                picker.setRangeStart(1949, 10, 1);
                picker.setRangeEnd(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.DATE));
                picker.setSelectedItem(1990, 1, 1);
                picker.setWeightEnable(true);
                picker.setLineColor(Color.BLACK);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {

                        birthday = year + "-" + month + "-" + day;
                        shengri_tv.setText(year + "-" + month + "-" + day);

                    }
                });
                picker.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
                    }
                });
                picker.show();
            }
        });

        alipay_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SharedPreferencesUtils.getInstance().getUserInfo() != null &&
                        !StringUtils.isNullOrEmpty(SharedPreferencesUtils.getInstance().getUserInfo().getAlipay()) && App.alipay.equals("1")) {
                    //已经支付宝授权过
                    dialogs();
                } else {
                    initPower();
                }
            }
        });


    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public MyAlipayPresenter createPresenter() {
        return new MyAlipayPresenter(getApp());
    }

    @Override // 七牛
    public void onGetQiniuBeanResult(QiniuBean qiniuBean) {

        if ("000".equals(qiniuBean.getCode())) {
            qiniuBeans = qiniuBean.getData();
            App.QiniuToken =qiniuBeans.getUploadToken();
        }
    }

    @Override // 获得私钥
    public void onGetAlipayPrivateKeyBeanResult(AlipayPrivateKeyBean alipayPrivateKeyBean) {

        if ("000".equals(alipayPrivateKeyBean.getCode())) {
            alipayPrivateKeyResult = alipayPrivateKeyBean.getData();
            RSA2_PRIVATE = alipayPrivateKeyResult.getPrivatekey();
            kv.put(Constants.RSA2_PRIVATE, alipayPrivateKeyResult.getPrivatekey()).commit();

        }

    }

    @Override // 申请授权
    public void onGetAlipayPowerBeanResult(AlipayPowerBean alipayPowerBean) {

        if ("000".equals(alipayPowerBean.getCode())) {
            alipayBeanResult = alipayPowerBean.getData();
            kv.put(Constants.AuthCode, alipayBeanResult.getUsrid()).commit();
            finish();
            /*Log.i("sssss",alipayBeanResult.getUsrid()+"");
            Toast.makeText(MyjibenActivity.this,alipayPowerBean.getDescription(),Toast.LENGTH_LONG).show();*/
        }
    }

    @Override // 更改用户信息
    public void onGetUpMyInfoBeanResult(UpMyInfoBean upMyInfoBean) {

        if ("000".equals(upMyInfoBean.getCode())) {
            finish();
            Toast.makeText(MyjibenActivity.this, "修改信息成功", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onGetMemberWhbyhResult(MemberInfoWhbyBean infoWhbyBean) {

    }

    @Override
    public void onUpdateWhbyInfoResult(BaseBean baseBean) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    // 支付宝账户授权业务
    public void initPower() {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i) {
                }
            }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；*/

        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;

        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(MyjibenActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MyjibenActivity.this);
        builder.setMessage("已经授权");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(MyjibenActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!MyjibenActivity.this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("qiniu++++", "complete: " + resultCode);
        if (resultCode == MyjibenActivity.this.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);

                    // 进行判断
                    upload(selectList.get(0).getPath());
            }
        }


    }

    private void upload(String fileUrl) {
        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.loading);
        loadingIv.setBackground(animationDrawable);
        animationDrawable.start();
        loadingFl.setVisibility(View.VISIBLE);

        /*Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
                .recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        UploadManager uploadManager = new UploadManager(config);*/


        //上传配置
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(10) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
                .zone(FixedZone.zone2) // 设置区域，指默认 Zone.zone0 注：这步是最关键的 当初错的主要原因也是他 根据自己的地方选
                .build();
        UploadManager uploadManager = new UploadManager(config);

        uploadManager.put(fileUrl, null, App.QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo,
                                 JSONObject jsonObject) {
                if (responseInfo.isOK()) {

                    Log.e("success", "complete:" + responseInfo + jsonObject);

                    try {
                        String upimg = jsonObject.getString("key");

                        imageurl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;
                        imagelist.add(imageurl);
                        Log.e("qiniu++++", "complete: " + jsonObject + imageurl);

                        qiniuHeadimageUrl = imagelist.get(0);
                        headimgurl = qiniuHeadimageUrl;
                        imagelist.clear();

                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .priority(Priority.HIGH)
                                .diskCacheStrategy(DiskCacheStrategy.NONE);

                        Glide.with(MyjibenActivity.this)
                                .asBitmap()
                                .apply(options)
                                .load(headimgurl)
                                .into(new BitmapImageViewTarget(touxiang_iv) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.
                                                        create(MyjibenActivity.this.getResources(), resource);
                                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                        touxiang_iv.setImageDrawable(circularBitmapDrawable);
                                    }
                                });
                        animationDrawable.stop();
                        loadingFl.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("failssss", s + responseInfo + jsonObject + imageurl);
                }
                Log.e("qiniu", "complete: " + jsonObject + imageurl);

            }
        }, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferencesUtils.getInstance().getUserInfo().setSex(sex);
        SharedPreferencesUtils.getInstance().getUserInfo().setHeadimgurl(headimgurl);
        SharedPreferencesUtils.getInstance().setNickName(nickName);
        getPresenter().onGetUpMyInfoBeanResult(App.getId(), sex, headimgurl, nickName, provincename, cityname, birthday);

        // 消息机制
        ChangeAnswerEvent changeAnswerEvent = new ChangeAnswerEvent();
        changeAnswerEvent.setAnswer("1");
        RxBus.getDefault().post(changeAnswerEvent);
    }
}
