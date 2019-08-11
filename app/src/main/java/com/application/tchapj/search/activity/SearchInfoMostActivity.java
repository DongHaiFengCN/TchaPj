package com.application.tchapj.search.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.search.adapter.SearchInfoMostAdapter;
import com.application.tchapj.search.bean.SearchBean;
import com.application.tchapj.search.presenter.SearchPresenter;
import com.application.tchapj.search.view.ISearchView;
import com.application.tchapj.widiget.LogUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.king.base.util.SystemUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 安卓开发 on 2018/8/7.
 */

public class SearchInfoMostActivity extends BaseMvpActivity<ISearchView, SearchPresenter>
        implements ISearchView{

    private String Name;
    private TextView tvTips;
    @BindView(R.id.search_info_most_rv)
    EasyRecyclerView search_info_most_rv;
    SearchInfoMostAdapter searchInfoMostAdapter;
    private List<SearchBean.SearchBeanResult.SearchMingrenList> listSearchMingrens;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        Intent intent = getIntent();
        Name = intent.getStringExtra("Name");

        toolbarHelper.setTitle("查看更多");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_search_info_most;
    }

    @Override
    public void initUI() {

        listSearchMingrens = new ArrayList<>();
        getPresenter().onGetSearchBeanResultMost(Name,"1");

        tvTips = (TextView) search_info_most_rv.findViewById(R.id.tvTips);
        SpaceDecoration spaceDecoration = new SpaceDecoration(0);
        search_info_most_rv.addItemDecoration(spaceDecoration);
        search_info_most_rv.setRefreshingColorResources(R.color.progress_color);

    }

    @Override
    public void initData() {

        search_info_most_rv.showProgress();
    }

    @NonNull
    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter(getApp());
    }

    @Override // 标签
    public void onGetMicroTabBeanResult(MicroTabBean dicroTabBean) {

    }

    @Override // 搜索内容
    public void onGetSearchBeanResult(SearchBean searchBean) {

    }

    @Override // 查看更多
    public void onGetSearchBeanResultMost(SearchBean searchBean) {

        if (searchBean.getData().getMingrenList().size()>0) {

            listSearchMingrens = searchBean.getData().getMingrenList();

            setAdapter();
        }else {
            search_info_most_rv.showEmpty();
        }

    }

    private void setAdapter() {

        searchInfoMostAdapter = new SearchInfoMostAdapter(SearchInfoMostActivity.this, listSearchMingrens);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchInfoMostActivity.this,LinearLayoutManager.VERTICAL,false);
        search_info_most_rv.setLayoutManager(linearLayoutManager);

        search_info_most_rv.setAdapter(searchInfoMostAdapter);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

        search_info_most_rv.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {

        LogUtils.w(e);

        if (SystemUtils.isNetWorkActive(SearchInfoMostActivity.this)) {

            tvTips.setText("获取数据失败，请重新搜索");
        } else {
            tvTips.setText(R.string.network_unavailable);
        }
        search_info_most_rv.showError();

    }

}
