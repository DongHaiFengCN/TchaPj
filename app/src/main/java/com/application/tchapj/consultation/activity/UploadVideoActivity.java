package com.application.tchapj.consultation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.base.BaseModel;

import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
import com.application.tchapj.utils2.pickers.util.LogUtils;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.iflytek.cloud.thirdparty.S;
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
import butterknife.OnClick;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 发布图文、小视频、长视频
 */
public class UploadVideoActivity extends BaseActvity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {


    public static final String TAG = UploadVideoActivity.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu_title)
    TextView toolbarMenuTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.upload_video_cancel_tv)
    TextView uploadVideoCancelTv;
    @BindView(R.id.upload_video_upload_tv)
    TextView uploadVideoUploadTv;
    //    @BindView(R.id.upload_video_ib)
//    ImageButton uploadVideoIb;
    @BindView(R.id.upload_video_content_edt)
    EditText uploadVideoContentEdt;
    @BindView(R.id.upload_video_title_edt)
    EditText uploadVideoTitleEdt;
    //上传图片
    @BindView(R.id.upload_img_rv)
    RecyclerView uploadImgRv;
    //上传视频
    @BindView(R.id.upload_video_rv)
    RecyclerView uploadVideoRv;

    Context mContext;

    //视频
    private GridImageAdapter gridImageAdapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 1;
    private int themeId;
    private int videoChooseMode = PictureMimeType.ofAll();
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> images = null;
    private ArrayList<ImageItem> selImageList; // 已选择的所有图片，本地路径


    //图片
    private int maxImgCount = 9;//允许选择图片最大数
    // 第一个图片选择
    private ImagePickerAdapter adapter;
    private String imageurl;
    private List<String> imagelist = new ArrayList<>();
    private List<String> videolist = new ArrayList<>();

    String type;
    private String contentStr;
    private List<String> uploadDialogNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        //视频
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 1
                , GridLayoutManager.VERTICAL, false);
        uploadVideoRv.setLayoutManager(manager);
        gridImageAdapter = new GridImageAdapter(mContext, onAddPicClickListener);
        gridImageAdapter.setLayoutId(R.layout.upload_video_rv_item_image);
        gridImageAdapter.setDefaultImgRes(R.drawable.icon_upload_video);
        gridImageAdapter.setList(selectList);
        gridImageAdapter.setSelectMax(maxSelectNum);
        uploadVideoRv.setAdapter(gridImageAdapter);
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
                        PictureSelector.create(UploadVideoActivity.this).externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(UploadVideoActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(UploadVideoActivity.this).externalPictureAudio(media.getPath());
                        break;
                }
            }
        });


        themeId = R.style.picture_default_style;


        //上传图片
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        initImagePicker();
        uploadImgRv.setLayoutManager(new GridLayoutManager(this, 3));
        uploadImgRv.setHasFixedSize(true);
        uploadImgRv.setAdapter(adapter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_upload_video;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        uploadDialogNames = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(type)) {
            switch (type) {
                case "2":
                    //图文 （当是单图&长图时，type改为6）
                    toolbarHelper.setTitle("发布图文");
                    uploadVideoRv.setVisibility(View.GONE);
                    uploadVideoTitleEdt.setVisibility(View.GONE);
                    uploadImgRv.setVisibility(View.VISIBLE);
                    uploadVideoContentEdt.setVisibility(View.VISIBLE);
                    videoChooseMode = PictureMimeType.ofImage();
                    uploadDialogNames.add("拍照");
                    uploadDialogNames.add("相册");

                    break;

                case "5":
                    //小视频
                    toolbarHelper.setTitle("发布小视频");
                    uploadVideoTitleEdt.setVisibility(View.GONE);
                    uploadImgRv.setVisibility(View.GONE);
                    uploadVideoRv.setVisibility(View.VISIBLE);
                    uploadVideoContentEdt.setVisibility(View.VISIBLE);
                    videoChooseMode = PictureMimeType.ofVideo();
                    uploadDialogNames.add("拍摄");
                    uploadDialogNames.add("本地视频");
                    break;

                case "3":
                    //长视频
                    toolbarHelper.setTitle("发布长视频");
                    uploadVideoContentEdt.setVisibility(View.GONE);
                    uploadImgRv.setVisibility(View.GONE);
                    uploadVideoRv.setVisibility(View.VISIBLE);
                    uploadVideoTitleEdt.setVisibility(View.VISIBLE);
                    videoChooseMode = PictureMimeType.ofVideo();
                    uploadDialogNames.add("拍摄");
                    uploadDialogNames.add("本地视频");
                    break;
            }
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
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);

                    //上传至七牛
                    showLoadingDialog();
                    for (int i = 0; i < images.size(); i++) {
                        upload(images.get(i).path, 1);
                    }

                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {

                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                ArrayList<String> urlImgs = (ArrayList<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_URL_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    imagelist.clear();
                    imagelist.addAll(urlImgs);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 视频选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();

                    //上传视频大小做限制
                    if (type.equals("5")) {
                        //小视频 小于1分钟
                        if (selectList != null && selectList.size() > 0 && selectList.get(0).getDuration() > 60 * 1000) {
                            ToastUtil.show(mContext, "小视频需小于1分钟");
                            return;
                        }
                    } else if (type.equals("3")) {
                        //长视频
                        if (selectList != null && selectList.size() > 0 && selectList.get(0).getDuration() > 60 * 60 * 1000) {
                            ToastUtil.show(mContext, "长视频需小于1小时");
                            return;
                        }
                    }

                    //上传至七牛
                    showLoadingDialog();
                    for (int i = 0; i < selectList.size(); i++) {
                        upload(selectList.get(0).getPath(), 2);
                    }

            }
        }


    }


    private void upload(String fileUrl, final int uploadType) {

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

        String key = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);

        Log.e("DOAING", key);

        uploadManager.put(fileUrl, key, App.QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo,
                                 JSONObject jsonObject) {
                dismissLoadingDialog();
                if (responseInfo.isOK()) {

                    Log.e("qiniu-success", "s===" + s + "responseInfo===" + responseInfo + "jsonObject===" + jsonObject);

                    try {
                        String upimg = jsonObject.getString("key");

                        imageurl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;
                        if (uploadType == 1) {
                            imagelist.add(imageurl);
                        } else {
                            videolist.add(imageurl);
                        }
                        Log.e("qiniu-success-imageurl", "imageurl===" + imageurl);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("qiniu-failssss", "s===" + s + "responseInfo===" + responseInfo + "jsonObject===" + jsonObject);
                }

            }
        }, null);
    }


    public static void start(Context context, String type) {
        Intent starter = new Intent(context, UploadVideoActivity.class);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }


    //视频
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter
            .onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            showDialog(new SelectDialog.SelectDialogListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        // 单独拍照
                        PictureSelector.create(UploadVideoActivity.this)
                                .openCamera(videoChooseMode)
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
                        PictureSelector.create(UploadVideoActivity.this)
                                .openGallery(videoChooseMode)
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
            }, uploadDialogNames);

        }
    };

    //图片
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
                                Intent intent = new Intent(UploadVideoActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(UploadVideoActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);//已经选择的图片，再次打开图片库时不勾选
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
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_URL_ITEMS, (ArrayList<String>) imagelist);
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(UploadVideoActivity.this, R.style.transparentFrameWindowStyle, listener, names);
        if (!isFinishing()) {
            dialog.show();
        }
        return dialog;
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

    @OnClick({R.id.upload_video_cancel_tv, R.id.upload_video_upload_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload_video_cancel_tv:
                finish();
                break;
            case R.id.upload_video_upload_tv:
                addNews(type);
                break;
        }
    }

    //发布图文、视频
    public void addNews(String type) {

        String imageStr = "";

        if (!StringUtils.isNullOrEmpty(type)) {
            if (type.equals("2")) {
                if (imagelist != null) {
                    if (imagelist.size() == 1) {
                        if (selImageList != null && selImageList.get(0) != null) {
                            int scale = selImageList.get(0).width / selImageList.get(0).height;
                            if (scale < 0.75) {
                                //图文-长图
                                type = "6";
                            }
                        }
                    }

                    for (int i = 0; i < imagelist.size(); i++) {
                        imageStr += imagelist.get(i);
                        if (imagelist.size() - 1 != i) {
                            imageStr += ",";
                        }
                    }
                }

            } else {
                for (int i = 0; i < videolist.size(); i++) {
                    imageStr += videolist.get(i);
                    if (videolist.size() - 1 != i) {
                        imageStr += ",";
                    }
                }
            }

            if (type.equals("2") || type.equals("5") || type.equals("6")) {
                contentStr = uploadVideoContentEdt.getText().toString();
            } else if (type.equals("3")) {
                contentStr = uploadVideoTitleEdt.getText().toString();
            }


            //非空判断 图文：有一个就可以；小视频：必须有视频；长视频：都必须有
            if (type.equals("2") || type.equals("6")) {
                if (StringUtils.isNullOrEmpty(contentStr) && StringUtils.isNullOrEmpty(imageStr)) {
                    ToastUtil.show(mContext, "请选择图片或输入文字");
                    return;
                }
            } else if (type.equals("5")) {
                if (StringUtils.isNullOrEmpty(imageStr)) {
                    ToastUtil.show(mContext, "请上传视频");
                    return;
                }
            } else if (type.equals("3")) {
                if (StringUtils.isNullOrEmpty(contentStr)) {
                    ToastUtil.show(mContext, "请输入文字");
                    return;
                } else if (StringUtils.isNullOrEmpty(imageStr)) {
                    ToastUtil.show(mContext, "请上传视频");
                    return;
                }
            }


        }

        Log.e(TAG, imageStr);

        showLoadingDialog();
        ((App) getApplication()).getAppComponent()
                .getAPIService() // 所有接口对象
                .getAddNewsResult("002", "1.0", "JSON", App.getId(), contentStr, imageStr, type)
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseModel>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override // 得到数据
                    public void onNext(BaseModel baseModel) {
                        dismissLoadingDialog();

                        if (baseModel != null && baseModel.getCode().equals("000")) {
                            ToastUtil.show(UploadVideoActivity.this, baseModel.getDescription());
                            finish();
                        } else {
                            ToastUtil.show(UploadVideoActivity.this, baseModel.getDescription());
                        }
                    }
                });
    }
}
