package com.application.tchapj.task.activity;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.PromotionPayResultBean;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.task.bean.FaTaskBean;
import com.application.tchapj.task.bean.FaTaskSuccessBean;
import com.application.tchapj.task.bean.FaTaskSuccessafterBean;
import com.application.tchapj.task.presenter.FaTaskPresenter;
import com.application.tchapj.task.view.IFaTaskView;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;

// 发其他任务
public class TaskQtActivity extends BaseMvpActivity<IFaTaskView, FaTaskPresenter>
        implements IFaTaskView {

    @BindView(R.id.fa_qt_et)
    EditText fa_qt_et;

    @BindView(R.id.fa_qt_bt)
    Button fa_qt_bt;

    private String memberId;    // 用户id
    private String taskType;    // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
    private String name;        // 活动标题
    private String require;     // 任务要求/商家需求
    private String imgUrl;      // 活动缩略图
    private String startTime;   // 活动开始时间
    private String endTime;     // 活动结束时间
//    private String circleTypeId;// 投放圈子
    private String unitPrice;   // 单价
    private String taskImgUrl;  // 活动图片url或视频url
    private String taskNum;     // 投放数量
    private String taskGuidance;// 活动引导
    private String phonenumber; // 手机号码
    private String copywriting; // 朋友圈文案
    private String Fans;        // 要求粉丝数量


    private FaTaskBean.FaTaskBeanResult faTaskBeanResult = new FaTaskBean.FaTaskBeanResult();
    private String taskId;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("发布其他任务");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_fa_qttask;
    }

    @Override
    public void initUI() {

        fa_qt_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                memberId = App.getId();   // 用户id
                taskType = "4";      // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
                name = "";           // 活动标题
                require = fa_qt_et.getText().toString();  // 任务要求/商家需求
                startTime ="";       // 活动开始时间
                endTime = "";          // 活动结束时间
                unitPrice = "";        // 单价
                taskNum = "";          // 投放数量
                taskGuidance = "";     // 活动引导
                phonenumber = "";      // 手机号码
                copywriting = "";      // 朋友圈文案
                Fans = "";             // 要求粉丝数量


                if (require.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入商家需求！", Toast.LENGTH_LONG).show();
                    return;
                }
                // 上传其他任务
                getPresenter().getFaTaskBeanResult(memberId,taskType,name,require,imgUrl,startTime
                        ,endTime,unitPrice,taskImgUrl
                        ,taskNum,taskGuidance,phonenumber,copywriting,Fans,"","");


            }
        });


    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public FaTaskPresenter createPresenter() {
        return new FaTaskPresenter(getApp());
    }

    @Override // 发任务
    public void onGetFaTaskBeanModels(FaTaskBean faTaskBean) {

        if ("000".equals(faTaskBean.getCode())) {
            faTaskBeanResult = faTaskBean.getData();
            taskId= faTaskBean.getData().getTaskId();

            Toast.makeText(TaskQtActivity.this, "发送成功！", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onGetAlipayOrderInfoSuccessBeanModels(FaTaskSuccessBean faTaskSuccessBean) {

    }

   /* @Override // 预付订单
    public void onGetFaTaskSuccessBeanModels(FaTaskSuccessBean faTaskSuccessBean) {

    }*/

    @Override // 任务付款
    public void onGetFaTaskSuccessafterBeanModels(FaTaskSuccessafterBean faTaskSuccessafterBean) {

    }

    @Override
    public void onGetBrokerage(MoneyInfoBean moneyInfoBean) {

    }

    @Override
    public void onGetPromotionPaySuccess(PromotionPayResultBean baseBean) {

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

