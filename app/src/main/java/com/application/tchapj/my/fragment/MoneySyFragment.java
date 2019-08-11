package com.application.tchapj.my.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.my.adpter.MoneySyAdapter;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;
import com.application.tchapj.my.presenter.MoneyPresenter;
import com.application.tchapj.my.view.IMoneyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


// 收益
public class MoneySyFragment extends BaseMvpFragment<IMoneyView, MoneyPresenter> implements IMoneyView {

    @BindView(R.id.money_sy_srl)
    SmartRefreshLayout money_sy_srl;
    @BindView(R.id.money_sy_rv)
    RecyclerView money_sy_rv;

    private int pageNum = 1;
    private int pageSize = 10;

    private MoneySyAdapter moneySyAdapter;

    // 收益
    private List<MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTaskLogs> moneyInfoTaskLogs = new ArrayList<>();

    // 接收参数
    public static MoneySyFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        MoneySyFragment fragment = new MoneySyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_sy;
    }

    @Override
    public void initUI() {

        moneySyAdapter = new MoneySyAdapter(getContext());
        money_sy_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        money_sy_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        money_sy_rv.setAdapter(moneySyAdapter);

        initListener();

    }

    private void initListener() {

        money_sy_srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                if (moneyInfoTaskLogs!=null){
                    moneyInfoTaskLogs.clear();
                }
                getPresenter().onGetMoneyInfoListBeanResult(pageNum+"",pageSize+"", App.getId());
            }
        });

        money_sy_srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getPresenter().onGetMoneyInfoListBeanResult(pageNum+"",pageSize+"", App.getId());
            }
        });

    }

    @Override
    public void initData() {

        getPresenter().onGetMoneyInfoListBeanResult(pageNum+"",pageSize+"", App.getId());
    }

    @Override
    public MoneyPresenter createPresenter() {
        return new MoneyPresenter(getApp());
    }

    @Override
    public void onGetMoneyInfoBeanResult(MoneyInfoBean moneyInfoBean) {

    }

    @Override
    public void onGetMoneyTransferBeanResult(MoneyTransferBean moneyTransferBean) {

    }

    @Override // 我的任务列表--零钱模块
    public void onGetMoneyInfoListBeanResult(MoneyInfoListBean moneyInfoListBean) {

        if ("000".equals(moneyInfoListBean.getCode())) {

            if (pageNum==1){
                moneyInfoTaskLogs = moneyInfoListBean.getData().getTaskLogs();
            }else{
                moneyInfoTaskLogs.addAll(moneyInfoListBean.getData().getTaskLogs());
            }

            moneySyAdapter.setData(moneyInfoTaskLogs);

            if (money_sy_srl.isEnableRefresh()){
                money_sy_srl.finishRefresh();
            }
            if (money_sy_srl.isEnableLoadMore()){
                money_sy_srl.finishLoadMore();
            }
        }

    }

    @Override
    public void onGetArtificialTransferBeanResult(BaseBean baseBean) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        if (money_sy_srl.isEnableRefresh()){
            money_sy_srl.finishRefresh();
        }
        if (money_sy_srl.isEnableLoadMore()){
            money_sy_srl.finishLoadMore();
        }

    }

}
