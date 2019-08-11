package com.application.tchapj.task.activity;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.task.adapter.TaskPagerAdapter;
import com.application.tchapj.task.fragment.TaskFaDyGpFragment;
import com.application.tchapj.task.fragment.TaskFaDyYcFragment;
import com.application.tchapj.task.fragment.TaskFaWsGpFragment;
import com.application.tchapj.task.fragment.TaskFaWsYcFragment;
import com.application.tchapj.widiget.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

// 发微视任务
public class TaskWsMainActivity extends BaseActvity {

    @BindView(R.id.top_dygroup)
    RadioGroup top_dygroup;

    @BindView(R.id.top_dygp_task)
    RadioButton top_dygp_task;
    @BindView(R.id.top_dyyc_task)
    RadioButton top_dyyc_task;

    @BindView(R.id.top_dy_pager)
    ViewPager top_dy_pager;

    private List<Fragment> fragments;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("发布微视任务");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fa_dytask;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
    }

    public void initUI() {

        fragments = new ArrayList<Fragment>();
        top_dygp_task.setChecked(true);

        fragments.add(new TaskFaWsGpFragment());
        fragments.add(new TaskFaWsYcFragment());

        top_dy_pager.setAdapter(new TaskPagerAdapter(getSupportFragmentManager(), fragments));
        top_dy_pager.setCurrentItem(0); // 默认

        initListener(); // 点击事件
    }

    public void initListener(){

        top_dygroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int current = 0;

                switch (checkedId) {

                    case R.id.top_dygp_task:
                        current = 0;
                        break;
                    case R.id.top_dyyc_task:
                        current = 1;
                        break;
                }

                if(top_dy_pager.getCurrentItem() != current){
                    top_dy_pager.setCurrentItem(current);
                }

            }
        });

        top_dy_pager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int currentItem = top_dy_pager.getCurrentItem();
                switch (currentItem){
                    case 0:
                        top_dygroup.check(R.id.top_dygp_task);
                        break;
                    case 1:
                        top_dygroup.check(R.id.top_dyyc_task);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
