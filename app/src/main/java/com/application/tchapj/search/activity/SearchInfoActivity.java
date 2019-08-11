package com.application.tchapj.search.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.consultation.activity.FindDetailActivity;
import com.application.tchapj.consultation.adapter.ConsultationInfoAdapter;
import com.application.tchapj.consultation.bean.CommentConfig;
import com.application.tchapj.consultation.bean.MessageNews;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.search.adapter.SearchRecyclerAdapter;
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

public class SearchInfoActivity extends BaseMvpActivity<ISearchView, SearchPresenter>
        implements ISearchView,ConsultationInfoAdapter.ClickItemListener {

    private String Name;

    @BindView(R.id.search_easyRecyclerView)
    EasyRecyclerView search_easyRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TextView tvTips;
    private List<MessageNews> listSearchNews;
    private List<SearchBean.SearchBeanResult.SearchMingrenList> listSearchMingrens;

    private SearchRecyclerAdapter searchRecyclerAdapter; // 适配器


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        Intent intent = getIntent();
        Name = intent.getStringExtra("Name");

        toolbarHelper.setTitle(Name);
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_search_info;
    }

    @Override
    public void initUI() {

        listSearchNews = new ArrayList<>();
        listSearchMingrens = new ArrayList<>();

        getPresenter().onGetSearchBeanResult(Name);
        search_easyRecyclerView.showProgress();

        tvTips = (TextView) search_easyRecyclerView.findViewById(R.id.tvTips);
        SpaceDecoration spaceDecoration = new SpaceDecoration(0);
        search_easyRecyclerView.addItemDecoration(spaceDecoration);
        search_easyRecyclerView.setRefreshingColorResources(R.color.progress_color);

        search_easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().onGetSearchBeanResult(Name);
                search_easyRecyclerView.showProgress();
            }
        });

    }

    @Override
    public void initData() {

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



        if(searchBean != null && searchBean.getCode().equals("000")){
            search_easyRecyclerView.showRecycler();

            listSearchNews = searchBean.getData().getNews();
            listSearchMingrens = searchBean.getData().getMingrenList();

            setAdapter();
        }else{
            search_easyRecyclerView.showError();
        }



    }

    @Override // 查看更多
    public void onGetSearchBeanResultMost(SearchBean searchBean) {

    }

    private void setAdapter() {

        searchRecyclerAdapter = new SearchRecyclerAdapter(SearchInfoActivity.this, listSearchMingrens, listSearchNews,Name);
        linearLayoutManager = new LinearLayoutManager(SearchInfoActivity.this, LinearLayoutManager.VERTICAL, false);
        search_easyRecyclerView.setLayoutManager(linearLayoutManager);

        search_easyRecyclerView.setAdapter(searchRecyclerAdapter);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {
        search_easyRecyclerView.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {

        LogUtils.w(e);

        if (SystemUtils.isNetWorkActive(SearchInfoActivity.this)) {

            tvTips.setText("获取数据失败，请重新搜索");
        } else {
            tvTips.setText(R.string.network_unavailable);
        }
        search_easyRecyclerView.showError();

    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onHeadImgClick(MessageNews messageNews) {

    }

    @Override
    public void onDeleteItemButtonClick(int position, int commentId) {

    }

    @Override
    public void addOrCancelLikes(int mPosition, MessageNews id) {

    }

    @Override
    public void onItemButtonClick(CommentConfig config, String id, int mSize) {

    }

    @Override
    public void onDeleteArticleClick(String news_Id, int deletePosition) {

    }
}
