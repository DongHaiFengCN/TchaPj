package com.application.tchapj.my.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.my.adpter.ImagePickerAdapter2;
import com.application.tchapj.my.bean.MicroInfoBean;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.my.presenter.MicroPresenter;
import com.application.tchapj.my.view.IMicroView;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.Verification;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.OnTagSelectListener;
import com.application.tchapj.widiget.TagAdapter3;
import com.application.tchapj.widiget.ToolbarHelper;
import com.king.base.util.StringUtils;
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

import static com.application.tchapj.utils2.Verification.loadSMS;
import static com.application.tchapj.utils2.Verification.verifyTel;

// 名人身份认证
public class MingrenActivity extends BaseMvpActivity<IMicroView, MicroPresenter> implements IMicroView ,ImagePickerAdapter2.OnRecyclerViewItemClickListener{

    @BindView(R.id.mingren_celebrity_fl)
    FlowTagLayout mingren_celebrity_fl;
    @BindView(R.id.mingren_name_et)
    EditText mingren_name_et;
    @BindView(R.id.mingren_mima_et)
    EditText mingren_mima_et;
    @BindView(R.id.mingren_shouji_et)
    EditText mingren_shouji_et;

    @BindView(R.id.mingren_selfie_rv)
    RecyclerView mingren_selfie_rv;

    @BindView(R.id.micro_info_next)
    Button micro_info_next;
    @BindView(R.id.permanent_address_city_name_tv)
    TextView permanentCityNameTv;//常驻城市

    private String cityNameStr;
    private int cityId = -1;


    private String id;
    private String catType; // 0 名人 1 媒体
    private String resourcesTypeId;
    private String xwhName;
    private String headimageUrl;//个人自拍
    private String realName;
    private String iDnumber;
    private String phoneNumber;

    private String companyName = "";
    private String conpanyCreditCode = "";
    private String conpanyImgUrl = "";

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private List<String> imagelist = new ArrayList<>();
    private ArrayList<ImageItem> images = null;
    private ImagePickerAdapter2 adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    //private int maxImgCount = 8;               //允许选择图片最大数
    private int maxImgCount = 1;               //允许选择图片最大数

    private String imageurl;
    private String token;

    private String tabid;
    private TagAdapter3<String> mSizeTagAdapter;

    private MicroInfoBean microInfoBeans = new MicroInfoBean();
    // 分类
    private List<MicroTabBean.MessageNewsResult.MessageNews> newstypeList = new ArrayList<>();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("名人身份激活");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_mingren;
    }

    @Override
    public void initUI() {

//        getPresenter().onGetMicroTabResult();

        token = App.QiniuToken;

        // 单选
        mSizeTagAdapter = new TagAdapter3<>(MingrenActivity.this);
        mSizeTagAdapter.setSelected(-1); // 默认选项
        mingren_celebrity_fl.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mingren_celebrity_fl.setAdapter(mSizeTagAdapter);
        mingren_celebrity_fl.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                        tabid = newstypeList.get(i).getId();
                    }

                    /*Snackbar.make(parent, position + "恭喜你" + sb.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                } else {

                    tabid = newstypeList.get(0).getId();
                    /*Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/

                }

            }
        });

//        mingren_yz_bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                phoneNumber = mingren_shouji_et.getText().toString();
//
//                if (!TextUtils.isEmpty(phoneNumber)) {
//                    boolean isTel = verifyTel(phoneNumber);
//                    if (isTel) {
//                        loadSMS(mingren_yz_bt);
//                    } else {
//                        Toast.makeText(MingrenActivity.this, "请正确填写手机号", Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//                    Toast.makeText(MingrenActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
//                }
//
//                getPresenter().getSmsCodeResult(phoneNumber);
//            }
//        });

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter2(MingrenActivity.this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        //最好放到 Application oncreate执行
        initImagePicker();
        initWidget();

        micro_info_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = App.getId();
                catType = "0";
                xwhName = "";
                headimageUrl = imageurl;

                if (tabid == null) {
//                    resourcesTypeId = "045fc07c2cc04d33a85d5c0811505f37"; // 默认的ID
                    Toast.makeText(MingrenActivity.this,"请选择领域！", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    resourcesTypeId = tabid;
                }


                realName = mingren_name_et.getText().toString();
                iDnumber = mingren_mima_et.getText().toString();

                phoneNumber = mingren_shouji_et.getText().toString();


                if (realName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入真实姓名！", Toast.LENGTH_LONG).show();
                    return;
                }else if (iDnumber.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入身份证号！", Toast.LENGTH_LONG).show();
                    return;
                } else if (phoneNumber==null) {
                    Toast.makeText(getApplication(),"请输入手机号！", Toast.LENGTH_LONG).show();
                    return;
                }else if (headimageUrl == null) {
                    Toast.makeText(getApplication(),"请选择个人自拍！", Toast.LENGTH_LONG).show();
                    return;
                }
                if(cityId == -1){
                    ToastUtil.show(MingrenActivity.this, "请选择常驻城市");
                    return;
                }

                if (!StringUtils.isEmpty(iDnumber)) {
                    boolean isSfz = Verification.verifySfz(iDnumber);
                    if (!isSfz) {
                        Toast.makeText(MingrenActivity.this, "请正确填写身份证号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                getPresenter().getMicroInfoBeanResult(id,catType,resourcesTypeId,xwhName,headimageUrl
                        ,realName,iDnumber,"","",phoneNumber,""
                        ,companyName,conpanyCreditCode,conpanyImgUrl, cityId + "");


            }
        });

    }

    @Override
    public void initData() {

        getPresenter().onGetMicroTabResult();
    }

    @NonNull
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

    @Override
    public void onGetSmsCodeResult(SmsCodeResponse loginResultBean) {

    }


    @Override // 认证
    public void onGetMicroInfoBeanResult(MicroInfoBean microInfoBean) {
        if ("000".equals(microInfoBean.getCode())) {
            microInfoBeans = microInfoBean;
            Toast.makeText(MingrenActivity.this, "名人身份提交审核成功", Toast.LENGTH_SHORT).show();
            this.finish();
        }
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

        mingren_selfie_rv.setLayoutManager(new GridLayoutManager(MingrenActivity.this, 2));
        mingren_selfie_rv.setHasFixedSize(true);
        mingren_selfie_rv.setAdapter(adapter);
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
                                Intent intent = new Intent(MingrenActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(MingrenActivity.this, ImageGridActivity.class);
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
                Intent intentPreview = new Intent(MingrenActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(MingrenActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!MingrenActivity.this.isFinishing()) {
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
        }else if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            cityNameStr = data.getStringExtra("city_name");
            cityId = data.getIntExtra("city_id", -1);
            if(!StringUtils.isEmpty(cityNameStr)){
                permanentCityNameTv.setText(cityNameStr);
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

        uploadManager.put(fileUrl, null,token, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo,
                                 JSONObject jsonObject) {
                if (responseInfo.isOK()) {

                    Log.e("success", "complete:"+responseInfo+jsonObject);

                    try {
                        String upimg = jsonObject.getString("key");

                        imageurl = "http://"+"qiniuyun2.ctrlmedia.cn/"+upimg;
                        //imagelist.add(imageurl);

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

    @OnClick({R.id.permanent_address_city_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.permanent_address_city_rl:
                CityListActivity.start(this);
                break;
        }
    }

}
