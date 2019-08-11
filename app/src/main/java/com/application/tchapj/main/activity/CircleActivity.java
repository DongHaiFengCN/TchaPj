package com.application.tchapj.main.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.main.adapter.CircleAdapter;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.bean.MyFilterParamsBean;
import com.application.tchapj.main.bean.MyFilterTabBean;
import com.application.tchapj.main.presenter.CirclePresenter;
import com.application.tchapj.main.view.ICircleView;
import com.application.tchapj.main.wigiter.MyPopEntityLoaderImp;
import com.application.tchapj.main.wigiter.MyResultLoaderImp;
import com.application.tchapj.search.activity.SearchActivity;
import com.application.tchapj.utils2.poptabview.FilterConfig;
import com.application.tchapj.utils2.poptabview.PopTabView;
import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.bean.FilterGroup;
import com.application.tchapj.utils2.poptabview.filter.sort.SortPopupWindow;
import com.application.tchapj.utils2.poptabview.listener.OnPopTabSetListener;
import com.application.tchapj.widiget.ToolbarHelper;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by 安卓开发 on 2018/8/9.
 */
// 全部圈子
public class CircleActivity extends BaseMvpActivity<ICircleView, CirclePresenter>
        implements ICircleView ,OnPopTabSetListener<MyFilterParamsBean> {

    @BindView(R.id.toolbar_menu_iv)
    ImageView toolbar_menu_iv;

    @BindView(R.id.circle_expandpop)
    PopTabView circle_popTabView;

    @BindView(R.id.circle_expandpop2)
    PopTabView circle_popTabView2;

    @BindView(R.id.circle_recyclerview)
    RecyclerView circle_recyclerview;
    @BindView(R.id.circle_refreshLayout)
    SmartRefreshLayout circle_refreshLayout;
    @BindView(R.id.person_iv_empty)
    ImageView mIvEmpty;

    private String circleTypeId ="";
    private String circleName ;
    private String dystate ="";
    private String pyqstate ="";
    private String wbstate ="";
    private int pageNum = 1;
    private int pageSize = 10;


    // 分类
    private List<HomeCircleModel.HomeCircleModelResult.HomeCircle> circletypeList = new ArrayList<>();

    // 服务
    private List<String> serviceItemList = new ArrayList<>();

    private List<String> newserviceItemList = new ArrayList<>();


    private List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> InfoCircles = new ArrayList<>();

    private CircleAdapter circleAdapter;

    ToolbarHelper mToolbarHelper;

    @Override
    public int getRootViewId() {
        return R.layout.activity_circle;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        circleTypeId = getIntent().getStringExtra("circleTypeId");
        circleName = getIntent().getStringExtra("circleName");

        if(circleName.equals(null)){
            toolbarHelper.setTitle("全部圈子");
        }else {
            toolbarHelper.setTitle(circleName);
        }



        mToolbarHelper = toolbarHelper;

    }


    @Override
    public void initUI() {

        getPresenter().getPersonSelectModelResult();

        getPresenter().getPersonSelectInfoModelResult(circleTypeId,"","","",pageNum+"",pageSize+"");

        circleAdapter = new CircleAdapter(this);
        circle_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        circle_recyclerview.setAdapter(circleAdapter);

        circle_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                if (InfoCircles!=null){
                    InfoCircles.clear();
                }
                requestData();
            }
        });

        circle_refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;

                requestData();
            }
        });

        toolbar_menu_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CircleActivity.this, SearchActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
            }
        });

    }


    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public CirclePresenter createPresenter() {
        return new CirclePresenter(getApp());
    }

    @Override // 分类
    public void onGetHomeCircleModelResult(HomeCircleModel homeCircleModel) {

        if ("000".equals(homeCircleModel.getCode())) {
            circletypeList = homeCircleModel.getData().getCircleTypeList();

            serviceItemList.add("抖音");
            serviceItemList.add("朋友圈");
            serviceItemList.add("微博");

            // FILTER_TYPE_SINGLE 单选
            FilterGroup filterGroup1 = getPopWindowData("分类", FilterConfig.TYPE_POPWINDOW_SORT, FilterConfig.FILTER_TYPE_SINGLE);

            // FILTER_TYPE_MUTIFY 多选
            FilterGroup filterGroup2 = getPopWindowData2("服务项目", FilterConfig.TYPE_POPWINDOW_SORT,FilterConfig.FILTER_TYPE_MUTIFY);

            circle_popTabView.setOnPopTabSetListener(this)
                    .setPopEntityLoader(new MyPopEntityLoaderImp())
                    .setResultLoader(new MyResultLoaderImp()) // 配置 {筛选类型}  方式
                    .addFilterItem(filterGroup1.getTab_group_name(), filterGroup1.getFilter_tab()
                            , filterGroup1.getTab_group_type(), filterGroup1.getSingle_or_mutiply());

            circle_popTabView.setOnPopTabSetListener(new OnPopTabSetListener() {
                @Override
                public void onPopTabSet(int index, String lable, Object params, String id, String selectTabName) {

                    pageNum = 1;

                    if(StringUtils.isEmpty(id)){
                        circleTypeId = "";
                    }else{
                        circleTypeId = id;
                    }

                    requestData();
                }

                @Override
                public void onResetClick() {
//                    circleTypeId = "";
//                    requestData();
                }
            });


            circle_popTabView.setDefaultSelectedItem(0, circleTypeId);



            circle_popTabView2.setOnPopTabSetListener(this)
                    .setPopEntityLoader(new MyPopEntityLoaderImp())
                    .setResultLoader(new MyResultLoaderImp()) // 配置 {筛选类型}  方式
                    .addFilterItem(filterGroup2.getTab_group_name(), filterGroup2.getFilter_tab()
                            , filterGroup2.getTab_group_type(), filterGroup2.getSingle_or_mutiply());

            circle_popTabView2.setOnPopTabSetListener(new OnPopTabSetListener() {
                @Override
                public void onPopTabSet(int index, String lable, Object params, String ids, String selectTabNames) {


                    dystate = "";
                    pyqstate = "";
                    wbstate = "";
                    if(!StringUtils.isEmpty(ids)){
                        String[] idArray = ids.split(",");
                        for (int i = 0; i < idArray.length; i++) {
                            if(idArray[i].equals("1")){
                                dystate = "1";
                            } else if(idArray[i].equals("2")){
                                pyqstate = "1";
                            } else if(idArray[i].equals("3")){
                                wbstate = "1";
                            }
                        }

                    }

                    pageNum = 1;
                    requestData();

                }

                @Override
                public void onResetClick() {
//                    dystate = "";
//                    pyqstate = "";
//                    wbstate = "";
//                    requestData();
                }
            });


        }
    }


    @Override // 详情
    public void onGetHomeCircleInfoModelResult(HomeCircleInfoModel homeCircleInfoModel) {

        if ("000".equals(homeCircleInfoModel.getCode())) {

            if (pageNum==1){
                InfoCircles = homeCircleInfoModel.getData().getMedia_own();
            }else{
                if(homeCircleInfoModel.getData().getMedia_own() != null && homeCircleInfoModel.getData().getMedia_own().size() > 0){
                    InfoCircles.addAll(homeCircleInfoModel.getData().getMedia_own());
                }else{
                    showToast(getString(R.string.load_more_empty));
                }

            }

            circleAdapter.setData(InfoCircles);


        }
        if (circle_refreshLayout.isEnableRefresh()){
            circle_refreshLayout.finishRefresh();
        }
        if (circle_refreshLayout.isEnableLoadMore()){
            circle_refreshLayout.finishLoadMore();
        }

    }

    // 筛选器的 数据格式 都是大同小异  要点:泛型处理,集合都用父类,实体都用子类表示.
    public FilterGroup getPopWindowData(String groupName, int groupType, int singleOrMutiply ) {

        FilterGroup filterGroup = new FilterGroup();

        filterGroup.setTab_group_name(groupName);
        filterGroup.setTab_group_type(groupType);
        filterGroup.setSingle_or_mutiply(singleOrMutiply);

        List<BaseFilterTabBean> singleFilterList = new ArrayList<>();

        MyFilterTabBean myFilterBean = new MyFilterTabBean();
        myFilterBean.setTab_name(""); // 一级数据

        List<MyFilterTabBean.MyChildFilterBean> childFilterList = new ArrayList<>();
        for (int i = 0; i < circletypeList.size(); i++) {//二级filter
            MyFilterTabBean.MyChildFilterBean myChildFilterBean = new MyFilterTabBean.MyChildFilterBean();
            myChildFilterBean.setTab_name(circletypeList.get(i).getName());
            myChildFilterBean.setTab_id(circletypeList.get(i).getId());

            childFilterList.add(myChildFilterBean);
        }
        //增加二级tab
        myFilterBean.setTabs(childFilterList);

        //增加一级tab
        singleFilterList.add(myFilterBean);

        filterGroup.setFilter_tab(singleFilterList);
        return filterGroup;

    }

    public FilterGroup getPopWindowData2(String groupName, int groupType, int singleOrMutiply ) {

        FilterGroup filterGroup = new FilterGroup();

        filterGroup.setTab_group_name(groupName);
        filterGroup.setTab_group_type(groupType);
        filterGroup.setSingle_or_mutiply(singleOrMutiply);

        List<BaseFilterTabBean> singleFilterList = new ArrayList<>();

        MyFilterTabBean myFilterBean = new MyFilterTabBean();
        myFilterBean.setTab_name(""); // 一级数据

        List<MyFilterTabBean.MyChildFilterBean> childFilterList = new ArrayList<>();
        for (int i = 0; i < serviceItemList.size(); i++) {//二级filter
            MyFilterTabBean.MyChildFilterBean myChildFilterBean = new MyFilterTabBean.MyChildFilterBean();
            myChildFilterBean.setTab_name(serviceItemList.get(i));
            myChildFilterBean.setTab_id(i + 1 +  "");
            childFilterList.add(myChildFilterBean);
        }
        //增加二级tab
        myFilterBean.setTabs(childFilterList);

        //增加一级tab
        singleFilterList.add(myFilterBean);

        filterGroup.setFilter_tab(singleFilterList);
        return filterGroup;

    }


    @Override
    public void onPopTabSet(int index, String lable, MyFilterParamsBean params, String id, String value) {

    }

    @Override
    public void onResetClick() {

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


    /**
     * 获取接口数据
     */
    private void requestData() {
        getPresenter().getPersonSelectInfoModelResult(circleTypeId,dystate,pyqstate,wbstate
                ,pageNum+"",pageSize+"");
    }

}

