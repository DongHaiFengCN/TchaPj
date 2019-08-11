package com.application.tchapj.task.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
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
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.OnTagSelectListener;
import com.application.tchapj.widiget.TagAdapter3;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2018\8\25 0025.
 */

public class TaskFaDyYcFragment extends BaseMvpFragment<IFaTaskView, FaTaskPresenter> implements IFaTaskView, ImagePickerAdapter.OnRecyclerViewItemClickListener{

    @BindView(R.id.fa_dyyc_imagerv)
    RecyclerView fa_dyyc_imagerv;

    @BindView(R.id.fa_dyyc_titilet)
    EditText fa_dyyc_titilet;
    @BindView(R.id.fa_dyyc_start)
    TextView fa_dyyc_start;
    @BindView(R.id.fa_dyyc_end)
    TextView fa_dyyc_end;

//    @BindView(R.id.fa_dyyc_fl)
//    FlowTagLayout fa_dyyc_fl;

    @BindView(R.id.fa_dyyc_vieoet)
    EditText fa_dyyc_vieoet;
    @BindView(R.id.fa_dyyc_shouji)
    EditText fa_dyyc_shouji;
    @BindView(R.id.fa_dyyc_jiagetv)
    EditText fa_dyyc_jiagetv;

    @BindView(R.id.fa_dyyc_bt)
    Button fa_dyyc_bt;

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

    // 流标签选择
    private String tabid;
    private TagAdapter3<String> mSizeTagAdapter;
    private List<String> positionlist = new ArrayList<>();
    public List<HomeCircleModel.HomeCircleModelResult.HomeCircle> circles;
    private String Position1="";
    private String Position2="";
    private String Position3="";

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

    private FaTaskBean.FaTaskBeanResult faTaskBeanResult = new FaTaskBean.FaTaskBeanResult();
    private String taskId;

    // 接收参数
    public static TaskFaDyYcFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        TaskFaDyYcFragment fragment = new TaskFaDyYcFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_fa_dyyctask;
    }

    @Override
    public void initUI() {

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getContext(), selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        initImagePicker();
        initWidget();

        // 多选
        mSizeTagAdapter = new TagAdapter3<>(getContext());
        mSizeTagAdapter.setSelected(-1); // 默认选项
/*        fa_dyyc_fl.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI); // 选择方式
        fa_dyyc_fl.setAdapter(mSizeTagAdapter);
        fa_dyyc_fl.setOnTagSelectListener(new OnTagSelectListener() {
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
        fa_dyyc_start.setOnClickListener(new View.OnClickListener() {
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

                        fa_dyyc_start.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
                    }
                });
                picker.show();
            }
        });

        fa_dyyc_end.setOnClickListener(new View.OnClickListener() {
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
                        fa_dyyc_end.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
                    }
                });
                picker.show();
            }
        });


        fa_dyyc_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                memberId = App.getId();   // 用户id
                taskType = "3";      // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
                name = fa_dyyc_titilet.getText().toString();         // 活动标题
                require = fa_dyyc_vieoet.getText().toString();       // 任务要求/商家需求

                startTime =fa_dyyc_start.getText().toString()+":00";       // 活动开始时间
                endTime = fa_dyyc_end.getText().toString()+":00";          // 活动结束时间

                unitPrice = fa_dyyc_jiagetv.getText().toString();        // 单价
                taskNum = "";          // 投放数量
                taskGuidance = "";     // 活动引导
                phonenumber = fa_dyyc_shouji.getText().toString();    // 手机号码
                copywriting = "";                                     // 朋友圈文案
                Fans = "";           // 要求粉丝数量

                imgUrl = imageurl;             // 活动缩略图
                taskImgUrl = "";               // 活动图片url或视频url

//                if(positionlist.size()==1){
//                    Position1 = positionlist.get(0);
//                    circleTypeId =Position1;
//                }else if(positionlist.size()==2){
//                    Position1 = positionlist.get(0);
//                    Position2 = positionlist.get(1);
//                    circleTypeId = Position1+","+Position2;
//                }if(positionlist.size()==3){
//                    Position1 = positionlist.get(0);
//                    Position2 = positionlist.get(1);
//                    Position3 = positionlist.get(2);
//                    circleTypeId = Position1+","+Position2+","+Position3;
//                }else {
//                    circleTypeId = "1";
//                }

                if (name.length() <= 0) {
                    Toast.makeText(getContext(),"请输入活动标题！", Toast.LENGTH_LONG).show();
                    return;
                }else if (startTime.length() <= 0) {
                    Toast.makeText(getContext(),"请选择开始时间！", Toast.LENGTH_LONG).show();
                    return;
                } else if (endTime.length() <= 0) {
                    Toast.makeText(getContext(),"请选择结束时间！", Toast.LENGTH_LONG).show();
                    return;
                }else if (unitPrice.length() <= 0) {
                    Toast.makeText(getContext(),"请输入单价！", Toast.LENGTH_LONG).show();
                    return;
                }else if (require.length() <= 0) {
                    Toast.makeText(getContext(),"请输入商家要求！", Toast.LENGTH_LONG).show();
                    return;
                } else if (phonenumber.length() <= 0) {
                    Toast.makeText(getContext(),"请输入手机号！", Toast.LENGTH_LONG).show();
                    return;
                } else if (imgUrl==null) {
                    Toast.makeText(getContext(),"请选择缩略图！", Toast.LENGTH_LONG).show();
                    return;
                }

                // 上传原创任务
                getPresenter().getFaTaskBeanResult(memberId,taskType,name,require,imgUrl,startTime
                        ,endTime,unitPrice,taskImgUrl
                        ,taskNum,taskGuidance,phonenumber,copywriting,Fans,"", "");


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
            taskId= faTaskBean.getData().getTaskId();

            Toast.makeText(getContext(), "发送成功！", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override // 预付订单
    public void onGetFaTaskSuccessBeanModels(FaTaskSuccessBean faTaskSuccessBean) {

    }

    @Override // 任务付款
    public void onGetFaTaskSuccessafterBeanModels(FaTaskSuccessafterBean faTaskSuccessafterBean) {

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

        fa_dyyc_imagerv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        fa_dyyc_imagerv.setHasFixedSize(true);
        fa_dyyc_imagerv.setAdapter(adapter);
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
                    for(int i=0;i<images.size();i++){
                        upload(images.get(i).path);
                    }

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

                    Log.e("success", "complete:"+responseInfo+jsonObject);

                    try {
                        String upimg = jsonObject.getString("key");

                        imageurl = "http://"+"qiniuyun2.ctrlmedia.cn/"+upimg;

                        Log.e("qiniu++++", "complete: "+jsonObject+imageurl);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("failssss", s + responseInfo + jsonObject+imageurl);
                }
                Log.e("qiniu", "complete: "+jsonObject+imageurl);

            }
        }, null);
    }

    // 初始化数据
    private void initSizeData() {
        circles = App.circles;
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < circles.size(); i++) {
            dataSource.add(circles.get(i).getName());
        }

        mSizeTagAdapter.onlyAddAll(dataSource);

    }

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

}
