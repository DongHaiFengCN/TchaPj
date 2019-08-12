package com.application.tchapj.task.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.alipay.OrderInfoUtil2_0;
import com.application.tchapj.alipay.PayResult;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.bean.PromotionPayResultBean;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.my.activity.MyjibenActivity;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.task.bean.FaTaskBean;
import com.application.tchapj.task.bean.FaTaskSuccessBean;
import com.application.tchapj.task.bean.FaTaskSuccessafterBean;
import com.application.tchapj.task.presenter.FaTaskPresenter;
import com.application.tchapj.task.view.IFaTaskView;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
import com.application.tchapj.utils2.pickers.common.LineConfig;
import com.application.tchapj.utils2.pickers.picker.DateTimePicker;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.KV;
import com.application.tchapj.widiget.OnTagSelectListener;
import com.application.tchapj.widiget.TagAdapter3;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by Administrator on 2018\8\25 0025.
 */

public class TaskFaDyGpFragment extends BaseMvpFragment<IFaTaskView, FaTaskPresenter> implements IFaTaskView, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.fa_dygp_imagerv)
    RecyclerView fa_dygp_imagerv;

    @BindView(R.id.fa_dygp_titilet)
    EditText fa_dygp_titilet;
    @BindView(R.id.fa_dygp_start)
    TextView fa_dygp_start;
    @BindView(R.id.fa_dygp_end)
    TextView fa_dygp_end;

//    @BindView(R.id.fa_dygp_fl)
//    FlowTagLayout fa_dygp_fl;

    @BindView(R.id.fa_dygp_ydet)
    EditText fa_dygp_ydet;
    @BindView(R.id.fa_dygp_dzet)
    EditText fa_dygp_dzet;
    @BindView(R.id.fa_dygp_plet)
    EditText fa_dygp_plet;
    @BindView(R.id.fa_dygp_zfet)
    EditText fa_dygp_zfet;
    @BindView(R.id.fa_dygp_llet)
    EditText fa_dygp_llet;

    @BindView(R.id.fa_dygp_vieorv)
    RecyclerView fa_dygp_vieorv;

    @BindView(R.id.fa_dygp_djet)
    EditText fa_dygp_djet;
    @BindView(R.id.fa_dygp_slet)
    EditText fa_dygp_slet;
    @BindView(R.id.fa_dygp_fanset)
    EditText fa_dygp_fanset;
    @BindView(R.id.fa_dygp_zjtv)
    TextView fa_dygp_zjtv;

    @BindView(R.id.fa_dygp_bt)
    Button fa_dygp_bt;


    private String imageurl;
    private String audioUrl;
    private ArrayList<ImageItem> images = null;
    // 第一个图片选择
    private ImagePickerAdapter adapter;        // 适配器
    private ArrayList<ImageItem> selImageList; // 当前选择的所有图片
    //private int maxImgCount = 8;               //允许选择图片最大数
    private int maxImgCount = 1;               //允许选择图片最大数

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    // 第二个图片选择
    //private int maxSelectNum = 9;
    private int maxSelectNum = 1;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    private GridImageAdapter gridImageAdapter;
    private List<String> imagelist = new ArrayList<>();

    // 流标签选择
    private String tabid;
    private TagAdapter3<String> mSizeTagAdapter;
    private List<String> positionlist = new ArrayList<>();
    public List<HomeCircleModel.HomeCircleModelResult.HomeCircle> circles;
    private String Position1 = "";
    private String Position2 = "";
    private String Position3 = "";

    private String memberId;    // 用户id
    private String taskType;    // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
    private String name;        // 活动标题
    private String require;     // 任务要求/商家需求
    private String imgUrl;      // 活动缩略图
    private String startTime;   // 活动开始时间
    private String endTime;     // 活动结束时间
//    private String circleTypeId;// 投放圈子
    private String unitPrice;   // 单价
    private String taskImgUrl;  // 活动图片url或视频url
    private String taskNum;     // 投放数量
    private String taskGuidance;// 活动引导
    private String phonenumber; // 手机号码
    private String copywriting; // 朋友圈文案
    private String Fans;        // 要求粉丝数量

    private BigDecimal sl;
    private BigDecimal dj;

    private FaTaskBean.FaTaskBeanResult faTaskBeanResult = new FaTaskBean.FaTaskBeanResult();
    private String taskId; // 任务ID

    private KV kv;                 // 保存缓存数据的对象

    private FaTaskSuccessBean.FaTaskSuccessBeanResult faTaskSuccessBeanResult = new FaTaskSuccessBean.FaTaskSuccessBeanResult();
    private String orderString; // 支付宝预付订单信息
    private String ordernumber; // 流水号

    // 支付宝支付业务：入参app_id
    public static final String APPID = "2018062560414948";

    // 支付宝私钥
    public static String RSA2_PRIVATE = "";

    // 支付宝公钥
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;

    // 消息机制刷新页面
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
/**
 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。*/


                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject json = new JSONObject(resultInfo);
                            JSONObject addressJson = json.getJSONObject("alipay_trade_app_pay_response");
                            ordernumber = addressJson.getString("trade_no");

                            Log.e("预付订单2", "预付订单: " + addressJson.getString("trade_no"));
                            // 上传任务付款 参数一  表示交易金额  参数二  表示任务id 参数三 表示交易流水号 参数四  表示用户id
                            getPresenter().getFaTaskSuccessafterBeanResult(fa_dygp_zjtv.getText().toString(), taskId,ordernumber,App.getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //Log.e("预付订单2", "预付订单: " + resultStatus);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                        Log.e("预付订单", "预付订单: " + resultInfo);
                        //Log.e("预付订单2", "预付订单: " + resultStatus);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    // 接收参数
    public static TaskFaDyGpFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        TaskFaDyGpFragment fragment = new TaskFaDyGpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_fa_dygptask;
    }

    @Override
    public void initUI() {

        kv = new KV(getContext());             // 保存基础数据
        RSA2_PRIVATE = kv.getString(Constants.RSA2_PRIVATE, "");

        //chooseMode = PictureMimeType.ofAll();
        //chooseMode = PictureMimeType.ofImage();
        chooseMode = PictureMimeType.ofVideo();
        //chooseMode = PictureMimeType.ofAudio();
        themeId = R.style.picture_default_style;
        //themeId = R.style.picture_white_style;
        //themeId = R.style.picture_QQ_style;
        //themeId = R.style.picture_Sina_style;
        initSecondImage();

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getContext(), selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        initImagePicker();
        initWidget();

        // 多选
        mSizeTagAdapter = new TagAdapter3<>(getContext());
        mSizeTagAdapter.setSelected(-1); // 默认选项
     /*   fa_dygp_fl.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI); // 选择方式
        fa_dygp_fl.setAdapter(mSizeTagAdapter);
        fa_dygp_fl.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                        //tabid = newstypeList.get(i).getId();
                    }
                    if (positionlist.size() >= 2) {
                        dialogs();
                    } else {
                        positionlist.add(circles.get(position).getId());
                        Snackbar.make(parent, circles.get(position).getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } else {

                    //tabid = newstypeList.get(0).getId();
                    *//*Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*//*

                }

            }
        });*/

        initSizeData();

        // 时间选择
        fa_dygp_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePicker picker = new DateTimePicker(getActivity(), DateTimePicker.HOUR_24);
                picker.setActionButtonTop(false);
                picker.setCanLinkage(false);
                picker.setTitleText("请选择");
                // picker.setStepMinute(5);
                picker.setWeightEnable(true);
                picker.setWheelModeEnable(true);
                LineConfig config = new LineConfig();
                config.setColor(Color.BLUE);//线颜色
                config.setAlpha(120);//线透明度
                config.setVisible(true);//线不显示 默认显示
                picker.setLineConfig(config);
                picker.setLabel(null, null, null, null, null);
                picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {

                        fa_dygp_start.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
                    }
                });
                picker.show();
            }
        });

        fa_dygp_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePicker picker = new DateTimePicker(getActivity(), DateTimePicker.HOUR_24);
                picker.setActionButtonTop(false);
                picker.setCanLinkage(false);
                picker.setTitleText("请选择");
//        picker.setStepMinute(5);
                picker.setWeightEnable(true);
                picker.setWheelModeEnable(true);
                LineConfig config = new LineConfig();
                config.setColor(Color.BLUE);//线颜色
                config.setAlpha(120);//线透明度
                config.setVisible(true);//线不显示 默认显示
                picker.setLineConfig(config);
                picker.setLabel(null, null, null, null, null);
                picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        fa_dygp_end.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
                    }
                });
                picker.show();
            }
        });


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!StringUtils.isNullOrEmpty(fa_dygp_slet.getText().toString()) && !StringUtils.isNullOrEmpty(fa_dygp_djet.getText().toString())){
                    sl = new BigDecimal(fa_dygp_slet.getText().toString());
                    dj = new BigDecimal(fa_dygp_djet.getText().toString());
                    fa_dygp_zjtv.setText(sl.multiply(dj) + "");

                }

            }
        };
        fa_dygp_djet.addTextChangedListener(textWatcher);
        fa_dygp_slet.addTextChangedListener(textWatcher);

        fa_dygp_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                memberId = App.getId();   // 用户id

                taskType = "2";      // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
                name = fa_dygp_titilet.getText().toString();         // 活动标题

                startTime = fa_dygp_start.getText().toString() + ":00";       // 活动开始时间
                endTime = fa_dygp_end.getText().toString() + ":00";          // 活动结束时间

                unitPrice = fa_dygp_djet.getText().toString();        // 单价
                taskNum = fa_dygp_slet.getText().toString();          // 投放数量
                taskGuidance = fa_dygp_ydet.getText().toString();     // 活动引导
                phonenumber = "";                                     // 手机号码
                copywriting = "";                                     // 朋友圈文案
//                Fans = fa_dygp_fanset.getText().toString();           // 要求粉丝数量

              /*  if (positionlist.size() == 1) {
                    Position1 = positionlist.get(0);
                    circleTypeId = Position1;
                } else if (positionlist.size() == 2) {
                    Position1 = positionlist.get(0);
                    Position2 = positionlist.get(1);
                    circleTypeId = Position1 + "," + Position2;
                }
                if (positionlist.size() == 3) {
                    Position1 = positionlist.get(0);
                    Position2 = positionlist.get(1);
                    Position3 = positionlist.get(2);
                    circleTypeId = Position1 + "," + Position2 + "," + Position3;
                } else {
                    circleTypeId = "1";
                }*/

                // 任务要求/商家需求
                if (fa_dygp_dzet.getText().toString().length() > 0) {
                    require = "点赞量不少于" + fa_dygp_dzet.getText().toString() + "个";
                } else if (fa_dygp_dzet.getText().toString().length() > 0
                        && fa_dygp_plet.getText().toString().length() > 0) {

                    require = "点赞量不少于" + fa_dygp_dzet.getText().toString() + "个"
                            + "," + "评论量不少于" + fa_dygp_dzet.getText().toString() + "个";

                } else if (fa_dygp_dzet.getText().toString().length() > 0
                        && fa_dygp_plet.getText().toString().length() > 0
                        && fa_dygp_zfet.getText().toString().length() > 0) {

                    require = "点赞量不少于" + fa_dygp_dzet.getText().toString() + "个"
                            + "," + "评论量不少于" + fa_dygp_dzet.getText().toString() + "个"
                            + "," + "转发量不少于" + fa_dygp_dzet.getText().toString() + "个";

                } else if (fa_dygp_dzet.getText().toString().length() > 0
                        && fa_dygp_plet.getText().toString().length() > 0
                        && fa_dygp_zfet.getText().toString().length() > 0
                        && fa_dygp_llet.getText().toString().length() > 0) {

                    require = "点赞量不少于" + fa_dygp_dzet.getText().toString() + "个"
                            + "," + "评论量不少于" + fa_dygp_dzet.getText().toString() + "个"
                            + "," + "转发量不少于" + fa_dygp_dzet.getText().toString() + "个"
                            + "," + "浏览量不少于" + fa_dygp_dzet.getText().toString() + "个";
                }

                if(imagelist != null){
                    imgUrl = imagelist.get(0);    // 活动缩略图
                }
                taskImgUrl = audioUrl;// 活动图片url或视频url

                if (name.length() <= 0) {
                    Toast.makeText(getContext(), "请输入活动标题！", Toast.LENGTH_LONG).show();
                    return;
                } else if (startTime.length() <= 0) {
                    Toast.makeText(getContext(), "请选择开始时间！", Toast.LENGTH_LONG).show();
                    return;
                } else if (endTime.length() <= 0) {
                    Toast.makeText(getContext(), "请选择结束时间！", Toast.LENGTH_LONG).show();
                    return;
                } else if (unitPrice.length() <= 0) {
                    Toast.makeText(getContext(), "请输入单价！", Toast.LENGTH_LONG).show();
                    return;
                } else if (taskNum.length() <= 0) {
                    Toast.makeText(getContext(), "请输入投放数量！", Toast.LENGTH_LONG).show();
                    return;
                } else if (taskGuidance.length() <= 0) {
                    Toast.makeText(getContext(), "请输入活动引导！", Toast.LENGTH_LONG).show();
                    return;
                } else if (imgUrl == null) {
                    Toast.makeText(getContext(), "请选择缩略图！", Toast.LENGTH_LONG).show();
                    return;
                } else if (taskImgUrl == null) {
                    Toast.makeText(getContext(), "请选择投放视频或视频正在上传", Toast.LENGTH_LONG).show();
                    return;
                } else if (require.length() <= 0) {
                    Toast.makeText(getContext(), "请输入任务要求！", Toast.LENGTH_LONG).show();
                    return;
                }

                // 上传合拍任务
                getPresenter().getFaTaskBeanResult(memberId, taskType, name, require, imgUrl, startTime
                        , endTime, unitPrice, taskImgUrl
                        , taskNum, taskGuidance, phonenumber, copywriting, Fans,"", "");


            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public FaTaskPresenter createPresenter() {
        return new FaTaskPresenter(getApp());
    }

    @Override // 发任务
    public void onGetFaTaskBeanModels(FaTaskBean faTaskBean) {

        if ("000".equals(faTaskBean.getCode())) {
            faTaskBeanResult = faTaskBean.getData();
            taskId = faTaskBean.getData().getTaskId();  // 任务id

            // 上传合拍任务 参数一  表示交易金额  参数二  表示活动名称
           // getPresenter().getFaTaskSuccessBeanResult(fa_dygp_zjtv.getText().toString(), name);

        }
    }

    @Override
    public void onGetAlipayOrderInfoSuccessBeanModels(FaTaskSuccessBean faTaskSuccessBean) {

    }

/*    @Override
    public void onGetFaTaskSuccessBeanModels(FaTaskSuccessBean faTaskSuccessBean) {

        if ("000".equals(faTaskSuccessBean.getCode())) {
            faTaskSuccessBeanResult = faTaskSuccessBean.getData();
            orderString = faTaskSuccessBean.getData().getOrderString(); // 支付宝预付订单信息
            Log.e("预付订单", "预付订单: " + orderString);

            if (RSA2_PRIVATE.equals("")) {
                jibendialogs();
            } else {
                payV2(fa_dygp_zjtv.getText().toString(), name);
            }


        }

    }*/

    @Override // 任务付款
    public void onGetFaTaskSuccessafterBeanModels(FaTaskSuccessafterBean faTaskSuccessafterBean) {

        if ("000".equals(faTaskSuccessafterBean.getCode())) {

            Toast.makeText(getContext(), "上传流水成功！", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onGetBrokerage(MoneyInfoBean moneyInfoBean) {

    }

    @Override
    public void onGetPromotionPaySuccess(PromotionPayResultBean baseBean) {

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

    private void initImagePicker() {

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {

        fa_dygp_imagerv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        fa_dygp_imagerv.setHasFixedSize(true);
        fa_dygp_imagerv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(getContext(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(getContext(), ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(getContext(), ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private void initSecondImage() {

        FullyGridLayoutManager manager = new FullyGridLayoutManager(getContext(), 1
                , GridLayoutManager.VERTICAL, false);
        fa_dygp_vieorv.setLayoutManager(manager);
        gridImageAdapter = new GridImageAdapter(getContext(), onAddPicClickListener);
        gridImageAdapter.setList(selectList);
        gridImageAdapter.setSelectMax(maxSelectNum);
        fa_dygp_vieorv.setAdapter(gridImageAdapter);
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                Log.e("pictureType", "url = " + pictureType);
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片
                        PictureSelector.create(TaskFaDyGpFragment.this).externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(TaskFaDyGpFragment.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(TaskFaDyGpFragment.this).externalPictureAudio(media.getPath());
                        break;
                }
            }
        });

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter
            .onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            List<String> names = new ArrayList<>();
            names.add("拍摄");
            names.add("视频");
            showDialog(new SelectDialog.SelectDialogListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        // 单独拍照
                        PictureSelector.create(TaskFaDyGpFragment.this)
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
                        PictureSelector.create(TaskFaDyGpFragment.this)
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
    };

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);

                    // 进行判断
                    upload(images.get(0).path, false);

                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {

                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();

                    // 进行判断
                    upload(selectList.get(0).getPath(), true);
            }
        }


    }

    private void upload(String fileUrl, final boolean isAudio) {

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
                        if(isAudio){
                            audioUrl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;
                        }
                        imagelist.add(imageurl);

                        Log.e("qiniu++++", "complete: " + jsonObject + imageurl);

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

    // 初始化数据
    private void initSizeData() {
        circles = App.circles;
        List<String> dataSource = new ArrayList<>();
        if(circles != null){
            for (int i = 0; i < circles.size(); i++) {
                dataSource.add(circles.get(i).getName());
            }
        }


        mSizeTagAdapter.onlyAddAll(dataSource);

    }

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("最多选择3个");
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

    // 支付宝支付业务
    public void payV2(String money, String titls) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(getContext()).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, money, titls); // 传参重点
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void jibendialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("请进行支付宝申请授权");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = new Intent(getContext(), MyjibenActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


}
