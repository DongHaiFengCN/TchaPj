package com.application.tchapj.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.bean.DarenDataBean;
import com.application.tchapj.my.bean.DarenDataOneBean;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.my.presenter.DarenOnePresenter;
import com.application.tchapj.my.view.IDarenOneView;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
import com.application.tchapj.utils2.pickers.AddressPickTask;
import com.application.tchapj.utils2.pickers.entity.City;
import com.application.tchapj.utils2.pickers.entity.County;
import com.application.tchapj.utils2.pickers.entity.Province;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.widiget.ToolbarHelper;
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
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2018\8\21 0021.
 */

public class DarenPyqAddActivity extends BaseMvpActivity<IDarenOneView, DarenOnePresenter> implements IDarenOneView
        , ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.pyq_add_ncet)
    EditText pyq_add_ncet;
    @BindView(R.id.pyq_add_hyet)
    EditText pyq_add_hyet;
    @BindView(R.id.pyq_add_dqtv)
    TextView pyq_add_dqtv;
    @BindView(R.id.pyq_add_bj)
    EditText pyq_add_bj;
    @BindView(R.id.pyq_add_image_rl)
    RecyclerView pyq_add_image_rl;

    @BindView(R.id.pyq_add_vieo_rl)
    RecyclerView pyq_add_vieo_rl;

    @BindView(R.id.pyq_add_bt)
    Button pyq_add_bt;


    private String imageurl;
    private ArrayList<ImageItem> images = null;
    private ImagePickerAdapter adapter;        // 适配器
    private ArrayList<ImageItem> selImageList; // 当前选择的所有图片
    //private int maxImgCount = 8;               //允许选择图片最大数
    private int maxImgCount = 1;               //允许选择图片最大数

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    //private int maxSelectNum = 9;
    private int maxSelectNum = 1;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    private GridImageAdapter gridImageAdapter;
    private List<String> imagelist = new ArrayList<>();

    private String taskApplyId = "";
    private String provincename;      // 省份
    private String cityname;          // 城市

    private String Id;            // 领任务认证id
    private String MemberId;      // 用户id
    private String catType = "0";  // 0 朋友圈 1 微博 2 抖音
    private String fans;          // 粉丝数
    private String nickName;      // 昵称
    private String price;         // 昵称
    private String headimageUrl;  // 七牛图片路径
    private String province;      // 省份
    private String city;          // 城市
    private String resourcesTypeId = "0"; // 圈子分类
    private String conpanyImgUrl; // 七牛视频路径
    private String industry = "0";      // 行业
    private String Views;         // 最多播放量
    private String comments;      // 最多评论量
    private String likes;         // 最多点赞量

    private DarenDataBean darenDataBeanResult = new DarenDataBean();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("添加朋友圈资料");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_pyq_add;
    }

    @Override
    public void initUI() {

        //chooseMode = PictureMimeType.ofAll();
        //chooseMode = PictureMimeType.ofImage();
        chooseMode = PictureMimeType.ofVideo();
        //chooseMode = PictureMimeType.ofAudio();
        themeId = R.style.picture_default_style;
        //themeId = R.style.picture_white_style;
        //themeId = R.style.picture_QQ_style;
        //themeId = R.style.picture_Sina_style;

        pyq_add_dqtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressPickTask task = new AddressPickTask(DarenPyqAddActivity.this);
                task.setHideCounty(true);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        pyq_add_dqtv.setText("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        pyq_add_dqtv.setText(province.getAreaName() + " " + city.getAreaName());
                        provincename = province.getAreaName();
                        cityname = city.getAreaName();

                    }
                });
                task.execute("山东", "济南");
            }
        });

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(DarenPyqAddActivity.this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        initImagePicker();
        initWidget();

        initSecondImage();

        pyq_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Id = App.TaskApplyId;
                MemberId = App.getId();
                catType = "0";
                price = pyq_add_bj.getText().toString();
//                fans = pyq_add_hyet.getText().toString();
//                nickName = pyq_add_ncet.getText().toString();
//
//                province=provincename;
//                city = cityname;
//                Views = "";
//                comments = "";
//                likes = "";
//
//                conpanyImgUrl = imagelist.get(0);

                if (imagelist != null && imagelist.size() > 0) {
                    headimageUrl = imagelist.get(0);
                } else {
                    headimageUrl = null;
                }

/*                if (fans.length() <= 0) {
                    Toast.makeText(getApplication(),"粉丝量！", Toast.LENGTH_LONG).show();
                    return;
                }else if (nickName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入昵称！", Toast.LENGTH_LONG).show();
                    return;
                } else if (province==null) {
                    Toast.makeText(getApplication(),"请选择省份！", Toast.LENGTH_LONG).show();
                    return;
                }else if (city==null) {
                    Toast.makeText(getApplication(),"请选择城市！", Toast.LENGTH_LONG).show();
                    return;
                }else if (conpanyImgUrl ==null) {
                    Toast.makeText(getApplication(),"请选择视频！", Toast.LENGTH_LONG).show();
                    return;
                }else */

               if (headimageUrl == null) {
                    Toast.makeText(getApplication(), "请选择图片！", Toast.LENGTH_LONG).show();
                    return;
                }

                // 上传达人资料
                getPresenter().getDarenDataBeanResult(Id, MemberId, catType, fans, nickName, price
                        , headimageUrl, province, city, conpanyImgUrl
                        , Views, comments, likes);


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

    @Override // 验证码
    public void onGetSmsCodeResult(LoginResult loginResultBean) {

    }

    @Override // 上传达人资料
    public void onGetDarenDataOneBeanResult(DarenDataOneBean darenDataOneBean) {

    }

    @Override // 上传达人频道资料
    public void onGetDarenDataBeanResult(DarenDataBean darenDataBean) {
        if ("000".equals(darenDataBean.getCode())) {

            Log.i("sssss", darenDataBean.getCode() + "");
            /*Toast.makeText(DarenPyqAddActivity.this,darenDataBean.getCode(),Toast.LENGTH_LONG).show();*/
            finish();
        }else{
            showToast(darenDataBean.getDescription());
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

        pyq_add_image_rl.setLayoutManager(new GridLayoutManager(DarenPyqAddActivity.this, 1));
        pyq_add_image_rl.setHasFixedSize(true);
        pyq_add_image_rl.setAdapter(adapter);
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
                                Intent intent = new Intent(DarenPyqAddActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(DarenPyqAddActivity.this, ImageGridActivity.class);
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
                Intent intentPreview = new Intent(DarenPyqAddActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private void initSecondImage() {

        FullyGridLayoutManager manager = new FullyGridLayoutManager(DarenPyqAddActivity.this, 1
                , GridLayoutManager.VERTICAL, false);
        pyq_add_vieo_rl.setLayoutManager(manager);
        gridImageAdapter = new GridImageAdapter(DarenPyqAddActivity.this, onAddPicClickListener);
        gridImageAdapter.setList(selectList);
        gridImageAdapter.setSelectMax(maxSelectNum);
        pyq_add_vieo_rl.setAdapter(gridImageAdapter);
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片
                        PictureSelector.create(DarenPyqAddActivity.this).externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(DarenPyqAddActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(DarenPyqAddActivity.this).externalPictureAudio(media.getPath());
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
                        PictureSelector.create(DarenPyqAddActivity.this)
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
                        PictureSelector.create(DarenPyqAddActivity.this)
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
        SelectDialog dialog = new SelectDialog(DarenPyqAddActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!DarenPyqAddActivity.this.isFinishing()) {
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
                    imagelist.clear();
                    upload(images.get(0).path);

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
        } else if (resultCode == DarenPyqAddActivity.this.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();

                    // 进行判断
                    imagelist.clear();
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

                        imageurl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;
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

    @OnClick(R.id.pyq_add_example_tv)
    public void onViewClicked() {

        CommonDialogUtil.showExampleImgDialog(this, "0");
    }
}
