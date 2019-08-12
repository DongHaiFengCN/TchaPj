package com.application.tchapj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.guide.GuideActivity;
import com.application.tchapj.http.APIService;
import com.application.tchapj.main.activity.StartUpAdvertActivity;
import com.application.tchapj.main.bean.FlashScreenBean;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.king.base.SplashActivity;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 启动页
public class WelcomeActivity extends SplashActivity {

    //是否是第一次使用
    boolean isFirstIn = false;
    private Intent intent;
    private SharedPreferences.Editor edit;

    @BindView(R.id.ivBg)
    ImageView ivBg;       // 首页

    private boolean isShowStartUpAdvert = false;//是否展示闪屏页

    FlashScreenBean flashScreenBean;

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public Animation.AnimationListener getAnimationListener() {

        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

//                RequestOptions options = new RequestOptions()
//                        .centerCrop()
//                        .priority(Priority.HIGH)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE);

//                Glide.with(WelcomeActivity.this)
//                        .asBitmap()
//                        .apply(options)
//                        .load(R.mipmap.icon_lead)
//                        .into(new BitmapImageViewTarget(ivBg) {
//                            @Override
//                            protected void setResource(Bitmap resource) {
//                                RoundedBitmapDrawable circularBitmapDrawable =
//                                        RoundedBitmapDrawableFactory.
//                                                create(WelcomeActivity.this.getResources(), resource);
//                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
//                                ivBg.setImageDrawable(circularBitmapDrawable);
//                            }
//                        });
//                ivBg.setImageResource(R.mipmap.icon_lead);

                ((App) getApplication()).getAppComponent()
                        .getAPIService() // 所有接口对象
                        .getFlashScreenResult(APIService.APP_KEY, APIService.V, APIService.SIGN, APIService.FORMAT) //得到闪屏页数据
                        .subscribeOn(Schedulers.io()) // 订阅方式
                        .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                        .subscribe(new Subscriber<BaseBean<FlashScreenBean>>() {  // 将数据绑定到实体类的操作
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override // 得到数据
                            public void onNext(BaseBean<FlashScreenBean> baseBean) {
                                if (baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null) {
                                    String startUpAdvertUrl = baseBean.getData().getOpenPageImg();//闪屏页图片url
                                    if (!StringUtils.isEmpty(startUpAdvertUrl)) {
                                        isShowStartUpAdvert = true;
                                        flashScreenBean = baseBean.getData();
                                    }

                                }

                            }
                        });
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //读取SharedPreferences中需要的数据
                final SharedPreferences sharedPreferences = getSharedPreferences("is_first_in_data", Context.MODE_PRIVATE);
                edit = sharedPreferences.edit();
                isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
                /**
                 *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
                 */
                if (isFirstIn) {
                    intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();

                    //之前错误没有这两句,没有设置Boolean类型,并提交
                    edit.putBoolean("isFirstIn", false);
                    edit.commit();
                } else {

                    if (isShowStartUpAdvert) {
                        intent = new Intent(WelcomeActivity.this, StartUpAdvertActivity.class);
                        if (flashScreenBean != null) {
                            intent.putExtra("flash_screen_bean", flashScreenBean);
                        }
                        startActivity(intent);
                    } else {
                        intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }


}
