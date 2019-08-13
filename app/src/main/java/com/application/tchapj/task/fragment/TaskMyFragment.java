package com.application.tchapj.task.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.login.activity.LoginMainActivity;
import com.application.tchapj.my.activity.DarenActivity;
import com.application.tchapj.task.activity.ReleaseTaskImgTextActivity;
import com.application.tchapj.task.activity.TaskDyMainActivity;
import com.application.tchapj.task.activity.TaskPyqActivity;
import com.application.tchapj.task.activity.TaskQtActivity;
import com.application.tchapj.task.activity.TaskWbActivity;
import com.application.tchapj.task.activity.TaskWsMainActivity;
import com.application.tchapj.task.adapter.MyFaTaskSquareAdapter;
import com.application.tchapj.task.adapter.MyTaskSquareAdapter;
import com.application.tchapj.task.bean.MyTaskSquareModel;
import com.application.tchapj.task.presenter.MyTaskSquarePresenter;
import com.application.tchapj.task.view.MyTaskSquareView;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.KV;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.application.tchapj.DataManager.getDataManager;

// 任务tab-我的任务
public class TaskMyFragment extends BaseMvpFragment<MyTaskSquareView, MyTaskSquarePresenter> implements MyTaskSquareView, View.OnClickListener {

    @BindView(R.id.top_rg_a)
    RadioButton top_rg_a;
    @BindView(R.id.top_rg_b)
    RadioButton top_rg_b;

    @BindView(R.id.task_square_ll_myfa)
    LinearLayout task_square_ll_myfa;
    @BindView(R.id.task_square_ll_my)
    LinearLayout task_square_ll_my;

    @BindView(R.id.task_square_rv_myfa)
    RecyclerView taskSquareRvMyfa;
    @BindView(R.id.task_square_rl_myfa)
    SmartRefreshLayout taskSquareRlMyfa;

    @BindView(R.id.task_square_rv_my)
    RecyclerView taskSquareRvMy;
    @BindView(R.id.task_square_rl_my)
    SmartRefreshLayout taskSquareRlMy;
    Unbinder unbinder;


    private int pageNum = 1;
    private int pageSize = 10;

    private MyFaTaskSquareAdapter fataskSquareAdapter;
    private MyTaskSquareAdapter taskSquareAdapter;

    private List<MyTaskSquareModel.DataBean.TasksBean> TaskSquareInfo = new ArrayList<>();

    private KV kv;                 // 保存缓存数据的对象
    private String memberId = "";
    private Context mContext;
    private Dialog publishTaskDialog;

    // 接收参数
    public static TaskMyFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        TaskMyFragment fragment = new TaskMyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // 构造方法
    public TaskMyFragment() {
        // Required empty public constructor
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_test;
    }

    @Override
    public void initUI() {

        mContext = getActivity();

        kv = new KV(getContext());             // 保存基础数据
        //memberId = kv.getString(Constants.USER_ID, "");
        memberId = App.getId();

        if (App.getId() == null) {
            dialogs();
        }


        fataskSquareAdapter = new MyFaTaskSquareAdapter(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        taskSquareRvMyfa.setLayoutManager(layoutManager);
        taskSquareRvMyfa.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        taskSquareRvMyfa.setAdapter(fataskSquareAdapter);

        taskSquareAdapter = new MyTaskSquareAdapter(getContext());

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setSmoothScrollbarEnabled(true);
        layoutManager2.setAutoMeasureEnabled(true);

        taskSquareRvMy.setLayoutManager(layoutManager2);
        taskSquareRvMy.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        taskSquareRvMy.setAdapter(taskSquareAdapter);

        initListener();

    }

    private void initListener() {

        top_rg_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().getFaTaskSquare(memberId, pageNum + "", pageSize + "");
                task_square_ll_myfa.setVisibility(View.VISIBLE);
                task_square_ll_my.setVisibility(View.GONE);
            }
        });

        top_rg_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().getTaskSquare(memberId, pageNum + "", pageSize + "");
                task_square_ll_myfa.setVisibility(View.GONE);
                task_square_ll_my.setVisibility(View.VISIBLE);
            }
        });


        taskSquareRlMyfa.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                if (TaskSquareInfo != null) {
                    TaskSquareInfo.clear();
                }

                getPresenter().getFaTaskSquare(memberId, pageNum + "", pageSize + "");
            }
        });

        taskSquareRlMyfa.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getPresenter().getFaTaskSquare(memberId, pageNum + "", pageSize + "");
            }
        });


        taskSquareRlMy.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                if (TaskSquareInfo != null) {
                    TaskSquareInfo.clear();
                }

                getPresenter().getTaskSquare(memberId, pageNum + "", pageSize + "");
            }
        });

        taskSquareRlMy.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getPresenter().getTaskSquare(memberId, pageNum + "", pageSize + "");
            }
        });


    }

    @Override
    public void initData() {

        getPresenter().getFaTaskSquare(memberId, pageNum + "", pageSize + "");
    }

    @Override
    public MyTaskSquarePresenter createPresenter() {
        return new MyTaskSquarePresenter(getApp());
    }

    @Override
    public void onGetMyFaTaskSquareModels(MyTaskSquareModel mytaskSquareModel) {

        if ("000".equals(mytaskSquareModel.getCode())) {

            if (pageNum == 1) {
                TaskSquareInfo = mytaskSquareModel.getData().getTasks();

            } else {
                TaskSquareInfo.addAll(mytaskSquareModel.getData().getTasks());

            }

            fataskSquareAdapter.setData(TaskSquareInfo);


            if (taskSquareRlMyfa.isEnableRefresh()) {
                taskSquareRlMyfa.finishRefresh();
            }
            if (taskSquareRlMyfa.isEnableLoadMore()) {
                taskSquareRlMyfa.finishLoadMore();
            }


        }
    }

    @Override
    public void onGetMyTaskSquareModels(MyTaskSquareModel mytaskSquareModel) {

        if ("000".equals(mytaskSquareModel.getCode())) {

            if (pageNum == 1) {
                TaskSquareInfo = mytaskSquareModel.getData().getTasks();

            } else {
                TaskSquareInfo.addAll(mytaskSquareModel.getData().getTasks());

            }

            taskSquareAdapter.setData(TaskSquareInfo);

        }

        if (taskSquareRlMy.isEnableRefresh()) {
            taskSquareRlMy.finishRefresh();
        }
        if (taskSquareRlMy.isEnableLoadMore()) {
            taskSquareRlMy.finishLoadMore();
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

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("请先进行登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = new Intent(getContext(), LoginMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


    private void dialogs2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("请激活达人资料");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = new Intent(getContext(), DarenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.add_task_button)
    public void onViewClicked() {

        String id = getDataManager().quickGetMetaData(R.string.id, String.class);
        if ("".equals(id)) {
            CommonDialogUtil.showLoginDialog(getActivity());
        } else {

            if ("2".equals(getDataManager().quickGetMetaData(R.string.faState, String.class))) {
                showPublishDialog();

            } else {

                CommonDialogUtil.identityDialog(getActivity(), "请先申请广告主身份");

            }

        }

    }

    private void showPublishDialog() {
        publishTaskDialog = new Dialog(mContext, R.style.DialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_publish_task, null);
        publishTaskDialog.setContentView(view);
        view.findViewById(R.id.dialog_consultation_ll_other).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_dy).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_circle_friends).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_wb).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_ws).setOnClickListener(this);

        view.findViewById(R.id.dialog_consultation_fl_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishTaskDialog.dismiss();
            }
        });

        Window dialogWindow = publishTaskDialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);

        layoutParams.width = wm.getDefaultDisplay().getWidth();
        layoutParams.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(layoutParams);
        publishTaskDialog.show();

    }

    @Override
    public void onClick(View view) {
        publishTaskDialog.dismiss();
        Intent intent;
        switch (view.getId()) {
            case R.id.dialog_consultation_ll_other:
                Toast.makeText(getContext(), "其他", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), TaskQtActivity.class);
                startActivity(intent);
                break;
            case R.id.dialog_consultation_ll_dy:
                Toast.makeText(getContext(), "抖音", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), TaskDyMainActivity.class);
                startActivity(intent);
                break;
            case R.id.dialog_consultation_ll_circle_friends:
                Toast.makeText(getContext(), "图文", Toast.LENGTH_SHORT).show();
                ReleaseTaskImgTextActivity.start(getActivity());
                break;
            case R.id.dialog_consultation_ll_wb:
                Toast.makeText(getContext(), "微博", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), TaskWbActivity.class);
                startActivity(intent);
                break;
            case R.id.dialog_consultation_ll_ws:
                Toast.makeText(getContext(), "微视", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), TaskWsMainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
