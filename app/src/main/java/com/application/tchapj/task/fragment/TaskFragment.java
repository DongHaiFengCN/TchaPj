package com.application.tchapj.task.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.task.adapter.TaskPagerAdapter;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.bean.TaskSquareModel;
import com.application.tchapj.task.presenter.TaskSquarePresenter;
import com.application.tchapj.task.view.ITaskSquareView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

// 任务页Fragment
public class TaskFragment extends BaseMvpFragment<ITaskSquareView, TaskSquarePresenter> implements ITaskSquareView {

    @BindView(R.id.top_radiogroup)
    RadioGroup top_radiogroup;

    @BindView(R.id.top_square_task)
    RadioButton top_square_task;
    @BindView(R.id.top_my_task)
    RadioButton top_my_task;

    @BindView(R.id.top_my_pager)
    ViewPager top_my_pager;

    private List<Fragment> fragments;

    private QiniuBean.QiniuBeanResult qiniuBeans = new QiniuBean.QiniuBeanResult();

    // 接收参数
    public static TaskFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args",param);
        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initUI() {

        fragments = new ArrayList<>();
        top_square_task.setChecked(true);

        fragments.add(new TaskSquareFragment());
        fragments.add(new TaskMyFragment());

        top_my_pager.setAdapter(new TaskPagerAdapter(getChildFragmentManager(), fragments));
        top_my_pager.setCurrentItem(0); // 默认

        initListener(); // 点击事件

    }

    public void initListener(){

        top_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int current = 0;

                switch (checkedId) {

                    case R.id.top_square_task:
                        current = 0;
                        break;
                    case R.id.top_my_task:
                        current = 1;
                        break;
                }

                if(top_my_pager.getCurrentItem() != current){
                    top_my_pager.setCurrentItem(current);
                }

            }
        });

        top_my_pager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int currentItem = top_my_pager.getCurrentItem();
                switch (currentItem){
                    case 0:
                        top_radiogroup.check(R.id.top_square_task);
                        break;
                    case 1:
                        top_radiogroup.check(R.id.top_my_task);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initData() {

        getPresenter().onGetQiniuResult();

        // getPresenter().getTaskSquare("",pageNum+"",pageSize+"");
    }

    @Override
    public TaskSquarePresenter createPresenter() {
        return new TaskSquarePresenter(getApp());
    }

    @Override // 七牛
    public void onGetQiniuBeanResult(QiniuBean qiniuBean) {
        if ("000".equals(qiniuBean.getCode())) {
            qiniuBeans = qiniuBean.getData();
            App.QiniuToken =qiniuBeans.getUploadToken();
        }
    }

    @Override
    public void onGetTaskSquareModels(TaskSquareModel taskSquareModel) {

    }

    @Override
    public void onGetFriendReleaseData(TaskSquareInfoModel friendReleaseBean) {

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
