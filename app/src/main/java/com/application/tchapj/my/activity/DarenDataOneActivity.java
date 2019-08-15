package com.application.tchapj.my.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.DataManager;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.bean.DarenDataBean;
import com.application.tchapj.my.bean.DarenDataOneBean;
import com.application.tchapj.my.presenter.DarenOnePresenter;
import com.application.tchapj.my.view.IDarenOneView;
import com.application.tchapj.utils.CommonDialogListenerUtil;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.Verification;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.widiget.DensityUtil;
import com.application.tchapj.widiget.FlowTagDarenLayout;
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.KV;
import com.application.tchapj.widiget.LogUtils;
import com.application.tchapj.widiget.MustWriteLinearLayout;
import com.application.tchapj.widiget.OnTagSelectListener;
import com.application.tchapj.widiget.TagAdapter;
import com.application.tchapj.widiget.TagDarenAdapter;
import com.application.tchapj.widiget.ToolbarHelper;
import com.iflytek.cloud.thirdparty.U;
import com.king.base.util.StringUtils;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;

// 达人资料第一页
public class DarenDataOneActivity extends BaseMvpActivity<IDarenOneView, DarenOnePresenter> implements IDarenOneView
        , ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.daren_one_head_img_rv)
    RecyclerView headImgRv;
    @BindView(R.id.darendataone_media_resources_rv)
    RecyclerView darendataone_media_resources_rv;
    @BindView(R.id.daren_one_name_et)
    EditText daren_one_name_et;
    @BindView(R.id.daren_one_jieshao_et)
    EditText daren_one_jieshao_et;

    @BindView(R.id.darendata_rg)
    RadioGroup darendata_rg;
    @BindView(R.id.darendata_rb_nan)
    RadioButton darendata_rb_nan;
    @BindView(R.id.darendata_rb_nv)
    RadioButton darendata_rb_nv;

    @BindView(R.id.daren_one_realname_et)
    EditText daren_one_realname_et;
    @BindView(R.id.daren_one_sfz_et)
    EditText daren_one_sfz_et;

    @BindView(R.id.daren_fl)
    FlowTagDarenLayout daren_fl;
    @BindView(R.id.darendataone_media_resources_fl)
    FlowTagLayout darenResourcesFlowTagl;

    @BindView(R.id.daren_one_next)
    Button daren_one_next;
    @BindView(R.id.darendataone_media_resources_price_et)
    EditText darendataone_media_resources_price_et;
    @BindView(R.id.permanent_address_city_name_tv)
    TextView permanentCityNameTv;//常驻城市
    @BindView(R.id.writeLl)
    MustWriteLinearLayout writeLinearLayout;
    @BindView(R.id.daren_one_invite_et)
    EditText inviteCodeEdt;


    private TagDarenAdapter<String> mSizeTagAdapter;
    private TagAdapter<String> tagResoursesAdapter;

    private String cityNameStr;
    private int cityId = -1;

    private String phoneNumber;
    private String sfzStr;
    private String imageurl = "";
    private String mediaImageUrl = "";//底部媒体资源图片

    private ArrayList<ImageItem> images = null;
    private ArrayList<ImageItem> mediaResourcesImages = null;
    private ImagePickerAdapter adapter;        // 头像适配器
    private ImagePickerAdapter mediaResourcesAdapter;        // 媒体资源图片适配器
    private ArrayList<ImageItem> selImageList; // 当前选择的所有图片
    private ArrayList<ImageItem> mediaResourcesSelImageList; // 当前选择的所有图片
    private int maxImgCount = 1;               //允许选择图片最大数
    private int mediaResourcesMaxImgCount = 1;               //允许选择图片最大数

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int MEDIA_RESOURCES_REQUEST_CODE_SELECT = 102;
    public static final int MEDIA_RESOURCES_REQUEST_CODE_PREVIEW = 103;

    private String Id;
    private String ResourcesTypeId = "0";
    private String XwhName;
    private String headimageUrl;
    private String NameNicheng;
    private String Jieshao;
    private String Realname;
    private String Sex = "1";
    private String Position1 = "";
    private String Position2 = "";
    private String Position3 = "";
    private String PositionId = "";
    private String catType = "0";  // 0 朋友圈 1 微博 2 抖音 3 微视 4 其他
    private String Industry = "0";

    UploadManager uploadManager;

    public List<HomeCircleModel.HomeCircleModelResult.HomeCircle> circles;

    private List<String> positionlist = new ArrayList<>();

    private DarenDataOneBean.DarenDataOneBeanResult darenDataOneBeanResult = new DarenDataOneBean.DarenDataOneBeanResult();


    //添加媒体资源图片选择
    @BindView(R.id.darendataone_media_resources_tip_tv)
    TextView darendataone_media_resources_tip_tv;
    private List<LocalMedia> videoSelectList = new ArrayList<>();


    ImagePickerAdapter.OnRecyclerViewItemClickListener mediaResourcesRvItemClick = new ImagePickerAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            showExampleImgDialog(position);

        }
    };

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("达人资料");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_darendataone;
    }

    @Override
    public void initUI() {

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(DarenDataOneActivity.this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        mediaResourcesSelImageList = new ArrayList<>();
        mediaResourcesAdapter = new ImagePickerAdapter(DarenDataOneActivity.this, mediaResourcesSelImageList,
                mediaResourcesMaxImgCount);
        mediaResourcesAdapter.setLayoutResId(R.layout.layout_item_upload_img, R.drawable.icon_upload_img);
        mediaResourcesAdapter.setOnItemClickListener(mediaResourcesRvItemClick);
        initImagePicker();
        initWidget();

        darendata_rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == darendata_rb_nan.getId()) {
                    Sex = "1";
                } else if (checkedId == darendata_rb_nv.getId()) {
                    Sex = "0";
                }

            }
        });

        // 多选
        mSizeTagAdapter = new TagDarenAdapter<>(DarenDataOneActivity.this);
        mSizeTagAdapter.setSelected(-1); // 默认选项
        daren_fl.setTagCheckedMode(FlowTagDarenLayout.FLOW_TAG_CHECKED_MULTI); // 选择方式
        daren_fl.setAdapter(mSizeTagAdapter);
        daren_fl.setOnTagSelectListener(new FlowTagDarenLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagDarenLayout parent, int position, List<Integer> selectedIndexList) {
                positionlist.clear();
                if (selectedIndexList != null && selectedIndexList.size() > 0) {
                    for (int i : selectedIndexList) {
                        positionlist.add(i + 1 + "");
                    }


                }

            }
        });


        tagResoursesAdapter = new TagAdapter<>(DarenDataOneActivity.this, R.layout.layout_tag_item_daren_media_resource);
        tagResoursesAdapter.setSelected(0); // 默认选项
        darenResourcesFlowTagl.setTagCheckedMode(FlowTagDarenLayout.FLOW_TAG_CHECKED_SINGLE); // 选择方式
        darenResourcesFlowTagl.setAdapter(tagResoursesAdapter);
        darenResourcesFlowTagl.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedIndexList) {
                if (position == 0) {
                    //朋友圈
                    darendataone_media_resources_tip_tv.setText("请上传微信-通讯录截图，会公开展示到圈子名片中");
                    catType = "0";
                } else if (position == 1) {
                    //微博
                    darendataone_media_resources_tip_tv.setText("请上传微博-我的界面，截图会公开展示在您的圈子名片中");
                    catType = "1";
                } else if (position == 2) {
                    //微视
                    darendataone_media_resources_tip_tv.setText("请上传微视-我的界面截图，会公开展示到您的圈子名片中");
                    catType = "3";
                } else if (position == 3) {
                    //抖音
                    darendataone_media_resources_tip_tv.setText("请上传抖音-我的界面截图，会公开展示在您的圈子名片中");
                    catType = "2";
                } else if (position == 4) {
                    //其他
                    darendataone_media_resources_tip_tv.setText("请用图片或视频展示你的特色或优势，会展示到圈子名片中");
                    catType = "4";
                }

                if (position == 4) {
                    mediaResourcesAdapter.setLayoutResId(-1, R.mipmap.icon_selector_image_add);
                    // mediaResourcesExampleTv.setVisibility(View.GONE);//其他媒体类型，隐藏截图示例按钮
                } else {
                    mediaResourcesAdapter.setLayoutResId(R.layout.layout_item_upload_img, R.drawable.icon_upload_img);
                    // mediaResourcesExampleTv.setVisibility(View.VISIBLE);
                }

            }
        });

        initTagLayoutData();

        daren_one_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realname = daren_one_realname_et.getText().toString();
                Jieshao = daren_one_jieshao_et.getText().toString();
                NameNicheng = daren_one_name_et.getText().toString();
                sfzStr = daren_one_sfz_et.getText().toString();
                String mediaPrice = darendataone_media_resources_price_et.getText().toString();

                String inviteCodeStr = inviteCodeEdt.getText().toString();

                if (positionlist != null && positionlist.size() > 0) {
                    if (positionlist.size() == 1) {
                        Position1 = positionlist.get(0);
                        PositionId = Position1;
                    } else if (positionlist.size() == 2) {
                        Position1 = positionlist.get(0);
                        Position2 = positionlist.get(1);
                        PositionId = Position1 + "," + Position2;
                    } else if (positionlist.size() == 3) {
                        Position1 = positionlist.get(0);
                        Position2 = positionlist.get(1);
                        Position3 = positionlist.get(2);
                        PositionId = Position1 + "," + Position2 + "," + Position3;
                    }
                } else {
                    PositionId = "";
                }


                if (StringUtils.isEmpty(headimageUrl)) {
                    Toast.makeText(getApplication(), "请选择头像", Toast.LENGTH_LONG).show();
                    return;
                } else if (StringUtils.isEmpty(NameNicheng)) {
                    Toast.makeText(getApplication(), "请输入昵称", Toast.LENGTH_LONG).show();
                    return;
                } else if (StringUtils.isEmpty(Sex)) {
                    Toast.makeText(getApplication(), "请选择性别", Toast.LENGTH_LONG).show();
                    return;
                } else if (cityId == -1) {
                    ToastUtil.show(DarenDataOneActivity.this, "请选择常驻区域");
                    return;
                } else if (StringUtils.isEmpty(PositionId)) {
                    Toast.makeText(DarenDataOneActivity.this, "请选择您的圈子", Toast.LENGTH_SHORT).show();
                    return;
                } else if (StringUtils.isEmpty(mediaImageUrl)) {
                    Toast.makeText(getApplication(), "请输入媒体资源图片！", Toast.LENGTH_LONG).show();
                    return;
                }


                if (!StringUtils.isEmpty(sfzStr)) {
                    boolean isSfz = Verification.verifySfz(sfzStr);
                    if (!isSfz) {
                        Toast.makeText(DarenDataOneActivity.this, "请正确填写身份证号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // 上传达人资料


                getPresenter().getDarenDataOneBeanResult(App.getId(), PositionId, Realname, Sex, ""
                        , "", sfzStr, Jieshao, headimageUrl, NameNicheng, catType, mediaPrice, mediaImageUrl, cityId + "", inviteCodeStr);


            }
        });


    }

    @Override
    public void initData() {


    }

    @NonNull
    @Override
    public DarenOnePresenter createPresenter() {
        return new DarenOnePresenter(getApp());
    }


    @Override
    public void onGetSmsCodeResult(SmsCodeResponse loginResultBean) {

    }

    @Override // 达人资料
    public void onGetDarenDataOneBeanResult(DarenDataOneBean darenDataOneBean) {
        if ("000".equals(darenDataOneBean.getCode())) {
            darenDataOneBeanResult = darenDataOneBean.getData();

            getDataManager().setMetaDataById(R.string.taskApplyId, darenDataOneBeanResult.getTaskApplyId(), true);
            // App.TaskApplyId = darenDataOneBeanResult.getTaskApplyId();//达人的id下一步操作要用
            ToastUtil.show(this, "提交成功");

            Log.i("sssss", darenDataOneBeanResult.getTaskApplyId() + "");
            finish();
        } else {
            ToastUtil.show(this, darenDataOneBean.getDescription());
        }

    }

    @Override // 上传达人频道资料
    public void onGetDarenDataBeanResult(DarenDataBean darenDataBean) {

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
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
//        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(1000);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1000);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {

        headImgRv.setLayoutManager(new GridLayoutManager(DarenDataOneActivity.this, 1));
        headImgRv.setHasFixedSize(true);
        headImgRv.setAdapter(adapter);

        darendataone_media_resources_rv.setLayoutManager(new GridLayoutManager(DarenDataOneActivity.this, 1));
        darendataone_media_resources_rv.setHasFixedSize(true);
        darendataone_media_resources_rv.setAdapter(mediaResourcesAdapter);
    }

    //头像图片选择
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
                                Intent intent = new Intent(DarenDataOneActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(DarenDataOneActivity.this, ImageGridActivity.class);
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
                Intent intentPreview = new Intent(DarenDataOneActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(DarenDataOneActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!DarenDataOneActivity.this.isFinishing()) {
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
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);

                    // 进行判断
                    showLoadingDialog();
                    for (int i = 0; i < images.size(); i++) {
                        upload(images.get(i).path, requestCode);
                    }

                }

            } else if (data != null && requestCode == MEDIA_RESOURCES_REQUEST_CODE_SELECT) {
                mediaResourcesImages = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (mediaResourcesImages != null) {
                    mediaResourcesSelImageList.clear();
                    mediaResourcesSelImageList.addAll(mediaResourcesImages);
                    mediaResourcesAdapter.setImages(mediaResourcesSelImageList);


                    showLoadingDialog();
                    // 进行判断
                    for (int i = 0; i < mediaResourcesSelImageList.size(); i++) {
                        upload(mediaResourcesSelImageList.get(i).path, requestCode);
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
            } else if (data != null && requestCode == MEDIA_RESOURCES_REQUEST_CODE_PREVIEW) {
                mediaResourcesImages = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (mediaResourcesImages != null) {
                    mediaResourcesSelImageList.clear();
                    mediaResourcesSelImageList.addAll(mediaResourcesImages);
                    mediaResourcesAdapter.setImages(mediaResourcesSelImageList);
                }
            }
        } else if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            cityNameStr = data.getStringExtra("city_name");
            cityId = data.getIntExtra("city_id", -1);
            if (!StringUtils.isEmpty(cityNameStr)) {
                permanentCityNameTv.setText(cityNameStr);
            }
        }

    }

    private void upload(String fileUrl, final int tag) {

        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone2)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();

        if (uploadManager == null) {
            uploadManager = new UploadManager(config);
        }


        uploadManager.put(fileUrl, null, App.QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo,
                                 JSONObject jsonObject) {
                if (responseInfo.isOK()) {

                    Log.e("success", "complete:" + responseInfo + jsonObject);

                    try {
                        String upimg = jsonObject.getString("key");
                        String url = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;
                        if (tag == REQUEST_CODE_SELECT) {
                            //上传头像返回
                            headimageUrl = url;

                            Log.e("DOAING+++++++", headimageUrl);
                        } else {
                            //上传媒体资源图片返回
                            if (!StringUtils.isEmpty(mediaImageUrl) && !mediaImageUrl.contains(url)) {
                                mediaImageUrl += ",";
                            }
                            if (!mediaImageUrl.contains(url)) {
                                mediaImageUrl += url;
                            }


                            Log.e("DOAING--------", mediaImageUrl);
                        }

                        //  Log.e("qiniu++++", "complete: " + jsonObject + imageurl);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                }

                dismissLoadingDialog();

            }
        }, null);
    }

    // 初始化数据
    private void initTagLayoutData() {
        circles = App.circles;
        List<Integer> dataSource = new ArrayList<>();
        dataSource.add(R.drawable.icon_talent_baby_mother_normal);
        dataSource.add(R.drawable.icon_talent_artist_normal);
        dataSource.add(R.drawable.icon_talent_internet_celebrity_normal);
        dataSource.add(R.drawable.icon_talent_student_normal);
        dataSource.add(R.drawable.icon_talent_reporter_normal);
        dataSource.add(R.drawable.icon_talent_skill_normal);
        dataSource.add(R.drawable.icon_talent_elderly_normal);
        dataSource.add(R.drawable.icon_talent_wechat_business_normal);
        dataSource.add(R.drawable.icon_talent_other_normal);

        mSizeTagAdapter.onlyAddAll(dataSource);

        List<String> dataMediaResourses = new ArrayList<>();
        dataMediaResourses.add("朋友圈");
        dataMediaResourses.add("微博");
        dataMediaResourses.add("微视");
        dataMediaResourses.add("抖音");
        dataMediaResourses.add("其他");

        tagResoursesAdapter.onlyAddAll(dataMediaResourses);

    }

    @OnClick({R.id.permanent_address_city_rl/*, R.id.darendataone_media_resources_example_tv*/})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.permanent_address_city_rl:
                CityListActivity.start(this);
                break;
            case R.id.darendataone_media_resources_example_tv:
                //显示截图示例

                //TODO 这里的显示改动到 点击添加截图触发
                showExampleImgDialog(1);
                break;
            default:
                break;
        }
    }

    //显示媒体资源，截图示例dialog
    private void showExampleImgDialog(final int position) {

        //0朋友圈  1微博  2抖音  3微视
        final Dialog dialog = new Dialog(this, R.style.DialogStyle);
        View view = getLayoutInflater().inflate(R.layout.activity_darendataone_add_image, null);
        ImageView iv = view.findViewById(R.id.wechat_iv);
        Button button = view.findViewById(R.id.submit_area);

        switch (catType) {
            case "0":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_wechat);
                break;
            case "1":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_wb);
                break;
            case "2":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_dy);
                break;
            case "3":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_ws);
                break;

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                switch (position) {
                    case IMAGE_ITEM_ADD:
                        List<String> names = new ArrayList<>();
                        if ((!catType.equals("4")) || (mediaResourcesImages != null && mediaResourcesImages.size() > 0)) {
                            names.add("拍照");
                            names.add("相册");
                        } else {
                            names.add("拍照");
                            names.add("相册");
                            names.add("拍摄");
                            names.add("本地视频");
                        }
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
                                        ImagePicker.getInstance().setSelectLimit(mediaResourcesMaxImgCount - mediaResourcesSelImageList.size());
                                        Intent intent = new Intent(DarenDataOneActivity.this, ImageGridActivity.class);
                                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                        startActivityForResult(intent, MEDIA_RESOURCES_REQUEST_CODE_SELECT);
                                        break;
                                    case 1:
                                        //打开选择,本次允许选择的数量
                                        ImagePicker.getInstance().setSelectLimit(mediaResourcesMaxImgCount - mediaResourcesSelImageList.size());
                                        Intent intent1 = new Intent(DarenDataOneActivity.this, ImageGridActivity.class);

                                        /* 如果需要进入选择的时候显示已经选中的图片，
                                         * 详情请查看ImagePickerActivity
                                         * */

                                        intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, mediaResourcesImages);
                                        startActivityForResult(intent1, MEDIA_RESOURCES_REQUEST_CODE_SELECT);
                                        break;
                                    case 2:
                                        // 单独拍摄
                                        PictureSelector.create(DarenDataOneActivity.this)
                                                .openCamera(PictureMimeType.ofVideo())
                                                .theme(R.style.picture_default_style)
                                                .maxSelectNum(1)
                                                .minSelectNum(1)
                                                .selectionMode(PictureConfig.SINGLE)
                                                .previewImage(true)
                                                .previewVideo(false)
                                                .compressGrade(Luban.THIRD_GEAR)
                                                .isCamera(true)
                                                .glideOverride(160, 160)
                                                .isGif(true)
                                                .openClickSound(false)
                                                .selectionMedia(videoSelectList)
                                                .forResult(PictureConfig.CHOOSE_REQUEST);
                                        break;
                                    case 3:
                                        //本地视频
                                        PictureSelector.create(DarenDataOneActivity.this)
                                                .openGallery(PictureMimeType.ofVideo())
                                                .theme(R.style.picture_default_style)
                                                .maxSelectNum(1)
                                                .minSelectNum(1)
                                                .selectionMode(PictureConfig.SINGLE)
                                                .previewImage(true)
                                                .previewVideo(false)
                                                .compressGrade(Luban.THIRD_GEAR)
                                                .isCamera(true)
                                                .glideOverride(160, 160)
                                                .previewEggs(true)
                                                .isGif(true)
                                                .openClickSound(false)
                                                .selectionMedia(videoSelectList)
                                                .forResult(PictureConfig.CHOOSE_REQUEST);
                                        break;
                                }
                                darendataone_media_resources_rv.setLayoutManager(new GridLayoutManager(DarenDataOneActivity.this, 1));
                                mediaResourcesAdapter.setOnItemClickListener(mediaResourcesRvItemClick);
                                darendataone_media_resources_rv.setAdapter(mediaResourcesAdapter);

                            }
                        }, names);


                        break;
                    default:
                        //打开预览
                        Intent intentPreview = new Intent(DarenDataOneActivity.this, ImagePreviewDelActivity.class);
                        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) mediaResourcesAdapter.getImages());
                        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                        startActivityForResult(intentPreview, MEDIA_RESOURCES_REQUEST_CODE_PREVIEW);
                        break;
                }

            }
        });
        dialog.setContentView(view);

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dp2px(this, 70);
        layoutParams.height = wm.getDefaultDisplay().getHeight() - DensityUtil.dp2px(this, 70);
        dialogWindow.setAttributes(layoutParams);


        dialog.show();

    }


}
