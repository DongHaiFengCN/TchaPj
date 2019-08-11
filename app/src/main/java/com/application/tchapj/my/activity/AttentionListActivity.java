package com.application.tchapj.my.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.my.fragment.AttentionListDataFragment;
import com.application.tchapj.my.presenter.AttentionFanPresenter;
import com.application.tchapj.my.view.IAttentionFanView;
import com.application.tchapj.widiget.ToolbarHelper;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

//关注列表
public class AttentionListActivity extends BaseMvpActivity<IAttentionFanView, AttentionFanPresenter> {

    @BindView(R.id.activity_attention_fan_tabs)
    TabLayout tabs;
    @BindView(R.id.activity_attention_fan_viewpager)
    ViewPager viewpager;

    Unbinder unbinder;

    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    private List<CharSequence> listTitle; // 标题

    List<CharSequence> listTemp;


    private List<Fragment> fragments;  // Fragment

    private AttentionListDataFragment attentionListDataFragment;

    private List<ConsultationTopModel.PersonSelectModelResult.NewStypeSelect> newStypeSelects = new ArrayList<>();
    private Context mContext;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("关注");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_attention_fan;
    }


    @NonNull
    @Override
    public AttentionFanPresenter createPresenter() {
        return new AttentionFanPresenter(getApp());
    }



    @Override
    public void initUI() {
        mContext = this;
        listTitle = new ArrayList<>();
        listTemp = new ArrayList<>();
        fragments = new ArrayList<>();

        attentionListDataFragment = new AttentionListDataFragment();



        //初始化数据
        fragments.clear();
        listTemp.clear();

        listTemp.add("名人");
        fragments.add(attentionListDataFragment.newInstance(AttentionListDataFragment.FROM_TYPE.CELEBRITY_TAB.ordinal()));
        listTemp.add("媒体");
        fragments.add(attentionListDataFragment.newInstance(AttentionListDataFragment.FROM_TYPE.MEDIA_TAB.ordinal()));
        listTemp.add("圈子");
        fragments.add(attentionListDataFragment.newInstance(AttentionListDataFragment.FROM_TYPE.CIRCLE_TAB.ordinal()));


        toSetList(listTitle, listTemp, false);


        if (viewPagerFragmentAdapter != null) {
            viewPagerFragmentAdapter.setListTitle(listTemp);
            viewPagerFragmentAdapter.setListData(fragments);
            viewPagerFragmentAdapter.notifyDataSetChanged();
        }

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, listTemp);

        viewpager.setAdapter(viewPagerFragmentAdapter);

        tabs.setupWithViewPager(viewpager);
    }

    @Override
    public void initData() {



    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AttentionListActivity.class);
        context.startActivity(starter);
    }


}
