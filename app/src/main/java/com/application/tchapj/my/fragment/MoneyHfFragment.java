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
import com.application.tchapj.bean.PromotionResultBean;
import com.application.tchapj.my.adpter.MoneyHfAdapter;
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


// 花费
public class MoneyHfFragment extends BaseMvpFragment<IMoneyView, MoneyPresenter> implements IMoneyView {

    @BindView(R.id.money_hf_srl)
    SmartRefreshLayout money_hf_srl;
    @BindView(R.id.money_hf_rv)
    RecyclerView money_hf_rv;

    private int pageNum = 1;
    private int pageSize = 10;

    private MoneyHfAdapter moneyHfAdapter;

    // 花费
    private List<MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTasks> moneyInfoTasks = new ArrayList<>();

    @Override
    public int getRootViewId() {
        return R.layout.fragment_hf;
    }

    @Override
    public void initUI() {

        moneyHfAdapter = new MoneyHfAdapter(getContext());
        money_hf_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        money_hf_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        money_hf_rv.setAdapter(moneyHfAdapter);

        initListener();

    }

    private void initListener() {

        money_hf_srl.setEnableRefresh(false);//关闭下拉刷新
        money_hf_srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getPresenter().onGetMoneyInfoListBeanResult(pageNum+"",pageSize+"", App.getId(),"2");
            }
        });

    }

    @Override
    public void initData() {

        getPresenter().onGetMoneyInfoListBeanResult(pageNum+"",pageSize+"", App.getId(),"2");

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
                moneyInfoTasks = moneyInfoListBean.getData().getTasks();
            }else{
                moneyInfoTasks.addAll(moneyInfoListBean.getData().getTasks());
            }

            moneyHfAdapter.setData(moneyInfoTasks);

            if (money_hf_srl.isEnableRefresh()){
                money_hf_srl.finishRefresh();
            }
            if (money_hf_srl.isEnableLoadMore()){
                money_hf_srl.finishLoadMore();
            }
        }

    }

    @Override
    public void onGetArtificialTransferBeanResult(BaseBean baseBean) {

    }

    @Override
    public void promotionResultBeanBaseBean(BaseBean<PromotionResultBean> baseBean) {

    }

    @Override
    public void promotionSuccess(BaseBean baseBean) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        if (money_hf_srl.isEnableRefresh()){
            money_hf_srl.finishRefresh();
        }
        if (money_hf_srl.isEnableLoadMore()){
            money_hf_srl.finishLoadMore();
        }


    }

}
