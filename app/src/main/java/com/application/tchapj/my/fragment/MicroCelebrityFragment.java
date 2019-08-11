/*
package com.application.tchapj.my.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.my.activity.MicroInfoActivity;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.bean.MicroInfoBean;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.my.presenter.MicroPresenter;
import com.application.tchapj.my.view.IMicroView;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
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

// 名人认证
public class MicroCelebrityFragment extends BaseMvpFragment<IMicroView, MicroPresenter> implements IMicroView
        , ImagePickerAdapter.OnRecyclerViewItemClickListener {


    @BindView(R.id.micro_celebrity_fl)
    FlowTagLayout micro_celebrity_fl;
    @BindView(R.id.micro_celebrity_name)
    EditText micro_celebrity_name;
    @BindView(R.id.micro_celebrity_rv)
    RecyclerView recyclerView;
    @BindView(R.id.micro_celebrity_next)
    Button micro_celebrity_next;

    private String tabid;
    private TagAdapter3<String> mSizeTagAdapter;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> images = null;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    //private int maxImgCount = 8;               //允许选择图片最大数
    private int maxImgCount = 1;               //允许选择图片最大数

    private String imageurl;
    private String token ;

    private String Id;
    private String CatType ;   // 0 名人 1 媒体
    private String ResourcesTypeId;
    private String XwhName;
    private String HeadimageUrl;

    private String companyName = "";
    private String conpanyCreditCode = "";
    private String conpanyImgUrl = "";

    // 分类
    private List<MicroTabBean.MessageNewsResult.MessageNews> newstypeList = new ArrayList<>();

    // 接收参数
    public static MicroCelebrityFragment newInstance(String tokenName) {
        Bundle args = new Bundle();

        MicroCelebrityFragment fragment = new MicroCelebrityFragment();
        fragment.token = tokenName;
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_micro_celebrity;
    }

    @Override
    public void initUI() {

        // 单选
        mSizeTagAdapter = new TagAdapter3<>(getContext());
        mSizeTagAdapter.setSelected(-1); // 默认选项
        micro_celebrity_fl.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        micro_celebrity_fl.setAdapter(mSizeTagAdapter);
        micro_celebrity_fl.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                        tabid = newstypeList.get(i).getId();
                    }

                    */
/*Snackbar.make(parent, position + "恭喜你" + sb.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*//*

                } else {

                    tabid = newstypeList.get(0).getId();
                    */
/*Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*//*


                }

            }
        });

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getContext(), selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        //最好放到 Application oncreate执行
        initImagePicker();
        initWidget();

        micro_celebrity_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Id = App.getId();
                CatType = "0";
                XwhName = micro_celebrity_name.getText().toString();
                HeadimageUrl = imageurl;

                if(tabid==null){
                    ResourcesTypeId = "045fc07c2cc04d33a85d5c0811505f37"; // 默认的ID
                }else {
                    ResourcesTypeId = tabid;
                }

                if (XwhName.length() <= 0) {
                    Toast.makeText(getContext(),"请输入小微号昵称！", Toast.LENGTH_LONG).show();
                    return;
                } else if (HeadimageUrl==null) {
                    Toast.makeText(getContext(),"请选择头像！", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getContext(), MicroInfoActivity.class);

                intent.putExtra("Id", Id);
                intent.putExtra("Token", token);
                intent.putExtra("CatType", CatType);
                intent.putExtra("ResourcesTypeId", ResourcesTypeId);
                intent.putExtra("XwhName", XwhName);
                intent.putExtra("HeadimageUrl", HeadimageUrl);

                intent.putExtra("companyName", companyName);
                intent.putExtra("conpanyCreditCode", conpanyCreditCode);
                intent.putExtra("conpanyImgUrl", conpanyImgUrl);

                startActivity(intent);

            }
        });

    }


    @Override
    public void initData() {

        getPresenter().onGetMicroTabResult();
    }

    @Override
    public MicroPresenter createPresenter() {

        return new MicroPresenter(getApp());
    }

    @Override // 标签数据
    public void onGetMicroTabBeanResult(MicroTabBean dicroTabBean) {

        if ("000".equals(dicroTabBean.getCode())) {
            newstypeList = dicroTabBean.getData().getNewstypeList();
            newstypeList.get(0).getName();
            initSizeData();
        }
    }

    @Override // 验证码
    public void onGetSmsCodeResult(LoginResult loginResultBean) {

    }

    @Override // 认证
    public void onGetMicroInfoBeanResult(MicroInfoBean microInfoBean) {

    }

    // 初始化数据
    private void initSizeData() {
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < newstypeList.size(); i++) {
            dataSource.add(newstypeList.get(i).getName());

        }
        mSizeTagAdapter.onlyAddAll(dataSource);
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

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
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
                                */
/**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 *//*

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
                                */
/* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * *//*

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
        }


    }

    private void upload(String fileUrl) {

        */
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
        UploadManager uploadManager = new UploadManager(config);*//*



        //上传配置
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(10) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
                .zone(FixedZone.zone2) // 设置区域，指默认 Zone.zone0 注：这步是最关键的 当初错的主要原因也是他 根据自己的地方选
                .build();
        UploadManager uploadManager = new UploadManager(config);

        uploadManager.put(fileUrl, null,token, new UpCompletionHandler() {
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


}
*/
