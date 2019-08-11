package com.application.tchapj.task.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.task.adapter.FriendReleaseAdapter;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.UploadAudioPresent;
import com.application.tchapj.task.view.IUploadAudioView;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.widiget.GridSpacingItemDecoration;
import com.application.tchapj.widiget.ToolbarHelper;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * 上传审核材料 提交申请
 *
 * 活动资料下载（文案+配图） 展示条件：朋友圈图文、微博
  */
public class UploadAuditActivity extends BaseMvpActivity<IUploadAudioView, UploadAudioPresent> implements IUploadAudioView {

    @BindView(R.id.uploadAudioImg)
    ImageView uploadAudioImg;
    @BindView(R.id.uploadAudioTv)
    TextView uploadAudioTv;
    @BindView(R.id.uploadAudioNameTv)
    TextView uploadAudioNameTv;
    @BindView(R.id.uploadAudioCountDownValueTv)
    TextView uploadAudioCountDownValueTv;
    @BindView(R.id.uploadAudioSubmitValueTv)
    TextView uploadAudioSubmitValueTv;
    /*@BindView(R.id.uploadAudioImg2)
    ImageView uploadAudioImg2;*/
    @BindView(R.id.uploadAudioContent1)
    TextView uploadAudioContent1;
    @BindView(R.id.uploadAudioBtnTime)
    TextView uploadAudioBtnTime;
    @BindView(R.id.uploadAudioBtn)
    Button uploadAudioBtn;
    @BindView(R.id.uploadAudioImg1)
    ImageView uploadAudioImg1;
    @BindView(R.id.vido_rl)
    RecyclerView vido_rl;

    @BindView(R.id.activity_data_cl)
    ConstraintLayout activityDataLayout;//活动资料布局
    @BindView(R.id.releasefDownloadValueTv1)
    TextView copywritingTv;//复制的文案
    @BindView(R.id.releasefValueRv)
    RecyclerView saveImgRv;//保存的图片


    private FriendReleaseAdapter saveImgAdapter;//活动资料下载的图片

    private String timeStr;
    private long countdownTime;
//    private Disposable disposable1;
    private String taskType;

    private String vidourl;


    // 第二个图片选择
    //private int maxSelectNum = 9;
    private int maxSelectNum = 1;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    private GridImageAdapter gridImageAdapter;
    private List<String> imagelist = new ArrayList<>();
    private String token;
    private String medioTypeName;
    private String id;
    private String infoId;

    TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskBean;

    //倒计时
    Timer timer = new Timer();
    Long timeDiffer = 0L;

    Handler handler=new Handler();
    Runnable mRunnerable = new Runnable() {
        @Override
        public void run() {
            if(!uploadAudioBtn.isClickable()){
                timeDiffer--;
//                LogUtils.e("TAGzyyzyyyzyyzyyzyyzyy", timeDiffer + "=======================");
                if(timeDiffer > 0){
                    String timeDifferStr= CommonUtils.getTimeStampDiffer(timeDiffer * 1000);
                    setTimeCountDown(timeDifferStr);
                }else{
                    uploadAudioBtn.setBackgroundResource(R.drawable.bg_gradient1);
                    uploadAudioBtn.setClickable(true);
                }
            }

            long time = taskBean.getEndTime() - System.currentTimeMillis();
            uploadAudioCountDownValueTv.setText(CommonUtils.getTimeStampDiffer(time));

            handler.postDelayed(mRunnerable, 2000);
        }
    };



    @NonNull
    @Override
    public UploadAudioPresent createPresenter() {
        return new UploadAudioPresent(getApp());
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getResources().getString(R.string.task_schedule));
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_upload_audit;
    }

    @Override
    public void initUI() {
        id = getIntent().getStringExtra("ID");
        //chooseMode = PictureMimeType.ofAudio();
        themeId = R.style.picture_default_style;
        //themeId = R.style.picture_white_style;
        //themeId = R.style.picture_QQ_style;
        //themeId = R.style.picture_Sina_style;
        initSecondImage();

        initData();


    }

    @OnClick({R.id.uploadAudioBtn, R.id.coypTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.uploadAudioBtn:
                if (vidourl != null) {
                    presenter.submitTaskReview(App.getId(), vidourl, infoId);
                } else {
                    Toast.makeText(this, "请提交审核材料", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.coypTv:
                ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("", copywritingTv.getText().toString().trim());
                mClipboardManager.setPrimaryClip(clipData);
                Toast.makeText(this, "文本复制成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void initData() {

        presenter.getFriendReleaseData(App.getId(), id);
    }


    @Override // 获取审核数据
    public void onGetUploadAudioData(TaskSquareInfoModel friendReleaseBean) {

        Log.e("135", friendReleaseBean.getCode());
        if (TextUtils.equals(friendReleaseBean.getCode(), "000")) {
            vido_rl.setVisibility(View.VISIBLE);
            Log.e("135", friendReleaseBean.getData() + "");
            Log.e("135", friendReleaseBean.getData().getTask() + "");
            taskBean = friendReleaseBean.getData().getTask();
            infoId = taskBean.getId();
            taskType = taskBean.getTaskType();//0 朋友圈 1 微博 2 抖音跟拍 3 抖音原创 4其他5朋友圈转发链接6微视合拍7微视原创

            //可以提交资料的时间判断。


            timeDiffer = friendReleaseBean.getData().getCountDownTime();
            if(timeDiffer <= 0 ){
                //大于X个小时 可以提交资料
                uploadAudioBtn.setBackgroundResource(R.drawable.bg_gradient1);
                uploadAudioBtn.setClickable(true);
                uploadAudioBtnTime.setVisibility(View.GONE);
                uploadAudioBtn.setClickable(true);
            }else{
                uploadAudioBtn.setBackgroundResource(R.drawable.bg_gradient1_gray);
                uploadAudioBtn.setClickable(false);
                uploadAudioBtnTime.setVisibility(View.VISIBLE);

                String timeDifferStr= CommonUtils.getTimeStampDiffer(timeDiffer * 1000);
                setTimeCountDown(timeDifferStr);
                handler.postDelayed(mRunnerable,2000);

            }




            activityDataLayout.setVisibility(View.GONE);//默认隐藏活动资料布局
            if (TextUtils.equals(taskType, "0")) {
                //朋友圈图文
                uploadAudioContent1.setText(String.format(getResources().getString(R.string.upload_audit_content1), "朋友圈"));
                chooseMode = PictureMimeType.ofImage();
                medioTypeName = "图片";
                uploadAudioImg1.setImageDrawable(getResources().getDrawable(R.drawable.friend_circle));
                activityDataLayout.setVisibility(View.VISIBLE);//显示活动资料布局
            } else if (TextUtils.equals(taskType, "1")) {
                uploadAudioContent1.setText(String.format(getResources().getString(R.string.upload_audit_content1), "微博发布成功"));
                chooseMode = PictureMimeType.ofImage();
                medioTypeName = "图片";
                uploadAudioImg1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_wb_select));
                activityDataLayout.setVisibility(View.VISIBLE);//显示活动资料布局
            } else if (TextUtils.equals(taskType, "2")) {
                uploadAudioContent1.setText(String.format(getResources().getString(R.string.upload_audit_content1), "抖音合拍视频"));
                chooseMode = PictureMimeType.ofImage();
                medioTypeName = "图片";
                uploadAudioImg1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_dy_select));
            } else if (TextUtils.equals(taskType, "3")) {
                uploadAudioContent1.setText(String.format(getResources().getString(R.string.upload_audit_content1), "抖音原创视频"));
                chooseMode = PictureMimeType.ofImage();
                medioTypeName = "图片";
                uploadAudioImg1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_dy_select));
            } else if (TextUtils.equals(taskType, "4")) {
                uploadAudioContent1.setText(String.format(getResources().getString(R.string.upload_audit_content1), "其他发布成功"));
                chooseMode = PictureMimeType.ofImage();
                medioTypeName = "图片";
                uploadAudioImg1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_qt));
            } else if (TextUtils.equals(taskType, "6")) {
                uploadAudioContent1.setText(String.format(getResources().getString(R.string.upload_audit_content1), "微视合拍视频"));
                chooseMode = PictureMimeType.ofImage();
                medioTypeName = "图片";
                uploadAudioImg1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ws_select));
            } else if (TextUtils.equals(taskType, "7")) {
                uploadAudioContent1.setText(String.format(getResources().getString(R.string.upload_audit_content1), "微视原创视频"));
                chooseMode = PictureMimeType.ofImage();
                medioTypeName = "图片";
                uploadAudioImg1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ws_select));
            }
            GlideUtils.loadRoundedImageView(this, taskBean.getImgUrl(), uploadAudioImg, R.color.gainsboro);
            uploadAudioNameTv.setText(taskBean.getName());
            uploadAudioTv.setText(taskBean.getNickName());
            uploadAudioSubmitValueTv.setText("审核名额剩余" + taskBean.getReceiveTotal() + "个");

            setActivityData(taskBean);

//            disposable1 = Observable
//                    .interval(1, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Consumer<Long>() {
//                        @Override
//                        public void accept(Long aLong) throws Exception {
//                            countdownTime--;
////                    Log.e("135", countdownTime + "");
//                            long time = taskBean.getEndTime() - System.currentTimeMillis();
//                            uploadAudioCountDownValueTv.setText(CommonUtils.getTimeStampDiffer(time));
//                            if (countdownTime == 0) {
//                                disposable1.dispose();
//                            }
//                        }
//                    });

        }
    }

    /**
     * 设置活动资料展示
     * @param taskBean
     */
    private void setActivityData(TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskBean) {
        if(activityDataLayout.getVisibility() == View.VISIBLE){
            copywritingTv.setText(taskBean.getCopywriting());

            //图片展示
            saveImgRv.setHasFixedSize(true);
            saveImgRv.setNestedScrollingEnabled(false);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            saveImgRv.addItemDecoration(new GridSpacingItemDecoration(3, Utils.dip2px(15f, this), Utils.dip2px(15f, this), false));
            saveImgRv.setLayoutManager(gridLayoutManager);
            saveImgAdapter = new FriendReleaseAdapter(this);
            saveImgRv.setAdapter(saveImgAdapter);


            // 资源路径
            String[] imgs = taskBean.getTaskImgUrl().split(",");
            if(imgs != null && imgs.length > 0){
                // 将资源路径设置到图片选择实体类
                final ArrayList<ImageItem> imageItems = new ArrayList<>();
                for (String url : imgs) {
                    ImageItem imageItem = new ImageItem();
                    imageItem.mimeType = "copy";
                    imageItem.path = url;
                    imageItems.add(imageItem);
                    Log.e("135", "url = " + url);
                }
                // 为适配器赋值 进行图片预览
                saveImgAdapter.setDataList(new ArrayList(Arrays.asList(imgs)));
                saveImgAdapter.setOnItemClickListener(new FriendReleaseAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int pos) {
                        Intent intentPreview = new Intent(UploadAuditActivity.this, ImagePreviewCopyActivity.class);
                        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, imageItems);
                        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, pos);
                        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                        startActivity(intentPreview);
                    }
                });
                saveImgAdapter.notifyDataSetChanged();
            }


        }
    }


    @Override // 提交
    public void submit(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            JSONObject jsonObject = new JSONObject(json);
            Log.e("135", "code = " + jsonObject.getString("code"));
            Log.e("135", "description = " + jsonObject.getString("description"));
            if (TextUtils.equals(jsonObject.getString("code"), "000")) {

                finish();
                Toast.makeText(UploadAuditActivity.this, "提交申请成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UploadAuditActivity.this, TaskReviewprogressActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(UploadAuditActivity.this, "提交申请失败", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (disposable1 != null) {
//            disposable1.dispose();
//        }
    }


    private void initSecondImage() {

        FullyGridLayoutManager manager = new FullyGridLayoutManager(UploadAuditActivity.this, 1, GridLayoutManager.VERTICAL, false);
        vido_rl.setLayoutManager(manager);
        gridImageAdapter = new GridImageAdapter(UploadAuditActivity.this, onAddPicClickListener);
        gridImageAdapter.setDefaultImgRes(R.drawable.icon_upload_img);
        gridImageAdapter.setList(selectList);
        gridImageAdapter.setSelectMax(maxSelectNum);
        vido_rl.setAdapter(gridImageAdapter);
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片
                        PictureSelector.create(UploadAuditActivity.this).externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(UploadAuditActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(UploadAuditActivity.this).externalPictureAudio(media.getPath());
                        break;
                }
            }
        });

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            if(!uploadAudioBtn.isClickable()){
                ToastUtil.show(UploadAuditActivity.this, "未到时间，暂时不能上传图片");
                return;
            }

            List<String> names = new ArrayList<>();
            names.add("拍照");
            names.add(medioTypeName);
            showDialog(new SelectDialog.SelectDialogListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        // 单独拍照
                        PictureSelector
                                .create(UploadAuditActivity.this)
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
                        PictureSelector
                                .create(UploadAuditActivity.this)
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
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == UploadAuditActivity.this.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    Log.e("135", "111111111111111");
                    // 获取视频或图片路径 设置到适配器  进行预览只是进行赋值就可以
                    selectList = PictureSelector.obtainMultipleResult(data);
                    Log.e("135", selectList.get(0).getPath());
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();

                    // 进行判断
                    for (int i = 0; i < selectList.size(); i++) {
                        upload(selectList.get(i).getPath());
                    }

            }

        }
    }

    @Override
    public void upload(String fileUrl) {
        Log.e("135", "upload()");
        //上传配置
        Configuration config = new Configuration.Builder().chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(10) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
                .zone(FixedZone.zone2) // 设置区域，指默认 Zone.zone0 注：这步是最关键的 当初错的主要原因也是他 根据自己的地方选
                .build();
        UploadManager uploadManager = new UploadManager(config);

        uploadManager.put(fileUrl, null, App.QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                if (responseInfo.isOK()) {
                    Log.e("135", "complete:" + responseInfo + jsonObject);
                    Log.e("success", "complete:" + responseInfo + jsonObject);

                    try {
                        String upimg = jsonObject.getString("key");

                        vidourl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;

                        Log.e("qiniu++++", "complete: " + jsonObject + vidourl);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("failssss", s + responseInfo + jsonObject + vidourl);
                }
                Log.e("qiniu", "complete: " + jsonObject + vidourl);
                // vidourl = "http://qiniuyun2.ctrlmedia.cn/";
            }
        }, null);
    }


    private void setTimeCountDown(String timeDifferStr) {
        uploadAudioBtnTime.setText(String.format(getResources().getString(R.string.upload_audit_time_str), timeDifferStr));
    }

    @Override
    protected void onPause() {
        super.onPause();

        handler.removeCallbacks(mRunnerable);
    }
}
