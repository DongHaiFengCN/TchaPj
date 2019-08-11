package com.application.tchapj.my.activity;

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
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.bean.GuanggaoBean;
import com.application.tchapj.my.presenter.GuangGaoPresenter;
import com.application.tchapj.my.view.IGuangGaoView;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
import com.application.tchapj.utils2.qiniu.utils.StringMap;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.OnTagSelectListener;
import com.application.tchapj.widiget.TagAdapter3;
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

import static com.application.tchapj.utils2.Verification.loadSMS;
import static com.application.tchapj.utils2.Verification.verifyTel;


// 广告主身份认证
public class GuanggaoActivity extends BaseMvpActivity<IGuangGaoView, GuangGaoPresenter> implements IGuangGaoView
        , ImagePickerAdapter.OnRecyclerViewItemClickListener{


    @BindView(R.id.guangao_gsname_et)
    EditText guangao_gsname_et;
    @BindView(R.id.guangao_zhiz_et)
    EditText guangao_zhiz_et;
    @BindView(R.id.guangao_gsjcname_et)
    EditText guangao_gsjcname_et;
    @BindView(R.id.guangao_name_et)
    EditText guangao_name_et;
    @BindView(R.id.guangao_shouji_et)
    EditText guangao_shouji_et;
    @BindView(R.id.guangao_code_et)
    EditText guangao_code_et;

    @BindView(R.id.guangao_code_tv)
    TextView guangao_code_tv;

    @BindView(R.id.guangao_rv)
    RecyclerView guangao_rv;

    @BindView(R.id.guangao_bv)
    Button guangao_bv;
    @BindView(R.id.guanggao_industry_fl)
    FlowTagLayout guanggao_industry_fl;

    private String imageurl;
    private ArrayList<ImageItem> images = null;
    private ImagePickerAdapter adapter;        // 适配器
    private ArrayList<ImageItem> selImageList; // 当前选择的所有图片
    //private int maxImgCount = 8;               //允许选择图片最大数
    private int maxImgCount = 1;               //允许选择图片最大数

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private String memberId;
    private String realName ;
    private String nickName;
    private String companyName;
    private String conpanyCreditCode;
    private String conpanyImgUrl;
    private String industry;
    private String phoneNumber;
    private String code;

    private TagAdapter3<String> mSizeTagAdapter;


    private String[] industryTagStrs = new String[]{"其他", "互联网", "传媒/营销", "学生", "金融财经", "电商微商", "文娱", "母婴", "体育", "旅游/酒店", "时尚", "餐饮/服务", "汽车",
                        "政府/企事业单位", "教育", "医疗/健康", "房地产", "零售/商超", "IT通讯", "物流", "游戏动漫", "IT数码", "司法"};
    private String industryStr;//行业


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("企业身份激活");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_guanggao;
    }

    @Override
    public void initUI() {

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(GuanggaoActivity.this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        //最好放到 Application oncreate执行
        initImagePicker();
        initWidget();

        guangao_code_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneNumber = guangao_shouji_et.getText().toString();

                if (!TextUtils.isEmpty(phoneNumber)) {
                    boolean isTel = verifyTel(phoneNumber);
                    if (isTel) {
                        loadSMS(guangao_code_tv);
                    } else {
                        Toast.makeText(GuanggaoActivity.this, "请正确填写手机号", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(GuanggaoActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }

                getPresenter().getBindingSmsCodeResult(phoneNumber);
            }
        });

        guangao_bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                memberId=App.getId();
                realName = guangao_name_et.getText().toString();
                nickName = guangao_gsjcname_et.getText().toString();
                companyName = guangao_gsname_et.getText().toString();
                conpanyCreditCode = guangao_zhiz_et.getText().toString();
                conpanyImgUrl = imageurl;
                phoneNumber = guangao_shouji_et.getText().toString();
                code = guangao_code_et.getText().toString();

                if (realName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入运营者姓名！", Toast.LENGTH_LONG).show();
                    return;
                }else if (nickName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入公司简称！", Toast.LENGTH_LONG).show();
                    return;
                }else if (companyName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入公司名称！", Toast.LENGTH_LONG).show();
                    return;
                }else if (conpanyCreditCode.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入社会统一信用代码！", Toast.LENGTH_LONG).show();
                    return;
                }else if (phoneNumber==null) {
                    Toast.makeText(getApplication(),"请输入手机号！", Toast.LENGTH_LONG).show();
                    return;
                }else if (code.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入验证码！", Toast.LENGTH_LONG).show();
                    return;
                }else if (conpanyImgUrl==null) {
                    Toast.makeText(getApplication(),"请选择图片！", Toast.LENGTH_LONG).show();
                    return;
                }else if (StringUtils.isNullOrEmpty(industryStr)) {
                    Toast.makeText(getApplication(),"请选择行业标签！", Toast.LENGTH_LONG).show();
                    return;
                }

                // 上传达人资料
                getPresenter().getDarenDataOneBeanResult(memberId,realName,nickName,companyName
                        ,conpanyCreditCode,conpanyImgUrl,phoneNumber,code,industryStr);

            }
        });

        mSizeTagAdapter = new TagAdapter3<>(GuanggaoActivity.this);
        mSizeTagAdapter.setSelected(-1); // 默认选项
        guanggao_industry_fl.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE); // 选择方式
        guanggao_industry_fl.setAdapter(mSizeTagAdapter);
        guanggao_industry_fl.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                industryStr = industryTagStrs[position];

            }
        });
        initSizeData();

    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public GuangGaoPresenter createPresenter() {
        return new GuangGaoPresenter(getApp());
    }


    @Override // 验证码
    public void onGetSmsCodeResult(BaseBean<SmsCodeBean> loginResultBean) {

    }

    @Override
    public void onGetGuangGaoResult(GuanggaoBean guanggaoBean) {
        if ("000".equals(guanggaoBean.getCode())) {
            ToastUtil.show(this, guanggaoBean.getDescription());

            Intent intent = new Intent(GuanggaoActivity.this, IdentityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
            startActivity(intent);
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

        guangao_rv.setLayoutManager(new GridLayoutManager(GuanggaoActivity.this, 1));
        guangao_rv.setHasFixedSize(true);
        guangao_rv.setAdapter(adapter);
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
                                Intent intent = new Intent(GuanggaoActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(GuanggaoActivity.this, ImageGridActivity.class);
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
                Intent intentPreview = new Intent(GuanggaoActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(GuanggaoActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!GuanggaoActivity.this.isFinishing()) {
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
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < industryTagStrs.length; i++) {
            dataSource.add(industryTagStrs[i]);

        }
        mSizeTagAdapter.onlyAddAll(dataSource);
    }

}
