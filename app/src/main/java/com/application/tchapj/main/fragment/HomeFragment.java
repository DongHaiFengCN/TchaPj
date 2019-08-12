package com.application.tchapj.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.login.activity.LoginMainActivity;
import com.application.tchapj.main.activity.CircleActivity;
import com.application.tchapj.main.activity.MessagenotificationActivity;
import com.application.tchapj.main.activity.PersonActivity;
import com.application.tchapj.main.adapter.GridViewAdapter;
import com.application.tchapj.main.adapter.HomeCircleAdapter;
import com.application.tchapj.main.adapter.HomeFollowAdapter;
import com.application.tchapj.main.adapter.HomeMediaAdapter;
import com.application.tchapj.main.adapter.HomeMediaAdapter2;
import com.application.tchapj.main.adapter.ViewPagerAdapter;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.bean.HomeMediaList;
import com.application.tchapj.main.bean.HomePerson;
import com.application.tchapj.main.bean.HomeTopData;
import com.application.tchapj.main.bean.StartInitiationDataModel;
import com.application.tchapj.main.presenter.HomePresenter;
import com.application.tchapj.main.view.IHomeView;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.search.activity.SearchActivity;
import com.application.tchapj.utils.UpdateManager;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.TypeTextView;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.DensityUtil;
import com.application.tchapj.widiget.KV;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

// 首页Fragment
public class HomeFragment extends BaseMvpFragment<IHomeView, HomePresenter> implements IHomeView {


    @BindView(R.id.toolbar2)
    Toolbar toolbar2;

    @BindView(R.id.ScrollView)
    NestedScrollView ScrollView;

    @BindView(R.id.search_rl)
    RelativeLayout search_rl;

    @BindView(R.id.toutiao)
    RelativeLayout toutiao;

    @BindView(R.id.person_more)
    TextView person_more;
    @BindView(R.id.et_search)
    TypeTextView et_search;

    // 消息
    @BindView(R.id.ic_message)
    ImageView ic_message;

    // 消息标签
    @BindView(R.id.ic_spot)
    ImageView ic_spot;

    @BindView(R.id.banner)
    ConvenientBanner banner;

    // 微相应
    @BindView(R.id.ll_response)
    LinearLayout ll_response;

    // 新闻头条
    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;

    /*@BindView(R.id.personView01)
    HomePersonView personView01;
    @BindView(R.id.personView02)
    HomePersonView personView02;
    @BindView(R.id.personView03)
    HomePersonView personView03;
    @BindView(R.id.personView04)
    HomePersonView personView04;*/
    /*@BindView(R.id.homeCircleView01)
    HomeCircleView homeCircleView01;
    @BindView(R.id.homeCircleView02)
    HomeCircleView homeCircleView02;
    @BindView(R.id.homeCircleView03)
    HomeCircleView homeCircleView03;*/

    // 微关注
    @BindView(R.id.follow_recycle)
    RecyclerView follow_recycle;

    // 找名人
    @BindView(R.id.viewpager)
    ViewPager mPager;
    @BindView(R.id.ll_dot)
    LinearLayout mLlDot;

    // 找媒体
    @BindView(R.id.media_view_pager)
    ViewPager media_view_pager;
    @BindView(R.id.media_ll_dot)
    LinearLayout media_ll_dot;

    // 新找圈子
    @BindView(R.id.circle_recycle)
    RecyclerView circle_recycle;

    private HomeFragmentListener homeFragmentListener;

    private float headerHeight;//顶部高度
    private float minHeaderHeight;//顶部最低高度，即Bar的高度

    private HomeMediaFragment mediaFragment;
    private List<Fragment> fragments;
    private List<String> imags = new ArrayList<>();
    private List<HomeTopData.HomeTopDataResult.HomeBanner> banners = new ArrayList<>();

    private HomeFollowAdapter followAdapter;

    private HomeMediaAdapter mediaAdapter;

    private HomeCircleAdapter circleAdapter;

    private String[] titles;
    private ArrayList<String> Titles = new ArrayList();

    private LayoutInflater inflater;
    private LayoutInflater inflater2;

    private int pageCount; // 总的页数
    private int pageSize = 4; // 每一页显示的个数
    private int curIndex = 0; // 当前显示的是第几页

    private int pageSize2 = 6; // 每一页显示的个数

    // 接收参数
    public static HomeFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {
     /*   int statusBarHeight = Utils.getStatusBarHeight(getActivity());
        ((MainActivity) getActivity()).setTopBarMarginTop(statusBarHeight);*/

        ScrollView.smoothScrollTo(0, 0);//设置scrollview焦点在顶部
        follow_recycle.setFocusable(false);
        circle_recycle.setFocusable(false);

 /*       if (SharedPreferencesUtils.getInstance().getUserInfo() != null && SharedPreferencesUtils.getInstance().getUserInfo() != null
                && !SharedPreferencesUtils.getInstance().getUserInfo().equals("0")) {
            ic_spot.setVisibility(View.VISIBLE);
        } else {
            ic_spot.setVisibility(View.GONE);
        }*/

        Titles.add("找名人为产品做品牌代言？");
        Titles.add("找媒体为企业做营销策划？");
        Titles.add("找达人为店铺做活动宣传？");
        Titles.add("点击搜索名人、媒体、圈子达人");

        et_search.start("查找名人、平台、个人自媒体");
        headerHeight = getResources().getDimension(R.dimen.dimen_160);
        minHeaderHeight = getResources().getDimension(R.dimen.abc_action_bar_default_height_material);

    }

    /**
     * @param view
     */
    // 点击事件  R.id.circle_more, 老版本找圈子的更多
    @OnClick({R.id.search_rl, R.id.follow_more, R.id.person_more
            , R.id.media_more, R.id.ll_person, R.id.ll_media
            , R.id.ll_circle, R.id.ll_response, R.id.ic_message})
    public void onViewClicked(View view) {
        Intent intent;
        hideSoftInput();
        switch (view.getId()) {
         /*   case R.id.home:
                hideSoftInput();
                break;*/

            case R.id.search_rl: // 搜索
                intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;

            case R.id.follow_more: // 微关注
                intent = new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;

            case R.id.ll_person: //找名人
                intent = new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.ll_circle: //找圈子
                intent = new Intent(getActivity(), CircleActivity.class);
                intent.putExtra("circleTypeId", "");
                intent.putExtra("circleName", "全部圈子");
                startActivity(intent);
                break;
            case R.id.ll_media: //找媒体
                intent = new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;

            case R.id.person_more: //名人
                intent = new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.media_more: //媒体
                intent = new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
           /* case R.id.circle_more: // 老版本找圈子
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL_KEY, H5UrlData.CircleList);
                intent.putExtra(WebViewActivity.TITLE, "");
                startActivity(intent);
                break;*/

            case R.id.ll_response: //微任务(打开任务tab)
//                intent = new Intent(getActivity(), MyFragment.class);
//                startActivity(intent);
                homeFragmentListener.tackImgClick();
                break;
            case R.id.ic_message: //消息

                if (App.getId() == null) {
                    intent = new Intent(getActivity(), LoginMainActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), MessagenotificationActivity.class);
                    startActivity(intent);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        getPresenter().onGetQiniuResult();//七牛token

        getNewestVersion();//版本更新

        getPresenter().getHomeBanne();   // 轮播图
        getPresenter().getHomePerson();  // 名人
        getPresenter().getHomeMediaList(); // 媒体
        getPresenter().getHomeCircleModel(); // 圈子
        getPresenter().onGetAlipayPrivateKeyBeanResult();
    }

    /**
     * 版本更新
     */
    private void getNewestVersion() {
        StartInitiationDataModel startInitiationDataModel = SharedPreferencesUtils.getInstance().getStartInitiationData();
        if (startInitiationDataModel != null && startInitiationDataModel.getVersionInfo() != null) {
            checkUpdateApk(startInitiationDataModel.getVersionInfo().getVersionCode(), startInitiationDataModel.getVersionInfo().getApkUrl());
        }
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
    }

    @Override // 轮播图
    public void onGetHomeBannerResult(HomeTopData homeTopData) {

        if ("000".equals(homeTopData.getCode())) {
            HomeTopData.HomeTopDataResult data = homeTopData.getData();

            initBannerView(data.getLadv()); // 轮播图
            initViewFlipper(data.getNews()); // 头条
            initTinyFollow(data.getNews()); // 微关注
        }

    }

    // 轮播图
    private void initBannerView(final List<HomeTopData.HomeTopDataResult.HomeBanner> bannerList) {

        if (bannerList.size() > 0) {
            banners = bannerList;

            banner.setPages(new CBViewHolderCreator() {
                @Override
                public Holder<HomeTopData.HomeTopDataResult.HomeBanner> createHolder() {
                    return new ImageHolder();
                }
            }, banners)
                    .setPageIndicator(new int[]{R.drawable.ic_dot_normal, R.drawable.ic_dot_pressed})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

            if (!banner.isTurning()) {
                banner.startTurning(4000);
            }

            banner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                    String name = banners.get(position).getName();
                    String id = banners.get(position).getId();
                    String href = banners.get(position).getHref();
                    String shareContent = banners.get(position).getIntro();
                    String shareSmallImg = banners.get(position).getLitimg();
                    //startWeb("http://124.133.43.12:8087/weihubaiying/gh5.html", "");
                    WebViewActivity.startShare(getActivity(), "All", href, name, shareContent, shareSmallImg);
                }
            });
        }

    }

    // 轮播图点击跳转
    public void startWeb(String url, String title) {

        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL_KEY, url);
        intent.putExtra(WebViewActivity.TITLE, title);
        startActivity(intent);
    }

    // 初始化滚动 头条
    private void initViewFlipper(final List<HomeTopData.HomeTopDataResult.HomeTopNews> news) {

        if (news.size() > 0) {

            final int size = news.size();

            //两条滚动条
       /* for (int i = 0; i < (size / 2 + 1); i++) {
            View v = View.inflate(getContext(), R.layout.viewflipper_add_one_layout, null);
            TextView tv1 = (TextView) v.findViewById(R.id.tv01);
            TextView tv2 = (TextView) v.findViewById(R.id.tv02);
            int pos = i * 2;
            tv1.setText(news.get(pos).getTitle());
            if (pos + 1 < size) {
                tv2.setText(news.get(pos + 1).getTitle());
            }
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),WebViewActivity.class);
                    intent.putExtra(WebViewActivity.URL_KEY, H5UrlData.ZIXUN+news.get(pos).getId());
                    startActivity(intent);
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),WebViewActivity.class);
                    intent.putExtra(WebViewActivity.URL_KEY,H5UrlData.ZIXUN+news.get(pos+1).getId());
                    startActivity(intent);
                }
            });
            viewFlipper.addView(v);
        }*/


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        for (int i = 0; i < 3; i++) {
                            Thread.sleep(6000);//先休眠3秒

                            if (et_search != null) {
                                et_search.start(Titles.get(i));//开始展示
                            }
                        }

                        /*for (int i = 0; i < size; i++) {
                            Thread.sleep(6000);//先休眠3秒

                            et_search.start(news.get(i).getTitle());//开始展示
                        }*/


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

            //1条滚动条
            for (int i = 0; i < size; i++) {
                View v = View.inflate(getContext(), R.layout.viewflipper_add_one_layout, null);
                TextView tv1 = (TextView) v.findViewById(R.id.tv01);
//            TextView tv02 = (TextView) v.findViewById(R.id.tv02);
                tv1.setText(news.get(i).getTitle());
//            tv02.setText(news.get(i+1).getTitle());
                final int finalI = i;
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(WebViewActivity.URL_KEY, H5UrlData.ZIXUN + news.get(finalI).getId());
                        startActivity(intent);
                    }
                });
                viewFlipper.addView(v);
            }

        }


    }

    // 初始化微关注
    private void initTinyFollow(final List<HomeTopData.HomeTopDataResult.HomeTopNews> news) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        // 设置焦点
        follow_recycle.setNestedScrollingEnabled(false);
        follow_recycle.setHasFixedSize(true);

        follow_recycle.setLayoutManager(linearLayoutManager);
        followAdapter = new HomeFollowAdapter(getContext());
        follow_recycle.setAdapter(followAdapter);

        followAdapter.setDatas(news);


    }

    @Override // 找名人
    public void onGetHomePersonResult(HomePerson homePerson) {

        // 新找名人
        if ("000".equals(homePerson.getCode())) {

            final List<HomePerson.HomePersonResult.HomeMingren> mingrenList = homePerson.getData().getMingrenList();

            inflater = LayoutInflater.from(getApp());
            //总的页数=总数/每页数量，并取整
            pageCount = (int) Math.ceil(mingrenList.size() * 1.0 / pageSize);
            List<View> mPagerList = new ArrayList<View>();
            for (int i = 0; i < pageCount; i++) {
                //每个页面都是inflate出一个新实例
                GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
                gridView.setAdapter(new GridViewAdapter(getApp(), mingrenList, i, pageSize));
                mPagerList.add(gridView);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int pos = position + curIndex * pageSize;

                        if (App.getId() == null) {
                            Intent intent = new Intent(getActivity(), WebViewActivity.class);

                            intent.putExtra(WebViewActivity.URL_KEY
                                    , H5UrlData.PEROSNDETAILS2 + mingrenList.get(pos).getId() + "&memberId=");
                    /*        intent.putExtra(WebViewActivity.URL_KEY
                                    ,  mingrenList.get(pos).getId());*/

                            intent.putExtra(WebViewActivity.TITLE, "");
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), WebViewActivity.class);

                            intent.putExtra(WebViewActivity.URL_KEY
                                    , H5UrlData.PEROSNDETAILS2 + mingrenList.get(pos).getId() + "&memberId=" + App.getId());
                   /*         intent.putExtra(WebViewActivity.URL_KEY
                                    ,  mingrenList.get(pos).getId());*/
                            intent.putExtra(WebViewActivity.TITLE, "");
                            startActivity(intent);
                        }

                    }
                });
            }
            //设置适配器
            mPager.setAdapter(new ViewPagerAdapter(mPagerList));

            //设置圆点
            setOvalLayout();

        }

        /*if ("000".equals(homePerson.getCode())) {
            // HomePerson data = model.getData();
            List<HomePerson.HomePersonResult.HomeMingren> mingrenList = homePerson.getData().getMingrenList();
            personView01.setData(mingrenList.get(0));
            personView02.setData(mingrenList.get(1));
            personView03.setData(mingrenList.get(2));
            personView04.setData(mingrenList.get(3));
        }*/
    }

    @Override // 找媒体
    public void onGetHomeMediaListResult(HomeMediaList homeMediaList) {

        if ("000".equals(homeMediaList.getCode())) {
           /* List<HomeMediaList.HomeMediaListResult.HomeMediaModel> mediaList = homeMediaList.getData().getMediaList();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            // 设置焦点
            media_recycle.setNestedScrollingEnabled(false);
            media_recycle.setHasFixedSize(true);

            media_recycle.setLayoutManager(linearLayoutManager);
            mediaAdapter = new HomeMediaAdapter(getContext());
            media_recycle.setAdapter(mediaAdapter);

            mediaAdapter.setDatas(mediaList);*/

            final List<HomeMediaList.HomeMediaListResult.HomeMediaModel> mediaList = homeMediaList.getData().getMediaList();

            inflater2 = LayoutInflater.from(getApp());
            //总的页数=总数/每页数量，并取整
            pageCount = (int) Math.ceil(mediaList.size() * 1.0 / pageSize2);
            List<View> mPagerList = new ArrayList<View>();
            for (int i = 0; i < pageCount; i++) {
                //每个页面都是inflate出一个新实例
                GridView gridView = (GridView) inflater2.inflate(R.layout.gridview2, media_view_pager, false);
                gridView.setAdapter(new HomeMediaAdapter2(getApp(), mediaList, i, pageSize2));
                mPagerList.add(gridView);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int pos = position + curIndex * pageSize2;

                        if (App.getId() == null) {
                            Intent intent = new Intent(getActivity(), WebViewActivity.class);

                            intent.putExtra(WebViewActivity.URL_KEY
                                    , H5UrlData.PEROSNDETAILS2 + mediaList.get(pos).getId() + "&memberId=");
                            intent.putExtra(WebViewActivity.TITLE, "");
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), WebViewActivity.class);

                            intent.putExtra(WebViewActivity.URL_KEY
                                    , H5UrlData.PEROSNDETAILS2 + mediaList.get(pos).getId() + "&memberId=" + App.getId());
                            intent.putExtra(WebViewActivity.TITLE, "");
                            startActivity(intent);
                        }

                    }
                });
            }
            //设置适配器
            media_view_pager.setAdapter(new ViewPagerAdapter(mPagerList));

            //设置圆点
            setOvalLayout2();

        }

    }

    @Override // 找圈子
    public void onGetHomeCircleModelResult(HomeCircleModel homeCircleModel) {


        // 老版本找圈子
        if ("000".equals(homeCircleModel.getCode())) {
            List<HomeCircleModel.HomeCircleModelResult.HomeCircle> circles = homeCircleModel.getData().getCircleTypeList();

            App.circles = homeCircleModel.getData().getCircleTypeList();

            SpaceDecoration spaceDecoration = new SpaceDecoration(DensityUtil.dp2px(getContext(), 6));
            circle_recycle.addItemDecoration(spaceDecoration);
            circleAdapter = new HomeCircleAdapter(getContext());

            circle_recycle.setNestedScrollingEnabled(false);
            circle_recycle.setHasFixedSize(true);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//            gridLayoutManager.setSpanSizeLookup(recommendAdapter.obtainGridSpanSizeLookUp(2));
            circle_recycle.setLayoutManager(gridLayoutManager);
            circle_recycle.setAdapter(circleAdapter);
            circleAdapter.setDatas(circles);

        }

        /*// 老版本找圈子
        if ("000".equals(homeCircleModel.getCode())) {
            List<HomeCircleModel.HomeCircleModelResult.HomeCircle> circles = homeCircleModel.getData().getCircleTypeList();
                   *//* homeCircleView01.setData(circles.get(0));
                    homeCircleView01.setData(circles.get(0));
                    homeCircleView01.setData(circles.get(0));*//*
            if (circles.size() > 0) {
                homeCircleView01.setData(circles.get(0));
                if (circles.size() > 1) {

                    homeCircleView02.setData(circles.get(2));
                }
                if (circles.size() > 2) {
                    homeCircleView03.setData(circles.get(3));
                }
            }
        }*/
    }

    @Override
    public void onGetQiniuBeanResult(QiniuBean qiniuBean) {
        if (qiniuBean != null && "000".equals(qiniuBean.getCode())) {
            if (qiniuBean.getData() != null) {
                App.QiniuToken = qiniuBean.getData().getUploadToken();
            }
        }
    }

    @Override
    public void onGetAlipayPrivateKeyBeanResult(AlipayPrivateKeyBean alipayPrivateKeyBean) {
        if ("000".equals(alipayPrivateKeyBean.getCode())) {
            String RSA2_PRIVATE = alipayPrivateKeyBean.getData().getPrivatekey();
            SharedPreferences.getInstance().setString(getString(R.string.RSA2_PRIVATE), RSA2_PRIVATE);

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

    // TODO: 2018/4/9 小红点
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // 隐藏输入法
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 获取软键盘的显示状态
        boolean isOpen = imm.isActive();
        if (isOpen) {
            // 强制隐藏软键盘
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
        }
    }

    // 轮播图
    public class ImageHolder implements Holder<HomeTopData.HomeTopDataResult.HomeBanner> {

        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return iv;
        }

        @Override
        public void UpdateUI(final Context context, int position, HomeTopData.HomeTopDataResult.HomeBanner data) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(context)
                    .asBitmap()
                    .apply(options)
                    .load(data.getBannerImage())
                    .into(new BitmapImageViewTarget(iv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(context.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            iv.setImageDrawable(circularBitmapDrawable);
                        }
                    });

        }
    }


    private void setActionBar() {

        ScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {

                //Y轴偏移量
                float scrollY = nestedScrollView.getScrollY();

                //变化率
                float headerBarOffsetY = headerHeight - minHeaderHeight;//Toolbar与header高度的差值
                float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
                // 获取Drawable对象
                final Drawable mDrawable = ContextCompat.getDrawable(getApp(), R.drawable.search_bg);

                if (offset > 0) {

// 设置Drawable的透明度
                    mDrawable.setAlpha(255);
// 给Toolbar设置背景图
                    toolbar2.setBackgroundDrawable(mDrawable);
                } else {

// 设置Drawable的透明度
                    mDrawable.setAlpha(0);
// 给Toolbar设置背景图
                    toolbar2.setBackgroundDrawable(mDrawable);
                }

                final Drawable mDrawable2 = ContextCompat.getDrawable(getApp(), R.drawable.search_bg2);
                mDrawable2.setAlpha(255);
                toutiao.setBackgroundDrawable(mDrawable2);

            }
        });

    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }


    /**
     * 设置圆点2
     */
    public void setOvalLayout2() {
        for (int i = 0; i < pageCount; i++) {
            media_ll_dot.addView(inflater2.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        media_ll_dot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        media_view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 取消圆点选中
                media_ll_dot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                media_ll_dot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public interface HomeFragmentListener {
        void tackImgClick();

    }

    public void setHomeFragmentListener(HomeFragmentListener homeFragmentListener) {
        this.homeFragmentListener = homeFragmentListener;
    }


    //检查更新
    private void checkUpdateApk(int newestVersionCodeStr, String downloadStr) {
        if (!StringUtils.isEmpty(downloadStr)) {
            UpdateManager manager = UpdateManager.getInstance();
            manager.setContext(getActivity());
            manager.checkUpdate(newestVersionCodeStr, downloadStr);
        }
    }

}
