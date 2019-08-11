/*
package com.application.tchapj.my.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.fragment.MicroCelebrityFragment;
import com.application.tchapj.my.fragment.MicroMediaFragment;
import com.application.tchapj.my.presenter.QiniuPresenter;
import com.application.tchapj.my.view.IQiniuView;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

*/
/**
 * Created by 安卓开发 on 2018/7/31.
 *//*


public class MicroNumberActivity extends BaseMvpActivity<IQiniuView, QiniuPresenter>
        implements IQiniuView {

    // 获得控件
    @BindView(R.id.micro_celebrity_rb)
    RadioButton micro_celebrity_rb;
    @BindView(R.id.micro_media_rb)
    RadioButton micro_media_rb;

    // 得到Fragment对象
    private MicroCelebrityFragment microCelebrityFragment;
    private MicroMediaFragment microMediaFragment;

    private QiniuBean.QiniuBeanResult qiniuBeans = new QiniuBean.QiniuBeanResult();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("申请小微号");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_micronumber_main;
    }

    @Override
    public void initUI() {
        getPresenter().onGetQiniuResult();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // qiniu  token
        getPresenter().onGetQiniuResult();

    }

    @Override
    public void initData() {

    }


    @NonNull
    @Override
    public QiniuPresenter createPresenter() {
        return new QiniuPresenter(getApp());
    }

    @Override
    public void onGetQiniuBeanResult(QiniuBean qiniuBean) {

        if ("000".equals(qiniuBean.getCode())) {
            qiniuBeans = qiniuBean.getData();

        }

        showMicroCelebrityFragment();

    }


    // 点击事件
    @OnClick({R.id.micro_celebrity_rb, R.id.micro_media_rb})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.micro_celebrity_rb:
                showMicroCelebrityFragment();
                break;
            case R.id.micro_media_rb:
                showMicroMediaFragment();
                break;

        }
    }

    // 显示名人Fragment
    public void showMicroCelebrityFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if(microCelebrityFragment == null){
            microCelebrityFragment = MicroCelebrityFragment.newInstance(qiniuBeans.getUploadToken());
            fragmentTransaction.add(R.id.micro_number_fl,microCelebrityFragment);
        }
        commitShowFragment(fragmentTransaction,microCelebrityFragment);
    }

    // 显示媒体Fragment
    public void showMicroMediaFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(microMediaFragment == null){
            microMediaFragment = MicroMediaFragment.newInstance(qiniuBeans.getUploadToken());
            fragmentTransaction.add(R.id.micro_number_fl,microMediaFragment);
        }

        commitShowFragment(fragmentTransaction,microMediaFragment);

    }

    // 显示Fragment
    public void commitShowFragment(FragmentTransaction fragmentTransaction,Fragment fragment){
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    // 隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        hideFragment(fragmentTransaction,microCelebrityFragment);
        hideFragment(fragmentTransaction,microMediaFragment);

    }

    // 隐藏Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment){
        if(fragment!=null){
            fragmentTransaction.hide(fragment);
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

}
*/
