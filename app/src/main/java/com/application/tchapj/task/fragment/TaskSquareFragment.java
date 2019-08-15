package com.application.tchapj.task.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpFragment;

import com.application.tchapj.my.activity.DarenActivity;
import com.application.tchapj.my.activity.IdentityActivity;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.task.activity.CollarTaskCircleFriendLinkActivity;
import com.application.tchapj.task.activity.FriendReleaseActivity;
import com.application.tchapj.task.activity.LeadTaskWechatActivity;
import com.application.tchapj.task.activity.TaskReviewfinishActivity;
import com.application.tchapj.task.activity.TaskReviewprogressActivity;
import com.application.tchapj.task.activity.TaskSquareDyGpActivity;
import com.application.tchapj.task.activity.TaskSquareDyYcActivity;
import com.application.tchapj.task.activity.TaskSquareDyYcHzActivity;
import com.application.tchapj.task.activity.TaskSquareWsGpActivity;
import com.application.tchapj.task.activity.TaskSquareWsYcActivity;
import com.application.tchapj.task.activity.UploadAuditActivity;
import com.application.tchapj.task.adapter.TaskSquareAdapter;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskModel;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.bean.TaskSquareModel;
import com.application.tchapj.task.presenter.TaskSquarePresenter;
import com.application.tchapj.task.view.ITaskSquareView;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


// 任务tab-任务广场
public class TaskSquareFragment extends BaseMvpFragment<ITaskSquareView, TaskSquarePresenter> implements ITaskSquareView {

    @BindView(R.id.task_square_rl)
    SmartRefreshLayout task_square_rl;
    @BindView(R.id.task_square_rv)
    RecyclerView task_square_rv;

    private String memberId = "";
    private int pageNum = 1;
    private int pageSize = 10;

    private TaskSquareAdapter taskSquareAdapter;

    private List<TaskModel> TaskSquareInfo = new ArrayList<>();

    private int fromType = FROM_TYPE.TASK_FRAGMENT.ordinal();
    private String status;//任务分析 全部/进行中/已结束/审核中

    public enum FROM_TYPE {
        TASK_ANALYSIS_ACTIVITY,//任务分析
        TASK_FRAGMENT//任务广场
    }

    // 接收参数
    public static TaskSquareFragment newInstance(int fromType, String status) {
        Bundle args = new Bundle();
        args.putInt("from_type", fromType);
        args.putString("status", status);
        TaskSquareFragment fragment = new TaskSquareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fromType = getArguments().getInt("from_type");//默认为任务广场
            status = getArguments().getString("status");
        }

    }

    @Override
    public int getRootViewId() {

        return R.layout.fragment_square_test;
    }

    @Override
    public void initUI() {

        taskSquareAdapter = new TaskSquareAdapter(getContext());
        task_square_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        task_square_rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        task_square_rv.setAdapter(taskSquareAdapter);

        initListener();

    }

    private void initListener() {

        task_square_rl.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                if (TaskSquareInfo != null) {
                    TaskSquareInfo.clear();
                }
                getListData();

            }
        });

        task_square_rl.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getListData();
            }
        });

        taskSquareAdapter.setOnItemClickLitener(new TaskSquareAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (StringUtils.isNullOrEmpty(App.getId())) {
                    CommonDialogUtil.showLoginDialog(getActivity());
                } else {
                    if (fromType == FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal()) {
                        //任务分析
                        //H5的任务详情页
                        String url = Constants.MY_RELEASE_TASK_DETAIL_URL + TaskSquareInfo.get(position).getId();
                        WebViewActivity.start(getActivity(),
                                "All", url, false, false);
                    } else {

                        Log.e("DOAING", "-------------------------");
                        presenter.getFriendReleaseData(App.getId(), TaskSquareInfo.get(position).getId());
                    }

                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    private void getListData() {
        if (fromType == FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal()) {
            getPresenter().getTaskAnalysis(App.getId(), status, pageNum + "", pageSize + "");
        } else {
            getPresenter().getTaskSquare(App.getId(), pageNum + "", pageSize + "");
        }

    }


    @Override
    public void initData() {

        getListData();

    }

    @Override
    public TaskSquarePresenter createPresenter() {
        return new TaskSquarePresenter(getApp());
    }

    @Override
    public void onGetQiniuBeanResult(QiniuBean qiniuBean) {

    }

    @Override
    public void onGetTaskSquareModels(TaskSquareModel taskSquareModel) {

        if ("000".equals(taskSquareModel.getCode())) {

            if (pageNum == 1) {
                if (fromType == FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal()) {
                    TaskSquareInfo = taskSquareModel.getData().getTasks();
                } else {
                    TaskSquareInfo = taskSquareModel.getData().getlTasks();
                }

            } else {
                if (fromType == FROM_TYPE.TASK_ANALYSIS_ACTIVITY.ordinal()) {
                    TaskSquareInfo.addAll(taskSquareModel.getData().getTasks());
                } else {
                    TaskSquareInfo.addAll(taskSquareModel.getData().getlTasks());
                }

            }

            taskSquareAdapter.setData(TaskSquareInfo);


            if (task_square_rl.isEnableRefresh()) {
                task_square_rl.finishRefresh();
            }
            if (task_square_rl.isEnableLoadMore()) {
                task_square_rl.finishLoadMore();
            }
        }

    }

    @Override
    public void onGetFriendReleaseData(TaskSquareInfoModel friendReleaseBean) {


        Log.e("DOAING", friendReleaseBean.getCode());

        if (TextUtils.equals(friendReleaseBean.getCode(), "000")) {

            TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskBean = friendReleaseBean.getData().getTask();
            String taskType = taskBean.getTaskType();//0是朋友圈,1是微博，2是抖音合拍，3是抖音原创，4是其他 ，5是朋友圈转发链接 6微视合拍 7微视原创
            String taskstatus = friendReleaseBean.getData().getTaskstatus();//0:未领取 1已领取 2上传资料审核中 3通过 4未通过 Task_type=3时 1为已提交合作

            if (TextUtils.equals(taskstatus, "0")) {
                if (taskType.equals("0")) {
                    Intent intent = new Intent(getContext(), LeadTaskWechatActivity.class);
                    intent.putExtra("ID", taskBean.getId());

                    startActivity(intent);
                } else if (taskType.equals("1")) {
                    Intent intent = new Intent(getContext(), FriendReleaseActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);

                } else if (taskType.equals("5")) {
                    Intent intent = new Intent(getContext(), CollarTaskCircleFriendLinkActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);

                } else if (taskType.equals("2")) {

                    Intent intent = new Intent(getContext(), TaskSquareDyGpActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);
                } else if (taskType.equals("3")) {

                    Intent intent = new Intent(getContext(), TaskSquareDyYcActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);
                } else if (taskType.equals("6")) {
                    Intent intent = new Intent(getContext(), TaskSquareWsGpActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);
                } else if (taskType.equals("7")) {

                    Intent intent = new Intent(getContext(), TaskSquareWsYcActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);
                }

            } else if (TextUtils.equals(taskstatus, "1")) {
                if (taskType.equals("0")) {
                    Intent intent = new Intent(getContext(), LeadTaskWechatActivity.class);
                    intent.putExtra("ID", taskBean.getId());

                    startActivity(intent);
                } else if (taskType.equals("3") || taskType.equals("7")) {//3是抖音原创  7微视原创

                    Intent intent = new Intent(getContext(), TaskSquareDyYcHzActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    intent.putExtra("index", "1");
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(getContext(), UploadAuditActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);

//                    if(TextUtils.equals("0",status)){
//                        if (taskType.equals("0")||taskType.equals("1")){
//                            //0是朋友圈,1是微博，2是抖音合拍，3是抖音，4是其他
//
//                            Intent intent = new Intent(getContext(), FriendReleaseActivity.class);
//                            intent.putExtra("ID", taskBean.getId());
//                            Log.e("id",taskBean.getId());
//                            startActivity(intent);
//
//                        }else if(taskType.equals("2")){
//
//                            Intent intent = new Intent(getContext(), TaskSquareDyGpActivity.class);
//                            intent.putExtra("ID", taskBean.getId());
//                            Log.e("id",taskBean.getId());
//                            startActivity(intent);
//                        }else if(taskType.equals("3")){
//
//                            Intent intent = new Intent(getContext(), TaskSquareDyYcActivity.class);
//                            intent.putExtra("ID", taskBean.getId());
//                            Log.e("id",taskBean.getId());
//                            startActivity(intent);
//                        }
//
//
//                    }else if (TextUtils.equals("1",status)){
//
//                        Intent intent = new Intent(getContext(), UploadAuditActivity.class);
//                        intent.putExtra("ID", taskBean.getId());
//                        Log.e("id",taskBean.getId());
//                        startActivity(intent);
//
//                    }

                }

            } else if (TextUtils.equals(taskstatus, "2")) {

                if (taskType.equals("0")) {
                    Intent intent = new Intent(getContext(), LeadTaskWechatActivity.class);
                    intent.putExtra("ID", taskBean.getId());

                    startActivity(intent);
                } else if (taskType.equals("3") || taskType.equals("7")) {
                    Intent intent = new Intent(getContext(), TaskSquareDyYcHzActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    intent.putExtra("index", "3");
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getContext(), TaskReviewprogressActivity.class);
                    startActivity(intent);
                }

            } else if (TextUtils.equals(taskstatus, "3")) {
                //通过
                if (taskType.equals("0")) {
                    Intent intent = new Intent(getContext(), LeadTaskWechatActivity.class);
                    intent.putExtra("ID", taskBean.getId());

                    startActivity(intent);
                } else if (taskType.equals("5")) {
                    //朋友圈转发链接
                    Intent intent = new Intent(getContext(), CollarTaskCircleFriendLinkActivity.class);
                    intent.putExtra("ID", taskBean.getId());
                    Log.e("id", taskBean.getId());
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getContext(), TaskReviewfinishActivity.class);
                    intent.putExtra("index", "1");
                    startActivity(intent);
                }

            } else if (TextUtils.equals(taskstatus, "4")) {
                //未通过
                if (taskType.equals("0")) {
                    Intent intent = new Intent(getContext(), LeadTaskWechatActivity.class);
                    intent.putExtra("ID", taskBean.getId());

                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getContext(), TaskReviewfinishActivity.class);
                    intent.putExtra("index", "2");
                    startActivity(intent);
                }


            }
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

    }

}
