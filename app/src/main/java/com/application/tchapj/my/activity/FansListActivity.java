package com.application.tchapj.my.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.main.adapter.PersonAdapter;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.my.adpter.FansListAdapter;
import com.application.tchapj.my.bean.FansInfoBean;
import com.application.tchapj.my.presenter.AttentionFanPresenter;
import com.application.tchapj.my.view.IAttentionFanView;
import com.application.tchapj.widiget.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FansListActivity extends BaseMvpActivity<IAttentionFanView, AttentionFanPresenter> implements IAttentionFanView{

    @BindView(R.id.activity_fans_list_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_fans_list_refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    FansListAdapter fansListAdapter;
    List<FansInfoBean> fansInfoBeanList = new ArrayList<>();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("粉丝");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_fans_list;
    }

    @Override
    public void initUI() {

        fansListAdapter = new FansListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fansListAdapter);

        getFansData();

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                getFansData();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getFansData();
            }
        });
    }

    private void getFansData() {
        getPresenter().getMyFansListModelResult(pageNum, pageSize);
    }

    @Override
    public void initData() {
    }

    @NonNull
    @Override
    public AttentionFanPresenter createPresenter() {
        return new AttentionFanPresenter(getApp());
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, FansListActivity.class);
        context.startActivity(starter);
    }


    @Override
    public void getAttentionCelebrityListResult(List<PersonMediaModel.PersonMediaModelResult.PersonMedia> personMediaList) {

    }

    @Override
    public void getAttentionCircleListResult(List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> homeInfoCircleList) {

    }

    @Override
    public void getFansListResult(List<FansInfoBean> fans) {
        if (fans != null) {
            if(pageNum == 1){
                fansInfoBeanList.clear();
            }
            fansInfoBeanList.addAll(fans);
            fansListAdapter.setDatas(fansInfoBeanList);

        }
        if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
        if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();
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
