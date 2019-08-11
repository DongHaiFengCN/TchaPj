package com.application.tchapj.main.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.main.adapter.PersonAdapter;
import com.application.tchapj.main.bean.MyFilterParamsBean;
import com.application.tchapj.main.bean.MyFilterTabBean;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.main.bean.PersonSelectModel;
import com.application.tchapj.main.presenter.PersonPresenter;
import com.application.tchapj.main.view.IPersonView;
import com.application.tchapj.main.wigiter.MyPopEntityLoaderImp;
import com.application.tchapj.main.wigiter.MyResultLoaderImp;
import com.application.tchapj.search.activity.SearchActivity;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.poptabview.FilterConfig;
import com.application.tchapj.utils2.poptabview.PopTabView;
import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.bean.FilterGroup;
import com.application.tchapj.utils2.poptabview.listener.OnPopTabSetListener;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.king.base.util.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Sun on 2018/6/24.
 */

public class PersonActivity extends BaseMvpActivity<IPersonView, PersonPresenter>
        implements IPersonView, OnPopTabSetListener<MyFilterParamsBean> {

    @BindView(R.id.toolbar_menu_iv)
    ImageView toolbar_menu_iv;

    @BindView(R.id.expandpop)
    PopTabView popTabView;

    @BindView(R.id.expandpop2)
    PopTabView popTabView2;

    @BindView(R.id.ph_ll)
    LinearLayout ph_ll;
    @BindView(R.id.ll_hot_more)
    LinearLayout ll_hot_more;

    @BindView(R.id.iv_hot1)
    CircleImageView iv_hot1;
    @BindView(R.id.tv_hot1)
    TextView tv_hot1;
    @BindView(R.id.tv_hot1_value)
    TextView tv_hot1_value;

    @BindView(R.id.iv_hot2)
    CircleImageView iv_hot2;
    @BindView(R.id.tv_hot2)
    TextView tv_hot2;
    @BindView(R.id.tv_hot2_value)
    TextView tv_hot2_value;

    @BindView(R.id.iv_hot3)
    CircleImageView iv_hot3;
    @BindView(R.id.tv_hot3)
    TextView tv_hot3;
    @BindView(R.id.tv_hot3_value)
    TextView tv_hot3_value;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.person_iv_empty)
    ImageView mIvEmpty;

    private int pageNum = 1;
    private int pageSize = 10;
    private String newsId = "1";
    private String serviceId = "1";
    private String jumpType = ""; //0-名人  1-媒体

    // 分类
    private List<PersonSelectModel.PersonSelectModelResult.NewStypeSelect> newstypeList = new ArrayList<>();

    // 服务
    private List<PersonSelectModel.PersonSelectModelResult.ServiceItemSelect> serviceItemList = new ArrayList<>();


    private List<PersonMediaModel.PersonMediaModelResult.PersonMedia> personMedias = new ArrayList<>();

    private List<PersonMediaModel.PersonMediaModelResult.HuoYue> huoYues = new ArrayList<>();

    private PersonMediaModel.PersonMediaModelResult.HuoYue huoYue1 ;
    private PersonMediaModel.PersonMediaModelResult.HuoYue huoYue2 ;
    private PersonMediaModel.PersonMediaModelResult.HuoYue huoYue3 ;

    private PersonAdapter personAdapter;

    ToolbarHelper mToolbarHelper;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        jumpType = getIntent().getStringExtra("type");
        if ("0".equals(jumpType)) {
            ph_ll.setVisibility(View.VISIBLE);
            toolbarHelper.setTitle("全部名人");

        } else if ("1".equals(jumpType)) {
            ph_ll.setVisibility(View.GONE);
            toolbarHelper.setTitle("全部媒体");

        }

        mToolbarHelper = toolbarHelper;
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_person;
    }

    @Override
    public void initUI() {

        getPresenter().getPersonSelectModelResult();
        getData();

        personAdapter = new PersonAdapter(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(personAdapter);

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getData();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getData();
            }
        });

        initListener(); // 点击事件
    }


    private void initListener() {

        toolbar_menu_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, SearchActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
            }
        });

        ll_hot_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PersonActivity.this,"明星活跃周榜",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PersonActivity.this, MingPeopleListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {


    }

    @NonNull
    @Override
    public PersonPresenter createPresenter() {
        return new PersonPresenter(getApp());
    }

    /*@Override  // 全部
    public void onGetPersonMediaModelResult(PersonMediaModel personMediaModel) {

        if ("000".equals(personMediaModel.getCode())) {
            personMedias = personMediaModel.getData().getAllmingrenList();
            personAdapter.addDatas(personMedias);
            if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
            if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();
        }

    }*/

    @Override // 名人
    public void onGetPersonMediaResult(PersonMediaModel personMediaModel) {

        if ("000".equals(personMediaModel.getCode())) {

            if(pageNum == 1){

                if ("0".equals(jumpType)) {
                    //活跃名人
                    huoYues = personMediaModel.getData().getHuoyueList();
                    huoYue1 = huoYues.get(0);
                    huoYue2 = huoYues.get(1);
                    huoYue3 = huoYues.get(2);

                    RequestOptions options1 = new RequestOptions()
                            .centerCrop()
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.NONE);

                    Glide.with(PersonActivity.this)
                            .asBitmap()
                            .apply(options1)
                            .load(huoYue1.getHeadUrl())
                            .into(new BitmapImageViewTarget(iv_hot1) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.
                                                    create(PersonActivity.this.getResources(), resource);
                                    //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                    iv_hot1.setImageDrawable(circularBitmapDrawable);
                                }
                            });
                    iv_hot1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(App.getId()==null){
                                Intent intent = new Intent(PersonActivity.this, WebViewActivity.class);

                                intent.putExtra(WebViewActivity.URL_KEY
                                        , H5UrlData.PEROSNDETAILS2 + huoYue1.getId() + "&memberId=");
                                intent.putExtra(WebViewActivity.TITLE, "");
                                PersonActivity.this.startActivity(intent);
                            }else {
                                Intent intent = new Intent(PersonActivity.this, WebViewActivity.class);

                                intent.putExtra(WebViewActivity.URL_KEY
                                        , H5UrlData.PEROSNDETAILS2 + huoYue1.getId() + "&memberId=" + App.getId());
                                intent.putExtra(WebViewActivity.TITLE, "");
                                PersonActivity.this.startActivity(intent);
                            }

                        }
                    });
                    tv_hot1.setText(huoYue1.getNickName());
                    tv_hot1_value.setText(huoYue1.getActivity());

                    RequestOptions options2 = new RequestOptions()
                            .centerCrop()
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.NONE);

                    Glide.with(PersonActivity.this)
                            .asBitmap()
                            .apply(options2)
                            .load(huoYue2.getHeadUrl())
                            .into(new BitmapImageViewTarget(iv_hot2) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.
                                                    create(PersonActivity.this.getResources(), resource);
                                    //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                    iv_hot2.setImageDrawable(circularBitmapDrawable);
                                }
                            });

                    iv_hot2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(App.getId()==null){
                                Intent intent = new Intent(PersonActivity.this, WebViewActivity.class);

                                intent.putExtra(WebViewActivity.URL_KEY
                                        , H5UrlData.PEROSNDETAILS2 + huoYue2.getId() + "&memberId=");
                                intent.putExtra(WebViewActivity.TITLE, "");
                                PersonActivity.this.startActivity(intent);
                            }else {
                                Intent intent = new Intent(PersonActivity.this, WebViewActivity.class);

                                intent.putExtra(WebViewActivity.URL_KEY
                                        , H5UrlData.PEROSNDETAILS2 + huoYue2.getId() + "&memberId=" + App.getId());
                                intent.putExtra(WebViewActivity.TITLE, "");
                                PersonActivity.this.startActivity(intent);
                            }
                        }
                    });

                    tv_hot2.setText(huoYue2.getNickName());
                    tv_hot2_value.setText(huoYue2.getActivity());


                    RequestOptions options3 = new RequestOptions()
                            .centerCrop()
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.NONE);

                    Glide.with(PersonActivity.this)
                            .asBitmap()
                            .apply(options3)
                            .load(huoYue3.getHeadUrl())
                            .into(new BitmapImageViewTarget(iv_hot3) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.
                                                    create(PersonActivity.this.getResources(), resource);
                                    //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                    iv_hot3.setImageDrawable(circularBitmapDrawable);
                                }
                            });

                    iv_hot3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(App.getId()==null){
                                Intent intent = new Intent(PersonActivity.this, WebViewActivity.class);

                                intent.putExtra(WebViewActivity.URL_KEY
                                        , H5UrlData.PEROSNDETAILS2 + huoYue3.getId() + "&memberId=");
                                intent.putExtra(WebViewActivity.TITLE, "");
                                PersonActivity.this.startActivity(intent);
                            }else {
                                Intent intent = new Intent(PersonActivity.this, WebViewActivity.class);

                                intent.putExtra(WebViewActivity.URL_KEY
                                        , H5UrlData.PEROSNDETAILS2 + huoYue3.getId() + "&memberId=" + App.getId());
                                intent.putExtra(WebViewActivity.TITLE, "");
                                PersonActivity.this.startActivity(intent);
                            }
                        }
                    });

                    tv_hot3.setText(huoYue3.getNickName());
                    tv_hot3_value.setText(huoYue3.getActivity());

                }

                personMedias = personMediaModel.getData().getClassnewsList();
                if (personMedias.size() != 0) {
                    personAdapter.setDatas(personMedias);
                    mIvEmpty.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);
                } else {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    recyclerview.setVisibility(View.GONE);
                }

            }else{
                personMedias = personMediaModel.getData().getClassnewsList();
                if(personMedias != null && personMedias.size() > 0){
                    personAdapter.addDatas(personMedias);
                }else{
                    showToast(getString(R.string.load_more_empty));
                }

            }

            if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
            if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();

        }
    }

   /* @Override  // 全部 Most
    public void onGetPersonMediaModelResultMost(PersonMediaModel personMediaModel) {
        if ("000".equals(personMediaModel.getCode())) {
            personMedias = personMediaModel.getData().getAllmingrenList();

            if (personMedias.size() != 0) {
                personAdapter.setDatas(personMedias);
                mIvEmpty.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
            } else {
                mIvEmpty.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);
            }

            if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
            if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();
        }
    }*/


    @Override // 分类
    public void onGetPersonSelectModelResult(PersonSelectModel personSelectModel) {
        if ("000".equals(personSelectModel.getCode())) {
            newstypeList = personSelectModel.getData().getNewstypeList();
            serviceItemList = personSelectModel.getData().getServiceItemList();
            //showPopWindow();

            // FILTER_TYPE_SINGLE 单选
            FilterGroup filterGroup1 = getPopWindowData("分类", FilterConfig.TYPE_POPWINDOW_SORT, FilterConfig.FILTER_TYPE_SINGLE);

            // FILTER_TYPE_MUTIFY 多选
            FilterGroup filterGroup2 = getPopWindowData2("服务项目", FilterConfig.TYPE_POPWINDOW_SORT, FilterConfig.FILTER_TYPE_MUTIFY);

            popTabView.setOnPopTabSetListener(this)
                    .setPopEntityLoader(new MyPopEntityLoaderImp())
                    .setResultLoader(new MyResultLoaderImp()) // 配置 {筛选类型}  方式
                    .addFilterItem(filterGroup1.getTab_group_name(), filterGroup1.getFilter_tab()
                            , filterGroup1.getTab_group_type(), filterGroup1.getSingle_or_mutiply());

            popTabView.setOnPopTabSetListener(new OnPopTabSetListener() {
                @Override
                public void onPopTabSet(int index, String lable, Object params, String id, String value) {

                    pageNum = 1;
                    if (value == null) {
                        newsId = "1";
                    } else {
                        newsId = newstypeList.get(Integer.parseInt(id) - 1).getId();
                    }
                    getData();
                }

                @Override
                public void onResetClick() {

                }
            });

            popTabView2.setOnPopTabSetListener(this)
                    .setPopEntityLoader(new MyPopEntityLoaderImp())
                    .setResultLoader(new MyResultLoaderImp()) // 配置 {筛选类型}  方式
                    .addFilterItem(filterGroup2.getTab_group_name(), filterGroup2.getFilter_tab()
                            , filterGroup2.getTab_group_type(), filterGroup2.getSingle_or_mutiply());

            popTabView2.setOnPopTabSetListener(new OnPopTabSetListener() {
                @Override
                public void onPopTabSet(int index, String lable, Object params, String id, String value) {
                    pageNum = 1;

                    if (value == null) {
                        serviceId = "1";
                    } else {
                        serviceId = value;
                    }
                    getData();
                }

                @Override
                public void onResetClick() {

                }
            });


        }
    }

    /*private void showPopWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list_layout, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();
        popWindow.showAsDropDown(toolbar_menu_title, 0, 10);

        popAdapter.setTextClickListeren(new ListTextAdapter.OnTextClickListeren() {
            @Override
            public void Click(String id, String name) {
                pageNum = 1;
                newsId = id;
                type = name;
                popWindow.dissmiss();
                getData();
            }
        });
    }

    private void handleListView(View contentView) {
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        popAdapter = new ListTextAdapter(this);
        Collections.reverse(newstypeList);
        popAdapter.setDatas(newstypeList);
        recyclerView.setAdapter(popAdapter);

    }*/


    // 筛选器的 数据格式 都是大同小异  要点:泛型处理,集合都用父类,实体都用子类表示.
    //分类
    public FilterGroup getPopWindowData(String groupName, int groupType, int singleOrMutiply) {

        FilterGroup filterGroup = new FilterGroup();

        filterGroup.setTab_group_name(groupName);
        filterGroup.setTab_group_type(groupType);
        filterGroup.setSingle_or_mutiply(singleOrMutiply);

        List<BaseFilterTabBean> singleFilterList = new ArrayList<>();

        MyFilterTabBean myFilterBean = new MyFilterTabBean();
        myFilterBean.setTab_name(""); // 一级数据

        List<MyFilterTabBean.MyChildFilterBean> childFilterList = new ArrayList<>();
        for (int i = 0; i < newstypeList.size(); i++) {//二级filter
            MyFilterTabBean.MyChildFilterBean myChildFilterBean = new MyFilterTabBean.MyChildFilterBean();
            myChildFilterBean.setTab_name(newstypeList.get(i).getName());
            myChildFilterBean.setTab_id(i + 1 + "");

            childFilterList.add(myChildFilterBean);
        }
        //增加二级tab
        myFilterBean.setTabs(childFilterList);

        //增加一级tab
        singleFilterList.add(myFilterBean);

        filterGroup.setFilter_tab(singleFilterList);
        return filterGroup;

    }

    //服务项目
    public FilterGroup getPopWindowData2(String groupName, int groupType, int singleOrMutiply) {

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
            myChildFilterBean.setTab_name(serviceItemList.get(i).getName());
            myChildFilterBean.setTab_id(i + 1 + "");

            childFilterList.add(myChildFilterBean);
        }
        //增加二级tab
        myFilterBean.setTabs(childFilterList);

        //增加一级tab
        singleFilterList.add(myFilterBean);

        filterGroup.setFilter_tab(singleFilterList);
        return filterGroup;

    }

    // 刷新
    public void getData() {

        getPresenter().getPersonMediaResult(jumpType, newsId, serviceId, pageNum + "", "" + pageSize);

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


}
