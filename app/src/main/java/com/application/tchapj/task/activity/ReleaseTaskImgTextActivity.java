package com.application.tchapj.task.activity;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.alipay.PayResult;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.PromotionPayResultBean;
import com.application.tchapj.bean.TaskDraftBean;
import com.application.tchapj.my.activity.MyjibenActivity;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.task.adapter.FriendReleaseAdapter;
import com.application.tchapj.task.bean.FaTaskBean;
import com.application.tchapj.task.bean.FaTaskSuccessBean;
import com.application.tchapj.task.bean.FaTaskSuccessafterBean;
import com.application.tchapj.task.presenter.FaTaskPresenter;
import com.application.tchapj.task.view.IFaTaskView;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.Utils;
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
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.FlowTagDarenLayout;
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.GridSpacingItemDecoration;
import com.application.tchapj.widiget.KV;
import com.application.tchapj.widiget.OnTagSelectListener;
import com.application.tchapj.widiget.TagAdapter;
import com.application.tchapj.widiget.ToolbarHelper;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.thirdparty.L;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

// 发图文任务
public class ReleaseTaskImgTextActivity extends BaseMvpActivity<IFaTaskView, FaTaskPresenter>
        implements IFaTaskView, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.fa_pyq_imagerv)
    RecyclerView fa_pyq_imagerv;

    @BindView(R.id.fa_pyq_titlet)
    EditText fa_pyq_titlet;
    @BindView(R.id.fa_pyq_start)
    TextView fa_pyq_start;
    @BindView(R.id.fa_pyq_end)
    TextView fa_pyq_end;

    @BindView(R.id.toolbar_menu_title)
    TextView toolbarRightTv;


    @BindView(R.id.fa_pyq_ydet)
    EditText fa_pyq_ydet;
    @BindView(R.id.release_task_img_text_task_require)
    EditText taskRequireTv;//任务要求
    @BindView(R.id.fa_pyq_waet)
    EditText fa_pyq_waet;
    @BindView(R.id.release_task_img_text_select_channel)
    FlowTagLayout selectChannelTagLayout;


    @BindView(R.id.fa_pyq_image_nanerv)
    RecyclerView fa_pyq_image_nanerv;

    @BindView(R.id.fa_pyq_djet)
    EditText fa_pyq_djet;
    @BindView(R.id.fa_pyq_slet)
    EditText fa_pyq_slet;
    @BindView(R.id.fa_pyq_price_tv)
    TextView fa_pyq_price_tv;

    @BindView(R.id.fa_pyq_bt)
    Button fa_pyq_bt;
    @BindView(R.id.fa_pyqtask_original_price_tv)
    TextView originalPriceTv;
    @BindView(R.id.fa_pyqtask_discount_tv)
    TextView discountTv;//折扣
    @BindView(R.id.toolbar_title)
    TextView titleTv;


    Dialog publishTaskDialog;
    private BigDecimal discount;
    private String discountPrice = "200"; //>=这个值就打折
    private String discountStr;//后台返的折扣字段，没折扣值为空
    private boolean isUseDiscount = false;//有没有用折扣

    int current = 1;
    /**
     * 1 推广金  2 支付宝
     */
    int payType = 1;
    private String imageurl;
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
    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalMedia> mselectList1;
    private List<LocalMedia> mselectList2;
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    private GridImageAdapter gridImageAdapter;
    private List<String> imagelist = new ArrayList<>();

    // 流标签选择
    private String tabid;
    private List<String> positionlist = new ArrayList<>();
    private String Position1 = "";
    private String Position2 = "";
    private String Position3 = "";

    private String memberId;    // 用户id
    private String taskType;    // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
    private String name;        // 活动标题
    private String require;     // 任务要求/商家需求
    private String activityImgUrl;      // 活动缩略图
    private String startTime;   // 活动开始时间
    private String endTime = "";     // 活动结束时间
    //    private String circleTypeId;// 投放圈子
    private String unitPrice;   // 单价
    private String taskImgUrl;  // 活动图片url或视频url
    private String taskNum;     // 投放数量
    private String taskGuidance;// 活动引导
    private String phonenumber; // 手机号码
    private String copywriting; // 朋友圈文案
    private String Fans = "";        // 要求粉丝数量

    private TagAdapter<String> tagLayoutAdapter;

    public BigDecimal sl;
    public BigDecimal dj;

    private FaTaskBean.FaTaskBeanResult faTaskBeanResult = new FaTaskBean.FaTaskBeanResult();
    public String taskId;

    private KV kv;                 // 保存缓存数据的对象
    //private FaTaskSuccessBean.FaTaskSuccessBeanResult faTaskSuccessBeanResult = new FaTaskSuccessBean.FaTaskSuccessBeanResult();
    public String orderString; // 支付宝预付订单信息
    //public static String ordernumber = "2018082821001004090523209120"; // 流水号

    // 支付宝支付业务：入参app_id
    // public static final String APPID = "2018062560414948";

    // 支付宝私钥
    // public static String RSA2_PRIVATE = "";

    // 支付宝公钥
    // public static final String RSA_PRIVATE = "";

    private double brokerage;


    private static final int SDK_PAY_FLAG = 1;
    // 消息机制刷新页面
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
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
                        Toast.makeText(ReleaseTaskImgTextActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject json = new JSONObject(resultInfo);
                            JSONObject addressJson = json.getJSONObject("alipay_trade_app_pay_response");
                            App.ordernumber = addressJson.getString("trade_no");


                            // 上传任务付款 参数一  表示交易金额  参数二  表示任务id 参数三 表示交易流水号 参数四  表示用户id

                            Log.e("支付：", "url===" + fa_pyq_price_tv.getText().toString());
                            getPresenter().getFaTaskSuccessafterBeanResult(fa_pyq_price_tv.getText().toString(), taskId, App.ordernumber, App.getId());


                            Log.e("预付订单2", "预付订单: " + addressJson.getString("trade_no"));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ReleaseTaskImgTextActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
        originalPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getRootViewId() {

        return R.layout.activity_release_task_img_text;
    }

    @Override
    public void initUI() {

        fa_pyq_ydet.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    fa_pyq_ydet.setTextColor(getColor(R.color.black));

                }
            }
        });
        taskRequireTv.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    taskRequireTv.setTextColor(getColor(R.color.black));
                }
            }
        });
        getPresenter().getBrokerage();
        titleTv.setText("发图文任务");
        toolbarRightTv.setText("保存草稿");

        kv = new KV(ReleaseTaskImgTextActivity.this);
        //RSA2_PRIVATE = kv.getString(Constants.RSA2_PRIVATE, "");

        //chooseMode = PictureMimeType.ofAll();
        chooseMode = PictureMimeType.ofImage();
        //chooseMode = PictureMimeType.ofVideo();
        //chooseMode = PictureMimeType.ofAudio();
        themeId = R.style.picture_default_style;
        //themeId = R.style.picture_white_style;
        //themeId = R.style.picture_QQ_style;
        //themeId = R.style.picture_Sina_style;
        initSecondImage();

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(ReleaseTaskImgTextActivity.this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        initImagePicker();
        initWidget();
        initSelectChannelTabLayout();


        // 时间选择
        fa_pyq_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePicker picker = new DateTimePicker(ReleaseTaskImgTextActivity.this, DateTimePicker.HOUR_24);
                picker.setActionButtonTop(false);
                picker.setCanLinkage(false);
                picker.setTitleText("请选择");
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

                        SimpleDateFormat ss = new SimpleDateFormat("ss");//获取秒
                        String s = ss.format(new Date());
                        fa_pyq_start.setText(month + "-" + day + " " + hour + ":" + minute + ":" + s);
                        startTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + s;       // 活动开始时间
                    }
                });
                picker.show();
            }
        });

        fa_pyq_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePicker picker = new DateTimePicker(ReleaseTaskImgTextActivity.this, DateTimePicker.HOUR_24);
                picker.setActionButtonTop(false);
                picker.setCanLinkage(false);
                picker.setTitleText("请选择");
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
                        SimpleDateFormat ss = new SimpleDateFormat("ss");//获取秒
                        String s = ss.format(new Date());
                        fa_pyq_end.setText(month + "-" + day + " " + hour + ":" + minute + ":" + s);
                        endTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + s;// 活动结束时间
                    }
                });
                picker.show();
            }
        });

        //图文单价和数量输入框值变化时动态计算价格
        TextWatcher tuwenTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isNullOrEmpty(fa_pyq_slet.getText().toString()) && !StringUtils.isNullOrEmpty(fa_pyq_djet.getText().toString())) {
                    sl = new BigDecimal(fa_pyq_slet.getText().toString());
                    dj = new BigDecimal(fa_pyq_djet.getText().toString());
                    setPrice(sl, dj);
                } else {
                    originalPriceTv.setText("");
                    fa_pyq_price_tv.setText("");
                }
            }
        };
        fa_pyq_djet.addTextChangedListener(tuwenTextWatcher);
        fa_pyq_slet.addTextChangedListener(tuwenTextWatcher);


        fa_pyq_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getViewData();//获取界面上用户填写的数据

                if (activityImgUrl == null) {
                    Toast.makeText(getApplication(), "请选择活动缩略图！", Toast.LENGTH_LONG).show();
                    return;
                } else if (name.length() <= 0) {
                    Toast.makeText(getApplication(), "请输入活动标题！", Toast.LENGTH_LONG).show();
                    return;
                } else if (startTime.length() <= 0) {
                    Toast.makeText(getApplication(), "请选择开始时间！", Toast.LENGTH_LONG).show();
                    return;
                } else if (endTime.length() <= 0) {
                    Toast.makeText(getApplication(), "请选择结束时间！", Toast.LENGTH_LONG).show();
                    return;
                } else if (taskGuidance.length() <= 0 && current != 0) {
                    Toast.makeText(getApplication(), "请输入活动引导！", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(taskImgUrl)) {

                    Toast.makeText(getApplication(), "请选择图片！", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(require)) {
                    Toast.makeText(getApplication(), "请输入任务要求！", Toast.LENGTH_LONG).show();
                    return;
                } else if (copywriting.length() <= 0 && current != 0) {
                    Toast.makeText(getApplication(), "请输入朋友圈文案！", Toast.LENGTH_LONG).show();
                    return;
                } else if (unitPrice.length() <= 0) {
                    Toast.makeText(getApplication(), "请输入单价！", Toast.LENGTH_LONG).show();
                    return;
                } else if (taskNum.length() <= 0) {
                    Toast.makeText(getApplication(), "请输入投放数量！", Toast.LENGTH_LONG).show();
                    return;
                }


                // 上传朋友圈任务
//                if(current == 0){
//                    //链接
//                    getPresenter().getFaTaskBeanResult(memberId, taskType, name, require, activityImgUrl, startTime
//                            , endTime, unitPrice, taskImgUrl
//                            , taskNum, linkTitleStr, phonenumber, copywriting, Fans, ForwardUrl,discountRequestParamStr);


            /*    getPresenter().getFaTaskBeanResult(memberId, taskType,"", name, require, activityImgUrl, startTime
                        , endTime, unitPrice, taskImgUrl
                        , taskNum, taskGuidance, phonenumber, copywriting, Fans, "");*/


            }
        });


        setViewData();//草稿数据设置到界面上

    }


    /**
     * 初始化选择渠道控件
     */
    private void initSelectChannelTabLayout() {
        tagLayoutAdapter = new TagAdapter<>(ReleaseTaskImgTextActivity.this, R.layout.layout_tag_item_release_task_img_text);
        tagLayoutAdapter.setSelected(0); // 默认选项


        selectChannelTagLayout.setTagCheckedMode(FlowTagDarenLayout.FLOW_TAG_CHECKED_SINGLE); // 选择方式
        selectChannelTagLayout.setAdapter(tagLayoutAdapter);
        selectChannelTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedIndexList) {


            }
        });

        List<String> dataMediaResourses = new ArrayList<>();
        dataMediaResourses.add("微信朋友圈");
        dataMediaResourses.add("QQ");
        dataMediaResourses.add("微博");
        tagLayoutAdapter.onlyAddAll(dataMediaResourses);
    }

    private void setPrice(BigDecimal sl, BigDecimal dj) {
        originalPriceTv.setText(sl.multiply(dj) + "");

        int compareToInt = sl.multiply(dj).compareTo(new BigDecimal(discountPrice));
        if ((compareToInt == 0 || compareToInt == 1) && !TextUtils.isEmpty(discountStr) && !discountStr.equals("1")) {//0:等于 1:大于  并且 折扣不等于null
            //用折扣
            isUseDiscount = true;
            fa_pyq_price_tv.setText(sl.multiply(dj).multiply(discount) + "");
        } else {
            isUseDiscount = false;
            fa_pyq_price_tv.setText(sl.multiply(dj) + "");
        }
    }


    @Override
    public void initData() {
        if (SharedPreferencesUtils.getInstance().getStartInitiationData() != null) {
            discountStr = SharedPreferencesUtils.getInstance().getStartInitiationData().getDiscount();
            if (!TextUtils.isEmpty(discountStr) && !discountStr.equals("1")) {
                discount = new BigDecimal(discountStr);
                discountTv.setText(discount.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString() + "折" + " （200起才能使用折扣）");//去无用的零 stripTrailingZeros().toPlainString()
            } else {
                discountStr = "";
                discount = new BigDecimal(1);
                discountTv.setText("无");
            }
        }
    }

    @NonNull
    @Override
    public FaTaskPresenter createPresenter() {
        return new FaTaskPresenter(getApp());
    }

    @Override // 发任务
    public void onGetFaTaskBeanModels(FaTaskBean faTaskBean) {

        if ("000".equals(faTaskBean.getCode())) {
            faTaskBeanResult = faTaskBean.getData();
            taskId = faTaskBean.getData().getTaskId();


            //获取任务id推广金支付
            if (payType == 1) {

                getPresenter().promotionPayOrder(fa_pyq_price_tv.getText().toString(), taskId);
                // Log.e("A", "推广金支付" + fa_pyq_price_tv.getText().toString() + "--1--" + taskId);

            } else {
                //支付预付订单
                getPresenter().getAlipayOrderInfoResult(fa_pyq_price_tv.getText().toString(), name);
                //  Log.e("A", "支付宝支付" + fa_pyq_price_tv.getText().toString() + "--2--" + name);
            }


        } else {
            String failStr = "发布失败";
            if (!TextUtils.isEmpty(faTaskBean.getDescription())) {
                failStr = failStr + ":" + faTaskBean.getDescription();
            }
            showToast(failStr);
        }
    }



    /**
     * 预付支付宝订单
     *
     * @param faTaskSuccessBean
     */
    @Override
    public void onGetAlipayOrderInfoSuccessBeanModels(FaTaskSuccessBean faTaskSuccessBean) {

        if ("000".equals(faTaskSuccessBean.getCode())) {
            orderString = faTaskSuccessBean.getData().getOrderString(); // 支付宝预付订单信息
            Log.e("预付订单", "预付订单: " + orderString);
            payV2(orderString);

        }
    }


    @Override // 任务付款
    public void onGetFaTaskSuccessafterBeanModels(FaTaskSuccessafterBean faTaskSuccessafterBean) {

        if ("000".equals(faTaskSuccessafterBean.getCode())) {
            Toast.makeText(ReleaseTaskImgTextActivity.this, "支付成功！", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     * 这里获取钱包佣金的余额
     *
     * @param moneyInfoBean
     */
    @Override
    public void onGetBrokerage(MoneyInfoBean moneyInfoBean) {

        if ("000".equals(moneyInfoBean.getCode())) {

            brokerage = Double.valueOf(moneyInfoBean.getData().getProSY());

        }
    }

    /**
     * 推广金支付完成的方法
     *
     * @param baseBean
     */
    @Override
    public void onGetPromotionPaySuccess(PromotionPayResultBean baseBean) {

        if ("000".equals(baseBean.getCode())) {

            Toast.makeText(ReleaseTaskImgTextActivity.this, baseBean.getDescription(), Toast.LENGTH_SHORT).show();
            finish();
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

        fa_pyq_imagerv.setLayoutManager(new GridLayoutManager(ReleaseTaskImgTextActivity.this, 1));
        fa_pyq_imagerv.setHasFixedSize(true);
        adapter.setLayoutResId(R.layout.layout_release_task_img_text, R.drawable.bg_upload_img_cover);
        fa_pyq_imagerv.setAdapter(adapter);
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
                                Intent intent = new Intent(ReleaseTaskImgTextActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(ReleaseTaskImgTextActivity.this, ImageGridActivity.class);
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
                Intent intentPreview = new Intent(ReleaseTaskImgTextActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private void initSecondImage() {

        FullyGridLayoutManager manager = new FullyGridLayoutManager(ReleaseTaskImgTextActivity.this, 3
                , GridLayoutManager.VERTICAL, false);
        fa_pyq_image_nanerv.setLayoutManager(manager);


        gridImageAdapter = new GridImageAdapter(ReleaseTaskImgTextActivity.this, onAddPicClickListener);
        gridImageAdapter.setLayoutId(R.layout.layout_rv_upload_img);
        gridImageAdapter.setDefaultImgRes(R.drawable.addimg_1x);
        gridImageAdapter.setList(selectList);
        gridImageAdapter.setSelectMax(9);

        fa_pyq_image_nanerv.setAdapter(gridImageAdapter);

        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片
                        PictureSelector.create(ReleaseTaskImgTextActivity.this).externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(ReleaseTaskImgTextActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(ReleaseTaskImgTextActivity.this).externalPictureAudio(media.getPath());
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
            names.add("拍照");
            names.add("相册");
            showDialog(new SelectDialog.SelectDialogListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        // 单独拍照
                        PictureSelector.create(ReleaseTaskImgTextActivity.this)
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
                        PictureSelector.create(ReleaseTaskImgTextActivity.this)
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

    private void showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(ReleaseTaskImgTextActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!ReleaseTaskImgTextActivity.this.isFinishing()) {
            dialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {

                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {

                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);

                    // 进行判断
                    showLoadingDialog();
                    upload(images.get(0).path, -1);

                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {

            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {


                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);

                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    if (selImageList.size() == 0) {
                        activityImgUrl = "";
                    }
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                if (current == 0) {
                    // 图片选择
                    mselectList1 = PictureSelector.obtainMultipleResult(data);
                    selectList.clear();
                    selectList = mselectList1;
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();

                    // 进行判断
                    showLoadingDialog();
                    for (int i = 0; i < selectList.size(); i++) {
                        upload(selectList.get(i).getPath(), i);
                    }
                } else {
                    // 图片选择
                    imagelist.clear();
                    mselectList2 = PictureSelector.obtainMultipleResult(data);

                    //不是下列格式的数据都过滤掉
                    Iterator<LocalMedia> localMediaIterator = mselectList2.iterator();
                    while (localMediaIterator.hasNext()) {
                        LocalMedia localMedia = localMediaIterator.next();
                        if (!("image/jpeg".equals(localMedia.pictureType) ||
                                "image/jpg".equals(localMedia.pictureType) ||
                                "image/gif".equals(localMedia.pictureType) ||
                                "image/png".equals(localMedia.pictureType))) {
                            Toast.makeText(ReleaseTaskImgTextActivity.this, "上传的格式不合法！", Toast.LENGTH_SHORT).show();
                            localMediaIterator.remove();
                        }
                    }

                    selectList.clear();
                    selectList = mselectList2;
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();

                    // 进行判断
                    if (selectList.size() > 0) {
                        showLoadingDialog();
                    }
                    for (int i = 0; i < selectList.size(); i++) {
                        upload(selectList.get(i).getPath(), i);
                    }
                }
            }
        }


    }

    /**
     * @param fileUrl 图片原始路径
     * @param index   缩略图
     */
    private void upload(String fileUrl, final int index) {

        //切割文件名
        String key = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);
        //上传配置
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(10) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
                .zone(FixedZone.zone2) // 设置区域，指默认 Zone.zone0 注：这步是最关键的 当初错的主要原因也是他 根据自己的地方选
                .build();
        UploadManager uploadManager = new UploadManager(config);

        uploadManager.put(fileUrl, key, App.QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo,
                                 JSONObject jsonObject) {
                if (responseInfo.isOK()) {

                    try {
                        String upimg = jsonObject.getString("key");

                        imageurl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;
                        if (index == -1) {
                            //表示活动缩略图
                            activityImgUrl = imageurl;
                        } else {
                            if (!imagelist.contains(imageurl)) {
                                imagelist.add(imageurl);
                            }

                        }
                        Log.e("qiniu++++", "complete: " + jsonObject + imageurl);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("failssss", s + responseInfo + jsonObject + imageurl);
                }
                if (index == -1 || (index >= 0 && selectList.size() - 1 == index)) {
                    dismissLoadingDialog();
                }

            }
        }, null);
    }


    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseTaskImgTextActivity.this);
        builder.setMessage("最多选择3个");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    // 支付宝支付业务
    public void payV2(final String orderString) {

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(ReleaseTaskImgTextActivity.this);
                Map<String, String> result = alipay.payV2(orderString, true);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseTaskImgTextActivity.this);
        builder.setMessage("请进行支付宝申请授权");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = new Intent(ReleaseTaskImgTextActivity.this, MyjibenActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    /**
     * 获取控件上填写的数据
     */
    private void getViewData() {
        memberId = App.getId();   // 用户id

        name = fa_pyq_titlet.getText().toString();         // 活动标题


        phonenumber = "";                                     // 手机号码

        if (current == 0) {
            //链接任务
            taskType = "5";      // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他 5朋友圈转发链接
        } else {
            taskType = "0";      // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他 5朋友圈转发链接
            unitPrice = fa_pyq_djet.getText().toString();        // 单价
            taskNum = fa_pyq_slet.getText().toString();          // 投放数量
            require = taskRequireTv.getText().toString(); // 任务要求/商家需求
        }
        //链接
        mselectList1 = new ArrayList<>();


        //图文
        mselectList2 = new ArrayList<>();
        taskGuidance = fa_pyq_ydet.getText().toString();     // 活动引导
        copywriting = fa_pyq_waet.getText().toString();       // 朋友圈文案


/*        fa_pyq_ydet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    fa_pyq_ydet.setTextColor(getColor(R.color.black));
                    Log.e("AAAAAAAA","AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                }
                return false;
            }
        });*/

        taskImgUrl = "";
        if (imagelist != null) {
            for (int i = 0; i < imagelist.size(); i++) {
                taskImgUrl += imagelist.get(i);
                if (i != imagelist.size() - 1) {
                    taskImgUrl += ",";

                }
            }
        }

    }

    @OnClick({R.id.toolbar_menu_title, R.id.release_task_img_text_preview_task_tv})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu_title:
                //保存草稿
                getViewData();
                TaskDraftBean taskDraftBean = new TaskDraftBean();
                taskDraftBean.setActivityTitle(name);
                taskDraftBean.setStartTime(fa_pyq_start.getText().toString());
                taskDraftBean.setEndTime(fa_pyq_end.getText().toString());
                taskDraftBean.setStartTimeComplete(startTime);
                taskDraftBean.setEndTimeComplete(endTime);
                taskDraftBean.setSelectType(current);


                taskDraftBean.setTaskGuide(taskGuidance);
                taskDraftBean.setTaskRequireLike(taskRequireTv.getText().toString());
                taskDraftBean.setWechatCopywriting(copywriting);

                //活动缩略图
                taskDraftBean.setActivityImgList(selImageList);
                taskDraftBean.setActivityImgUrl(activityImgUrl);

                //链接封面/图片附件
                taskDraftBean.setImageUrlList(imagelist);
                taskDraftBean.setSelectImageList(selectList);

                SharedPreferencesUtils.getInstance().setTaskToDraft(taskDraftBean);
                showToast("保存草稿成功");
                finish();

                break;

            case R.id.release_task_img_text_preview_task_tv:
                //预览任务
                getViewData();//获取界面上用户填写的数据

                if (activityImgUrl == null) {
                    Toast.makeText(getApplication(), "请选择活动缩略图！", Toast.LENGTH_LONG).show();
                    return;
                } else if (name.length() <= 0) {
                    Toast.makeText(getApplication(), "请输入活动标题！", Toast.LENGTH_LONG).show();
                    return;
                } else if (startTime.length() <= 0) {
                    Toast.makeText(getApplication(), "请选择开始时间！", Toast.LENGTH_LONG).show();
                    return;
                } else if (endTime.length() <= 0) {
                    Toast.makeText(getApplication(), "请选择结束时间！", Toast.LENGTH_LONG).show();
                    return;
                } else if (taskGuidance.length() <= 0 && current != 0) {
                    Toast.makeText(getApplication(), "请输入活动引导！", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(taskImgUrl)) {

                    Toast.makeText(getApplication(), "请选择图片！", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(require)) {
                    Toast.makeText(getApplication(), "请输入任务要求！", Toast.LENGTH_LONG).show();
                    return;
                } else if (copywriting.length() <= 0 && current != 0) {
                    Toast.makeText(getApplication(), "请输入朋友圈文案！", Toast.LENGTH_LONG).show();
                    return;
                } else if (unitPrice.length() <= 0) {
                    Toast.makeText(getApplication(), "请输入单价！", Toast.LENGTH_LONG).show();
                    return;
                } else if (taskNum.length() <= 0) {
                    Toast.makeText(getApplication(), "请输入投放数量！", Toast.LENGTH_LONG).show();
                    return;
                }

                showPreviewTaskDialog();

                break;
        }


    }

    public void setViewData() {
        if (SharedPreferencesUtils.getInstance().getTaskToDraft() != null) {
            TaskDraftBean taskDraftBean = SharedPreferencesUtils.getInstance().getTaskToDraft();
            fa_pyq_titlet.setText(taskDraftBean.getActivityTitle());
            fa_pyq_start.setText(taskDraftBean.getStartTime());
            startTime = taskDraftBean.getStartTimeComplete();
            fa_pyq_end.setText(taskDraftBean.getEndTime());
            endTime = taskDraftBean.getEndTimeComplete();


            maxSelectNum = 9;
            fa_pyq_djet.setText("");
            fa_pyq_slet.setText("");
            selectList.clear();
            imagelist.clear();


            fa_pyq_ydet.setText(taskDraftBean.getTaskGuide());
            taskRequireTv.setText(taskDraftBean.getTaskRequireLike());
            fa_pyq_waet.setText(taskDraftBean.getWechatCopywriting());

            if (!TextUtils.isEmpty(taskDraftBean.getActivityImgUrl()) && taskDraftBean.getActivityImgList() != null
                    && taskDraftBean.getActivityImgList().size() > 0) {

                selImageList.clear();
                selImageList.addAll(taskDraftBean.getActivityImgList());
                adapter.setImages(selImageList);

                activityImgUrl = taskDraftBean.getActivityImgUrl();
            }

            if (taskDraftBean.getImageUrlList() != null
                    && taskDraftBean.getImageUrlList().size() > 0
                    && taskDraftBean.getSelectImageList() != null
                    && taskDraftBean.getSelectImageList().size() > 0) {

                selectList = taskDraftBean.getSelectImageList();
                gridImageAdapter.setList(selectList);
                gridImageAdapter.notifyDataSetChanged();

                imagelist = taskDraftBean.getImageUrlList();
            }
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ReleaseTaskImgTextActivity.class);
        context.startActivity(starter);
    }

    @SuppressLint("SetTextI18n")
    public void showPreviewTaskDialog() {


        final Dialog dialog = new Dialog(this, R.style.DialogStyle);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_lead_task_wechat, null);
        dialog.setContentView(view);

        view.findViewById(R.id.lead_task_wechat_top_progress).setVisibility(View.GONE);
        view.findViewById(R.id.coypTv).setVisibility(View.GONE);
        view.findViewById(R.id.lead_task_wechat_submit_tv).setVisibility(View.GONE);
        view.findViewById(R.id.place_order_task_ll).setVisibility(View.VISIBLE);
        ((Toolbar) view.findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.place_order_task_submit_tv).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                String amount = fa_pyq_price_tv.getText().toString();

                final Dialog mPayDialog = new Dialog(ReleaseTaskImgTextActivity.this, R.style.DialogStyle);
                mPayDialog.setCanceledOnTouchOutside(true); //手指触碰到外界取消
                mPayDialog.setCancelable(true);             //可取消 为true
                Window window = mPayDialog.getWindow();      // 得到dialog的窗体
                Objects.requireNonNull(window).setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.share_animation);

                View view1 = getLayoutInflater().inflate(R.layout.layout_dialog_pay_task, null);
                view1.findViewById(R.id.close_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPayDialog.dismiss();
                    }
                });
                TextView amountTv = view1.findViewById(R.id.amount_tv);
                amountTv.setText("￥" + amount);
                RadioButton r1 = view1.findViewById(R.id.r1);
                // RadioButton r2 = view1.findViewById(R.id.r2);
                r1.setText("推广佣金  " + brokerage);

                BigDecimal data1 = new BigDecimal(amount);
                RadioGroup radioGroup = view1.findViewById(R.id.pay_type_rg);

                //支付金额大于佣金转到支付宝支付  1 推广金 2 支付宝
                if (data1.compareTo(new BigDecimal(brokerage)) > 0) {
                    radioGroup.check(R.id.r2);
                    r1.setClickable(false);
                    r1.setTextColor(getResources().getColor(R.color.alpha_40_black));
                    payType = 2;
                } else {
                    radioGroup.check(R.id.r1);
                    payType = 1;
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.r1:
                                payType = 1;
                                break;
                            case R.id.r2:
                                payType = 2;
                                break;
                            default:
                                break;
                        }

                    }
                });

                Button payBt = view1.findViewById(R.id.pay_bt);
                payBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //调用发任务的接口
                        getPresenter().getFaTaskBeanResult(memberId, taskType, tagLayoutAdapter.position + "", name, require, activityImgUrl, startTime
                                , endTime, unitPrice, taskImgUrl
                                , taskNum, taskGuidance, phonenumber, copywriting, Fans, "");
                        mPayDialog.dismiss();

                    }
                });
                window.setContentView(view1);
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
                mPayDialog.show();

            }
        });


        TextView toolbarTitleTv = view.findViewById(R.id.toolbar_title);
        TextView releaseNameTv = view.findViewById(R.id.releasefNameTv);
        ImageView releaseImg = view.findViewById(R.id.releasefImg);
        TextView friendCircleTv = view.findViewById(R.id.friendCircleTv);
        TextView releaseDownloadValueTv1 = view.findViewById(R.id.releasefDownloadValueTv1);
        TextView releaseMoneyTv = view.findViewById(R.id.releasefMoneyValueTv);
        releaseMoneyTv.setText((Double.valueOf(unitPrice) * 10) + "");

        TextView activityRequireTv = view.findViewById(R.id.lead_task_wechat_task_requirement_tv);
        TextView activityGuideTv = view.findViewById(R.id.lead_task_wechat_activity_guide_tv);
        TextView activityTimeValueTv = view.findViewById(R.id.activityTimeValueTv);
        TextView taskStatusTv = view.findViewById(R.id.lead_task_wechat_task_status_tv);
        TextView releaseCopywritingTv = view.findViewById(R.id.releasefCopywritingTv);
        RecyclerView releasefValueRv = view.findViewById(R.id.releasefValueRv);
        TextView priceTv = view.findViewById(R.id.place_order_task_price_tv);

        toolbarTitleTv.setText("预览任务效果");
        GlideUtils.loadRoundedImageView(this, activityImgUrl, releaseImg, R.color.gainsboro);
        releaseNameTv.setText(name);
        friendCircleTv.setText(SharedPreferencesUtils.getInstance().getNickName());
        releaseDownloadValueTv1.setText(copywriting);
        taskStatusTv.setText("任务总数：" + taskNum);
        releaseCopywritingTv.setText("图文文案");
        priceTv.setText(fa_pyq_price_tv.getText());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(endTime);
            long endTimeLong = date.getTime() - System.currentTimeMillis();
            activityTimeValueTv.setText(CommonUtils.getTimeStampDiffer(endTimeLong));//活动倒计时
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //任务要求
        if (!StringUtils.isNullOrEmpty(require)) {
            activityRequireTv.setText(require);
        }
        if (!StringUtils.isNullOrEmpty(taskGuidance)) {
            activityGuideTv.setText(taskGuidance);
        }

        // init 配图 RecyclerView
        releasefValueRv.setHasFixedSize(true);
        releasefValueRv.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        releasefValueRv.addItemDecoration(new GridSpacingItemDecoration(3, Utils.dip2px(15f, this), Utils.dip2px(15f, this), false));
        releasefValueRv.setLayoutManager(gridLayoutManager);
        FriendReleaseAdapter friendReleaseAdapter = new FriendReleaseAdapter(this);
        releasefValueRv.setAdapter(friendReleaseAdapter);
        //set data
        friendReleaseAdapter.setDataList(imagelist);

        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        for (String url : imagelist) {
            ImageItem imageItem = new ImageItem();
            imageItem.mimeType = "copy";
            imageItem.path = url;
            imageItems.add(imageItem);
            Log.e("135", "url = " + url);
        }
        friendReleaseAdapter.setOnItemClickListener(new FriendReleaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intentPreview = new Intent(ReleaseTaskImgTextActivity.this, ImagePreviewCopyActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, imageItems);
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, pos);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivity(intentPreview);
            }
        });
        friendReleaseAdapter.notifyDataSetChanged();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams dialogParams = window.getAttributes();
        dialogParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(dialogParams);

        dialog.show();


    }


}

