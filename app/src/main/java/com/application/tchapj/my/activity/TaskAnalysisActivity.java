package com.application.tchapj.my.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpActivity;
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
import com.application.tchapj.task.fragment.TaskSquareFragment;
import com.application.tchapj.task.presenter.TaskSquarePresenter;
import com.application.tchapj.task.view.ITaskSquareView;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;

public class TaskAnalysisActivity extends BaseMvpActivity<ITaskSquareView, TaskSquarePresenter> {

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

    private TaskSquareFragment taskSquareFragment;

    private List<ConsultationTopModel.PersonSelectModelResult.NewStypeSelect> newStypeSelects = new ArrayList<>();

    private Context mContext;


    @Override
    public int getRootViewId() {
        return R.layout.activity_content_manager;
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("任务分析");
    }



    @Override
    public TaskSquarePresenter createPresenter() {

        return new TaskSquarePresenter(getApp());
    }

    @Override
    public void initUI() {
        mContext = this;
        listTitle = new ArrayList<>();
        listTitle2 = new ArrayList<>();
        listTemp = new ArrayList<>();
        listTempId = new ArrayList<>();
        fragments = new ArrayList<>();

        taskSquareFragment = new TaskSquareFragment();

//        // 添加的
//        mSubscription = RxBus.getDefault().toObserverable(ChangeAnswerEvent.class)
//                .subscribe(new Action1<ChangeAnswerEvent>() {
//                    @Override
//                    public void call(ChangeAnswerEvent changeAnswerEvent) {
//
//                        if (changeAnswerEvent.getTitles().size() != 0) {
//
//                            listTemp.clear();
//                            listTempId.clear();
//                            fragments.clear();
//                            listTemp.addAll(changeAnswerEvent.getTitles());
//                            listTempId.addAll(changeAnswerEvent.getTitlesId());
//                            for (int i = 0; i < listTemp.size(); i++) {
//                                fragments.add(taskSquareFragment.newInstance(TaskSquareFragment.FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal()));
//                            }
//                            toSetList(listTitle, listTemp, false);
//                            toSetList(listTitleId, listTempId, false);
//
//                            if (viewPagerFragmentAdapter != null) {
//                                viewPagerFragmentAdapter.setListTitle(listTitle);
//                                viewPagerFragmentAdapter.setListData(fragments);
//                                viewPagerFragmentAdapter.notifyDataSetChanged();
//                            }
//
//                            viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, listTitle);
//
//                            viewpager.setAdapter(viewPagerFragmentAdapter);
//
//                            tabs.setupWithViewPager(viewpager);
//                        }
//
//
//                    }
//
//                });


        //初始化数据
        fragments.clear();
        listTemp.clear();
        listTempId.clear();

        listTemp.add("全部");
        fragments.add(taskSquareFragment.newInstance(TaskSquareFragment.FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal(), ""));
        listTemp.add("进行中");
        fragments.add(taskSquareFragment.newInstance(TaskSquareFragment.FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal(), "2"));
        listTemp.add("已结束");
        fragments.add(taskSquareFragment.newInstance(TaskSquareFragment.FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal(), "3"));
        listTemp.add("审核中");
        fragments.add(taskSquareFragment.newInstance(TaskSquareFragment.FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal(), "1"));


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

}
