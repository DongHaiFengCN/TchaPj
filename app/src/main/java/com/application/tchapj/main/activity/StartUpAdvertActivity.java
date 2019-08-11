package com.application.tchapj.main.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.main.bean.FlashScreenBean;
import com.application.tchapj.main.presenter.StartUpAdvertPresenter;
import com.application.tchapj.main.view.IStartUpAdvertView;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.github.florent37.viewanimator.AnimationListener;
import com.king.base.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;


//闪屏页
public class StartUpAdvertActivity extends BaseActvity{


    @BindView(R.id.tvTimeCount)
    TextView tvTimeCount;
    @BindView(R.id.ivAdvertisement)
    ImageView ivAdvertisement;
    @BindView(R.id.llTimeCount)
    LinearLayout llTimeCount;

    private final String PAGE_LABLE = "splash";
    private int time = 5;
    private Handler mHandler = new Handler();
    private Runnable mRunnerable = new Runnable() {
        @Override
        public void run() {
            time--;
            if(time>0){
                mHandler.postDelayed(mRunnerable,1000);
                tvTimeCount.setText(time+" 跳过");
            }else{
                if (!isOpenedMain){
                    MainActivity.start(StartUpAdvertActivity.this);
                }
                finish();
            }
        }
    };
    private String clickLink;
    private boolean isOpenedMain = false;
    FlashScreenBean flashScreenBean;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_startup_advert;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String imgUrl = "";
        if (getIntent() != null && getIntent().getSerializableExtra("flash_screen_bean") != null && getIntent().getSerializableExtra("flash_screen_bean") instanceof FlashScreenBean) {
            flashScreenBean = (FlashScreenBean) getIntent().getSerializableExtra("flash_screen_bean");
            imgUrl = flashScreenBean.getOpenPageImg();
            clickLink = flashScreenBean.getHref();
        }


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .error(R.color.md_grey_100)
                .placeholder(R.color.md_grey_100)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(StartUpAdvertActivity.this)
                .applyDefaultRequestOptions(options)
                .load(imgUrl)
                .into(ivAdvertisement);

        startCountDown();


    }



    //倒计时
    private void startCountDown() {
        tvTimeCount.setText(time+" 跳过");
        mHandler.postDelayed(mRunnerable,1000);
    }


    @OnClick({R.id.ivAdvertisement, R.id.llTimeCount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivAdvertisement:
                if (!StringUtils.isEmpty(clickLink)) {
                    MainActivity.start(StartUpAdvertActivity.this);
                    isOpenedMain = true;

                    if(flashScreenBean != null){
                        WebViewActivity.startShare(StartUpAdvertActivity.this,
                                "All", clickLink, flashScreenBean.getName(), flashScreenBean.getIntro(), flashScreenBean.getLitimg());
                    }
                    finish();
                }
                break;
            case R.id.llTimeCount:
                isOpenedMain = true;
                MainActivity.start(StartUpAdvertActivity.this);
                finish();
                break;
        }
    }

}
