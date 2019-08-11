package com.application.tchapj.my.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
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
import com.application.tchapj.main.bean.MemberInfoWhbyBean;
import com.application.tchapj.my.bean.AlipayPowerBean;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UpMyInfoBean;
import com.application.tchapj.my.presenter.MyAlipayPresenter;
import com.application.tchapj.my.view.IMyAlipayView;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.pickers.AddressPickTask;
import com.application.tchapj.utils2.pickers.entity.City;
import com.application.tchapj.utils2.pickers.entity.County;
import com.application.tchapj.utils2.pickers.entity.Province;
import com.application.tchapj.utils2.pickers.picker.DatePicker;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
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
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 微呼百应号设置
 */
public class WhbyMemberActivity extends BaseMvpActivity<IMyAlipayView, MyAlipayPresenter> implements IMyAlipayView {

    @BindView(R.id.touxiang_rl)
    RelativeLayout touxiang_rl;
    @BindView(R.id.nc_rl)
    RelativeLayout nc_rl;
    @BindView(R.id.diqu_rl)
    RelativeLayout diqu_rl;

    @BindView(R.id.touxiang_iv)
    ImageView touxiang_iv;
    @BindView(R.id.nick_name_tv)
    TextView nickNameTv;

    @BindView(R.id.qianming_tv)
    TextView qianmingTv;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu_title)
    TextView toolbarMenuTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.qianming_rl)
    RelativeLayout qianmingRl;
    @BindView(R.id.diqu_tv)
    TextView diqu_tv;
    @BindView(R.id.real_name_tv)
    TextView realNameTv;
    @BindView(R.id.real_name_rl)
    RelativeLayout realNameRl;
    @BindView(R.id.id_number_tv)
    TextView idNumberTv;
    @BindView(R.id.id_number_rl)
    RelativeLayout idNumberRl;
    @BindView(R.id.telephone_tv)
    TextView telephoneTv;
    @BindView(R.id.telephone_rl)
    RelativeLayout telephoneRl;
    @BindView(R.id.activity_whby_member_loading_iv)
    ImageView loadingIv;
    @BindView(R.id.activity_whby_member_loading_fl)
    FrameLayout loadingFl;


    private KV kv;                 // 保存缓存数据的对象

    // RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE
    public String RSA2_PRIVATE = "";

    private static final int SDK_AUTH_FLAG = 2;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private int maxSelectNum = 1;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();

    private String headimageUrl;  //头像
    private String nickName;      // 昵称
    private String provincename;      // 省份
    private String cityname;          // 城市
    private String realName;
    private String idNumber;
    private String telephoneNumber;
    private String signatureStr;//签名

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
                        kv.put(Constants.AuthCode, alipayBeanResult.getUsrid()).commit();

                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(WhbyMemberActivity.this, "授权失败", Toast.LENGTH_LONG).show();
                        kv.put(Constants.AuthCode, "").commit();

                    }

                    break;
                }
                default:
                    break;
            }
        }
    };
    private View.OnClickListener noUpdateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ToastUtil.show(WhbyMemberActivity.this, "此字段不支持修改");
        }
    };


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("微呼百应号设置");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_whby_member;
    }

    @Override
    public void initUI() {

        kv = new KV(this);             // 保存基础数据

        // 获得七牛token
        getPresenter().onGetQiniuResult();


        //chooseMode = PictureMimeType.ofAll();
        chooseMode = PictureMimeType.ofImage();
        //chooseMode = PictureMimeType.ofVideo();
        //chooseMode = PictureMimeType.ofAudio();
        themeId = R.style.picture_default_style;
        //themeId = R.style.picture_white_style;
        //themeId = R.style.picture_QQ_style;
        //themeId = R.style.picture_Sina_style;


        initClickListener();

        getPresenter().onGetMemberWhbyhResult(App.getId());//获取微呼百应号信息


    }

    private void initClickListener() {
        //头像
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
                        selectList.clear();
                        if (position == 0) {
                            // 单独拍照
                            PictureSelector.create(WhbyMemberActivity.this)
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
                            PictureSelector.create(WhbyMemberActivity.this)
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

        //昵称
        nc_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MyjibenActivity.this, "昵称", Toast.LENGTH_LONG).show();

                LayoutInflater factoryInflater = LayoutInflater
                        .from(WhbyMemberActivity.this);
                final View view = factoryInflater.inflate(R.layout.my_dialog,
                        null);
                final EditText nc_name = (EditText) view
                        .findViewById(R.id.nc_name);
                nc_name.setHint("请输入微呼百应号名称");

                AlertDialog.Builder set = new AlertDialog.Builder(
                        WhbyMemberActivity.this);
                set.setTitle(""); // 标题
                set.setCancelable(false);
                set.setView(view);
                set.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!StringUtils.isNullOrEmpty(nc_name.getText().toString())){
                            nickNameTv.setText(nc_name.getText());
                            nickName = String.valueOf(nc_name.getText());
                        }
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

        //签名
        qianmingRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MyjibenActivity.this, "昵称", Toast.LENGTH_LONG).show();

                LayoutInflater factoryInflater = LayoutInflater
                        .from(WhbyMemberActivity.this);
                final View view = factoryInflater.inflate(R.layout.my_dialog,
                        null);
                final EditText editText = (EditText) view
                        .findViewById(R.id.nc_name);
                editText.setHint("请输入签名");

                AlertDialog.Builder set = new AlertDialog.Builder(
                        WhbyMemberActivity.this);
                set.setTitle(""); // 标题
                set.setCancelable(false);
                set.setView(view);
                set.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!StringUtils.isNullOrEmpty(editText.getText().toString())){
                            qianmingTv.setText(editText.getText());
                            signatureStr = String.valueOf(editText.getText());
                        }

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


   /*     //所在区域
        diqu_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyjibenActivity.this,"地区",Toast.LENGTH_LONG).show();
                AddressPickTask task = new AddressPickTask(WhbyMemberActivity.this);
                task.setHideCounty(true);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        diqu_tv.setText("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {

                        provincename = province.getAreaName();
                        cityname = city.getAreaName();
                        diqu_tv.setText(province.getAreaName() + " " + city.getAreaName());

                    }
                });
                task.execute("山东", "济南");
            }
        });*/


        //联系方式
        telephoneRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MyjibenActivity.this, "昵称", Toast.LENGTH_LONG).show();

                LayoutInflater factoryInflater = LayoutInflater
                        .from(WhbyMemberActivity.this);
                final View view = factoryInflater.inflate(R.layout.my_dialog,
                        null);
                final EditText editText = (EditText) view
                        .findViewById(R.id.nc_name);
                editText.setHint("请输入联系方式");

                AlertDialog.Builder set = new AlertDialog.Builder(
                        WhbyMemberActivity.this);
                set.setTitle(""); // 标题
                set.setCancelable(false);
                set.setView(view);
                set.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!StringUtils.isNullOrEmpty(editText.getText().toString())){
                            telephoneTv.setText(editText.getText());
                            telephoneNumber = String.valueOf(editText.getText());
                        }

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

        diqu_rl.setOnClickListener(noUpdateClickListener);
        realNameRl.setOnClickListener(noUpdateClickListener);
        idNumberRl.setOnClickListener(noUpdateClickListener);

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
            App.QiniuToken = qiniuBeans.getUploadToken();
        }
    }

    @Override // 获得私钥
    public void onGetAlipayPrivateKeyBeanResult(AlipayPrivateKeyBean alipayPrivateKeyBean) {
    }

    @Override
    public void onGetAlipayPowerBeanResult(AlipayPowerBean alipayPowerBean) {

    }

    @Override
    public void onGetUpMyInfoBeanResult(UpMyInfoBean upMyInfoBean) {

    }


    //修改微呼百应号信息返回
    @Override
    public void onUpdateWhbyInfoResult(BaseBean baseBean) {
        if ("000".equals(baseBean.getCode())) {
            finish();
            Toast.makeText(WhbyMemberActivity.this, "修改信息成功", Toast.LENGTH_LONG).show();
        }
    }

    //获取微呼百应号资料
    @Override
    public void onGetMemberWhbyhResult(MemberInfoWhbyBean infoWhbyBean) {
        if (infoWhbyBean != null) {
            headimageUrl = infoWhbyBean.getHeadimgurl();
            nickName = infoWhbyBean.getNickName();
            signatureStr = infoWhbyBean.getAuthor();
            provincename = infoWhbyBean.getProvince();
            cityname = infoWhbyBean.getCity();
            realName = infoWhbyBean.getRealname();
            idNumber = infoWhbyBean.getIdnumber();
            telephoneNumber = infoWhbyBean.getTelephone();

            if(!StringUtils.isNullOrEmpty(nickName)){
                nickNameTv.setText(nickName);
            }else{
                nickNameTv.setText("请输入微呼百应号名称");
            }

            if(!StringUtils.isNullOrEmpty(signatureStr)){
                qianmingTv.setText(signatureStr);//签名
            }else{
                qianmingTv.setText("请输入签名");
            }



            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .placeholder(R.mipmap.ic_media_head_default)
                    .error(R.mipmap.ic_media_head_default)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);


            Glide.with(WhbyMemberActivity.this)
                    .asBitmap()
                    .apply(options)
                    .load(headimageUrl)
                    .into(new BitmapImageViewTarget(touxiang_iv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(WhbyMemberActivity.this.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            touxiang_iv.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            if (!StringUtils.isNullOrEmpty(provincename) || !StringUtils.isNullOrEmpty(cityname)) {
                diqu_tv.setText(provincename + "-" + cityname);
            } else {
                diqu_tv.setText("未确认");
            }

            realNameTv.setText(realName);
            idNumberTv.setText(idNumber);
            if(!StringUtils.isNullOrEmpty(telephoneNumber)){
                telephoneTv.setText(telephoneNumber);
            }else{
                telephoneTv.setText("请输入联系方式");
            }

        }

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



    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(WhbyMemberActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!WhbyMemberActivity.this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("qiniu++++", "complete: " + resultCode);
        if (resultCode == WhbyMemberActivity.this.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);

                    animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.loading);
                    loadingIv.setBackground(animationDrawable);
                    animationDrawable.start();
                    loadingFl.setVisibility(View.VISIBLE);
                    // 进行判断
                    upload(selectList.get(0).getPath());
            }
        }


    }

    private void upload(String fileUrl) {

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

                        headimageUrl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;
                        Log.e("qiniu++++", "complete: " + jsonObject + headimageUrl);


                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .priority(Priority.HIGH)
                                .diskCacheStrategy(DiskCacheStrategy.NONE);

                        Glide.with(WhbyMemberActivity.this)
                                .asBitmap()
                                .apply(options)
                                .load(headimageUrl)
                                .into(new BitmapImageViewTarget(touxiang_iv) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.
                                                        create(WhbyMemberActivity.this.getResources(), resource);
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

                }

            }
        }, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onUpdateWhbyInfoResult(headimageUrl, nickName, provincename, cityname, telephoneNumber, signatureStr);
    }

}
