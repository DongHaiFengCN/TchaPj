package com.application.tchapj.my.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.main.adapter.CircleAdapter;
import com.application.tchapj.main.adapter.PersonAdapter;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.my.bean.FansInfoBean;
import com.application.tchapj.my.presenter.AttentionFanPresenter;
import com.application.tchapj.my.view.IAttentionFanView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//关注的名人列表
public class AttentionListDataFragment extends BaseMvpFragment<IAttentionFanView, AttentionFanPresenter> implements  IAttentionFanView{


    @BindView(R.id.activity_attention_celebrity_list_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_attention_celebrity_list_refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.activity_attention_celebrity_list_rl)
    RelativeLayout activity_attention_celebrity_list_rl;

    private int fromType;

    private int pageNum = 1;
    private int pageSize = 10;

    private List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> InfoCircles = new ArrayList<>();
    private CircleAdapter circleAdapter;

    private List<PersonMediaModel.PersonMediaModelResult.PersonMedia> celebrityList = new ArrayList<>();
    private PersonAdapter personAdapter;


    public enum  FROM_TYPE{
        CELEBRITY_TAB,//名人
        MEDIA_TAB,//媒体
        CIRCLE_TAB//圈子
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_attention_celebrity_list;
    }

    @Override
    public void initUI() {

        if(fromType == FROM_TYPE.CELEBRITY_TAB.ordinal() || fromType == FROM_TYPE.MEDIA_TAB.ordinal()){
            //名人、媒体
            personAdapter = new PersonAdapter(getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(personAdapter);
        } else{
            //圈子
            circleAdapter = new CircleAdapter(getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(circleAdapter);
        }


        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                getAttentionData();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getAttentionData();
            }
        });

    }

    @Override
    public void initData() {

        getAttentionData();

    }

    private void getAttentionData() {
        if(fromType == FROM_TYPE.CELEBRITY_TAB.ordinal()){
            getPresenter().getMyAttentionCelebrityListModelResult(pageNum, pageSize);//关注的名人列表
        } else if(fromType == FROM_TYPE.MEDIA_TAB.ordinal()){
            getPresenter().getMyAttentionMediaListModelResult(pageNum, pageSize);//关注的媒体列表
        } else{
            getPresenter().getMyAttentionCircleListModelResult(pageNum, pageSize);//关注的圈子列表
        }

    }

    @Override
    public AttentionFanPresenter createPresenter() {
        return new AttentionFanPresenter(getApp());
    }


    public AttentionListDataFragment newInstance(int fromType) {
        Bundle args = new Bundle();
        AttentionListDataFragment fragment = new AttentionListDataFragment();
        fragment.fromType = fromType;
        fragment.setArguments(args);
        return fragment;
    }


    //名人、媒体回调
    @Override
    public void getAttentionCelebrityListResult(List<PersonMediaModel.PersonMediaModelResult.PersonMedia> personMediaList) {

        if (personMediaList != null) {
            if(pageNum == 1){
                celebrityList.clear();
            }
            celebrityList.addAll(personMediaList);
            personAdapter.setDatas(celebrityList);

        }
        if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
        if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();

    }


    @Override
    public void getAttentionCircleListResult(List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> homeInfoCircleList) {

        if(homeInfoCircleList != null){
            if (pageNum == 1) {
                InfoCircles.clear();
            }

            InfoCircles.addAll(homeInfoCircleList);
            circleAdapter.setData(InfoCircles);
        }


        if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
        if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();

    }

    @Override
    public void getFansListResult(List<FansInfoBean> fansInfoBeans) {

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
