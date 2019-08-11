package com.application.tchapj.main.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.main.adapter.MingPeopleAdapter;
import com.application.tchapj.main.bean.MingPeopleListBean;
import com.application.tchapj.main.presenter.MingPeopleListPresenter;
import com.application.tchapj.main.view.IMingPeopleListView;
import com.application.tchapj.widiget.ToolbarHelper;
import com.king.base.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

// 明星活跃周榜
public class MingPeopleListActivity extends BaseMvpActivity<IMingPeopleListView, MingPeopleListPresenter>
        implements IMingPeopleListView {


    @BindView(R.id.mingpeople_recyclerview)
    RecyclerView mingpeople_recyclerview;

    private MingPeopleAdapter mingPeopleAdapter;

    private List<MingPeopleListBean.DataBean.HuoyueListBean> huoyueLists = new ArrayList<>();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("明星活跃周榜");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_mingpeoplelist;
    }

    @Override
    public void initUI() {

        getPresenter().getMingPeopleListResult();


        mingPeopleAdapter = new MingPeopleAdapter(MingPeopleListActivity.this);
        mingpeople_recyclerview.setLayoutManager(new LinearLayoutManager(MingPeopleListActivity.this));
        mingpeople_recyclerview.addItemDecoration(new DividerItemDecoration(MingPeopleListActivity.this, DividerItemDecoration.VERTICAL));
        mingpeople_recyclerview.setAdapter(mingPeopleAdapter);

        initListener();
    }

    private void initListener() {

    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public MingPeopleListPresenter createPresenter() {
        return new MingPeopleListPresenter(getApp());
    }

    @Override
    public void onGetMingPeopleListResult(MingPeopleListBean mingPeopleListBean) {

        if ("000".equals(mingPeopleListBean.getCode())) {

            huoyueLists = mingPeopleListBean.getData().getHuoyueList();

            mingPeopleAdapter.setData(huoyueLists);

        }

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        LogUtils.w(e);
    }

}
