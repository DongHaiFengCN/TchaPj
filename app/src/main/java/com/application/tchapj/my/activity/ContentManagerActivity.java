package com.application.tchapj.my.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.consultation.activity.ChannelActivity;
import com.application.tchapj.consultation.activity.UploadVideoActivity;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.consultation.bean.IsAuthorData;
import com.application.tchapj.consultation.bean.UpdateAuthorData;
import com.application.tchapj.consultation.fragment.ConsultationFragment;
import com.application.tchapj.consultation.fragment.ConsultationInfoFragment;
import com.application.tchapj.consultation.presenter.ConsultationTobPresenter;
import com.application.tchapj.consultation.view.IConsultationTobView;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.rxbus.ChangeAnswerEvent;
import com.application.tchapj.rxbus.RxBus;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 内容管理
 */
public class ContentManagerActivity extends BaseMvpActivity<IConsultationTobView, ConsultationTobPresenter>  implements IConsultationTobView, View.OnClickListener {

    @BindView(R.id.content_manager_tabs)
    TabLayout tabs;
    @BindView(R.id.content_manager_viewpager)
    ViewPager viewpager;

    Subscription mSubscription;
    Unbinder unbinder;

    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    private List<CharSequence> listTitle; // 标题
    private List<CharSequence> listTitleId; // 标题

    List<CharSequence> listTemp;
    List<CharSequence> listTempId;

    private List<CharSequence> listTitle2; // 标题

    private List<Fragment> fragments;  // Fragment

    private ConsultationInfoFragment categoryFragment;

    private List<ConsultationTopModel.PersonSelectModelResult.NewStypeSelect> newStypeSelects = new ArrayList<>();

    private Context mContext;


    @Override
    public int getRootViewId() {
        return R.layout.activity_content_manager;
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("内容管理");
    }



    @Override
    public ConsultationTobPresenter createPresenter() {

        return new ConsultationTobPresenter(getApp());
    }

    @Override
    public void initUI() {
        mContext = this;
        listTitle = new ArrayList<>();
        listTitle2 = new ArrayList<>();
        listTemp = new ArrayList<>();
        listTempId = new ArrayList<>();
        fragments = new ArrayList<>();

        categoryFragment = new ConsultationInfoFragment();

        // 添加的
        mSubscription = RxBus.getDefault().toObserverable(ChangeAnswerEvent.class)
                .subscribe(new Action1<ChangeAnswerEvent>() {
                    @Override
                    public void call(ChangeAnswerEvent changeAnswerEvent) {

                        if (changeAnswerEvent.getTitles().size() != 0) {

                            listTemp.clear();
                            listTempId.clear();
                            fragments.clear();
                            listTemp.addAll(changeAnswerEvent.getTitles());
                            listTempId.addAll(changeAnswerEvent.getTitlesId());
                            for (int i = 0; i < listTemp.size(); i++) {
                                fragments.add(categoryFragment.newInstance((String) listTempId.get(i), ConsultationInfoFragment.FROM_TYPE.CONTENT_MANAGER_ACTIVITY.ordinal()));
                            }
                            toSetList(listTitle, listTemp, false);
                            toSetList(listTitleId, listTempId, false);

                            if (viewPagerFragmentAdapter != null) {
                                viewPagerFragmentAdapter.setListTitle(listTitle);
                                viewPagerFragmentAdapter.setListData(fragments);
                                viewPagerFragmentAdapter.notifyDataSetChanged();
                            }

                            viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, listTitle);

                            viewpager.setAdapter(viewPagerFragmentAdapter);

                            tabs.setupWithViewPager(viewpager);
                        }


                    }

                });


        //初始化数据
        fragments.clear();
        listTemp.clear();
        listTempId.clear();

        listTemp.add("全部");
        fragments.add(categoryFragment.newInstance("100", ConsultationInfoFragment.FROM_TYPE.CONTENT_MANAGER_ACTIVITY.ordinal()));
        listTemp.add("图文");
        fragments.add(categoryFragment.newInstance("1", ConsultationInfoFragment.FROM_TYPE.CONTENT_MANAGER_ACTIVITY.ordinal()));
        listTemp.add("视频");
        fragments.add(categoryFragment.newInstance("2", ConsultationInfoFragment.FROM_TYPE.CONTENT_MANAGER_ACTIVITY.ordinal()));


        toSetList(listTitle, listTemp, false);
        toSetList(listTitleId, listTempId, false);



        if (viewPagerFragmentAdapter != null) {
            viewPagerFragmentAdapter.setListTitle(listTitle);
            viewPagerFragmentAdapter.setListData(fragments);
            viewPagerFragmentAdapter.notifyDataSetChanged();
        }

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, listTitle);

        viewpager.setAdapter(viewPagerFragmentAdapter);

        tabs.setupWithViewPager(viewpager);
    }

    @Override
    public void initData() {


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


    // 得到全部咨询数据 respon
    @Override
    public void onGetConsultationTobResult(ConsultationTopModel consultationTopModel) {



    }



    //开通微呼百应账号
    @Override
    public void onUpdateAuthor(BaseBean baseBean) {

    }

    //获取个人中心信息
    @Override
    public void onGetMemberInfo(final UserInfo userInfo) {
//        this.userInfo = userInfo;
//        if(userInfo != null && (
//                (!StringUtils.isNullOrEmpty(userInfo.getMrState()) && userInfo.getMrState().equals("2")) ||
//                        (!StringUtils.isNullOrEmpty(userInfo.getMtState()) && userInfo.getMtState().equals("2")) ||
//                        (!StringUtils.isNullOrEmpty(userInfo.getLingState()) && userInfo.getLingState().equals("2")))){
//            //已激活名人/媒体/达人身份
//            CommonDialogUtil.showIdentityOpenDialog(mContext, new ConsultationFragment.ConsultationFragmentOpenDialogClickListener() {
//                @Override
//                public void agreementClick() {
//                    Intent intent = new Intent(mContext, WebViewActivity.class);
//                    intent.putExtra(WebViewActivity.URL_KEY, Constants.ACCOUNT_NUMBER_AGREEMENT);
//                    intent.putExtra(WebViewActivity.TITLE, "");
//                    mContext.startActivity(intent);
//                }
//
//                @Override
//                public void confirmClick() {
//                    //确认开通
//                    getPresenter().updateUserIsAuthor(App.getId());
//                }
//            });
//        }else{
//            //去激活身份
//            CommonDialogUtil.showIdentityActivateDialog(mContext, new ConsultationFragment.ConsultationFragmentActivateDialogClickListener() {
//
//                @Override
//                public void goActivateClick() {
//                    if(userInfo != null){
//                        IdentityActivity.start(mContext, userInfo);
//                    }
//
//                }
//            });
//        }

    }

    @Override
    public void onClick(View view) {

    }

}
