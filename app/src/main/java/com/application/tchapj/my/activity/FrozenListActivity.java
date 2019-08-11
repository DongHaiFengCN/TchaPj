package com.application.tchapj.my.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.my.adpter.FrozenListAdapter;
import com.application.tchapj.my.bean.FrozenInfoBean;
import com.application.tchapj.my.presenter.FrozenListPresenter;
import com.application.tchapj.my.view.IFrozenListView;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//V币解冻
public class FrozenListActivity extends BaseMvpActivity<IFrozenListView, FrozenListPresenter> implements IFrozenListView, FrozenListAdapter.FrozenListClickListener {

    @BindView(R.id.activity_frozen_list_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_frozen_list_refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    FrozenListAdapter frozenListAdapter;
    List<FrozenInfoBean> frozenInfoBeanList = new ArrayList<>();
    private int frozenToBalancePosition;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("V币解冻");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_frozen_list;
    }

    @Override
    public void initUI() {

        frozenListAdapter = new FrozenListAdapter(this);
        frozenListAdapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(frozenListAdapter);

        getFrozenListData();

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                getFrozenListData();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getFrozenListData();
            }
        });
    }

    private void getFrozenListData() {
        getPresenter().getMoneyFrozenListModelResult(pageNum, pageSize);
    }

    @Override
    public void initData() {
    }

    @NonNull
    @Override
    public FrozenListPresenter createPresenter() {
        return new FrozenListPresenter(getApp());
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

    //冻结资金列表
    @Override
    public void getMoneyFrozenListModelResult(List<FrozenInfoBean> frozenlist) {
        if (frozenlist != null) {
            if(pageNum == 1){
                frozenInfoBeanList.clear();
            }
            frozenInfoBeanList.addAll(frozenlist);
            frozenListAdapter.setDatas(frozenInfoBeanList);

        }
        if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
        if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();
    }

    //冻结资金转到余额
    @Override
    public void getFrozenToBalanceModelResult(BaseBean baseBean) {
        if ("000".equals(baseBean.getCode())) {
            ToastUtil.show(FrozenListActivity.this, "转入成功");
            //更新控件
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(frozenToBalancePosition);
            if(viewHolder != null && viewHolder instanceof  FrozenListAdapter.ViewHolder){
                TextView adapterTaskActionTv = ((FrozenListAdapter.ViewHolder)viewHolder).taskActionTv;
                adapterTaskActionTv.setText("已转入");
                adapterTaskActionTv.setBackground(null);
            }

        }else if(baseBean != null && !StringUtils.isNullOrEmpty(baseBean.getDescription())){
            ToastUtil.show(FrozenListActivity.this, baseBean.getDescription());
        }else{
            ToastUtil.show(FrozenListActivity.this, "转入失败，请稍后再试");
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, FrozenListActivity.class);
        context.startActivity(starter);
    }

    //冻结资金转到余额
    @Override
    public void frozenToBalanceClick(String id, BigDecimal money, int position) {
        frozenToBalancePosition = position;
        getPresenter().getFrozenToBalanceModelResult(id, money + "");

    }
}
