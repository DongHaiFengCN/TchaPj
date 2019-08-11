package com.application.tchapj.my.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.activity.BindingPhoneActivity;
import com.application.tchapj.login.presenter.BindingPhonePresenter;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.my.presenter.QiniuPresenter;
import com.application.tchapj.my.view.IQiniuView;
import com.application.tchapj.my.wigiter.CornerLabelView;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;


// 身份管理
public class IdentityActivity extends BaseMvpActivity<IQiniuView, QiniuPresenter>
        implements IQiniuView{

    @BindView(R.id.item_daren_rl)
    RelativeLayout item_daren_rl;
    @BindView(R.id.daren_clv)
    CornerLabelView daren_clv;
    @BindView(R.id.daren_dy_iv)
    ImageView daren_dy_iv;
    @BindView(R.id.daren_py_iv)
    ImageView daren_py_iv;
    @BindView(R.id.daren_wb_iv)
    ImageView daren_wb_iv;
    @BindView(R.id.daren_ws_iv)
    ImageView daren_ws_iv;
    @BindView(R.id.daren_other_iv)
    ImageView daren_other_iv;

    @BindView(R.id.item_guangao_rl)
    RelativeLayout item_guangao_rl;
    @BindView(R.id.guangao_clv)
    CornerLabelView guangao_clv;

    @BindView(R.id.item_mingren_rl)
    RelativeLayout item_mingren_rl;
    @BindView(R.id.mingren_clv)
    CornerLabelView mingren_clv;

    @BindView(R.id.item_meiti_rl)
    RelativeLayout item_meiti_rl;
    @BindView(R.id.meiti_clv)
    CornerLabelView meiti_clv;

    private String darenState ="0";
    private String guanggaozhuState ="0";
    private String mingrenState ="0";
    private String meitiState ="0";

    private String dyState ="0";
    private String pyqState ="0";
    private String wbState ="0";
    private String wsState = "0";

    private QiniuBean.QiniuBeanResult qiniuBeans = new QiniuBean.QiniuBeanResult();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    /*    Intent intent = getIntent();

        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("darenState"))){
            darenState = intent.getStringExtra("darenState");
        }
        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("guanggaozhuState"))){
            guanggaozhuState = intent.getStringExtra("guanggaozhuState");
        }
        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("mingrenState"))){
            mingrenState = intent.getStringExtra("mingrenState");
        }
        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("meitiState"))){
            meitiState = intent.getStringExtra("meitiState");
        }

        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("dyState"))){
            dyState = intent.getStringExtra("dyState");
        }
        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("pyqState"))){
            pyqState = intent.getStringExtra("pyqState");
        }
        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("wbState"))){
            wbState = intent.getStringExtra("wbState");
        }
        if(!StringUtils.isNullOrEmpty(intent.getStringExtra("wsState"))){
            wsState = intent.getStringExtra("wsState");
        }*/

        toolbarHelper.setTitle("身份管理");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_identity;
    }

    @Override
    public void initUI() {

        getPresenter().onGetQiniuResult();

        item_meiti_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(meitiState.equals("0")||meitiState.equals("3")){
                    if(mingrenState.equals("1") || mingrenState.equals("2")){
                        ToastUtil.show(IdentityActivity.this, "名人身份和媒体身份只能拥有一个");
                    }else{
                        Intent intent = new Intent(IdentityActivity.this, MeitiActivity.class);
                        startActivity(intent);
                    }
                }else {
                    dialogs(meitiState);
                }
            }
        });
        /*item_meiti_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(IdentityActivity.this,"媒体",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IdentityActivity.this, MeitiActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getUserModelResult(App.getId());
    }

    private void initView() {

        if(dyState.equals("1")){
            daren_dy_iv.setImageResource(R.mipmap.ic_dy_select);
        }else {
            daren_dy_iv.setImageResource(R.mipmap.ic_dy_select_normal);
        }

        if(pyqState.equals("1")){
            daren_py_iv.setImageResource(R.mipmap.ic_py_select);
        }else {
            daren_py_iv.setImageResource(R.mipmap.ic_py_select_normal);
        }

        if(wbState.equals("1")){
            daren_wb_iv.setImageResource(R.mipmap.ic_wb_select);
        }else {
            daren_wb_iv.setImageResource(R.mipmap.ic_wb_select_normal);
        }

        if(wsState.equals("1")){
            daren_ws_iv.setImageResource(R.mipmap.ic_ws_select);
        }else {
            daren_ws_iv.setImageResource(R.mipmap.ic_ws_select_normal);
        }

        if(wsState.equals("1")){
            daren_other_iv.setImageResource(R.mipmap.ic_other_select);
        }else {
            daren_other_iv.setImageResource(R.mipmap.ic_other_select_normal);
        }


        int paddingTop = 16;
        //0-未申请   1-正在审核中  2-已通过  3-未通过 lingState 达人
        daren_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if(darenState.equals("0")){


            daren_clv.setFillColor(0xffa9a9a9); // 背景色
            daren_clv.right();                  // 位置
            daren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            daren_clv.setPaddingCenter(2);        // 填充标签的边距
            daren_clv.setPaddingBottom(-4);        // 填充标签的边距
            daren_clv.setText2("未激活");
            daren_clv.setText2Color(0xffffffff);

        }else if(darenState.equals("1")){
            daren_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            daren_clv.right();                  // 位置
            daren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            daren_clv.setPaddingCenter(2);        // 填充标签的边距
            daren_clv.setPaddingBottom(-4);        // 填充标签的边距
            daren_clv.setText2("审核中");
            daren_clv.setText2Color(0xffffffff);
        }else if(darenState.equals("2")){
            daren_clv.setFillColor(0xffff6347); // 背景色
            daren_clv.right();                  // 位置
            daren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            daren_clv.setPaddingCenter(2);        // 填充标签的边距
            daren_clv.setPaddingBottom(-4);        // 填充标签的边距
            daren_clv.setText2("已激活");
            daren_clv.setText2Color(0xffffffff);
        }else if(darenState.equals("3")){
            daren_clv.setFillColor(0xffff4500); // 背景色
            daren_clv.right();                  // 位置
            daren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            daren_clv.setPaddingCenter(2);        // 填充标签的边距
            daren_clv.setPaddingBottom(-4);        // 填充标签的边距
            daren_clv.setText2("激活失败");
            daren_clv.setText2Color(0xffffffff);
        }

        /*item_daren_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(darenState.equals("0")||darenState.equals("4")){
                    //Toast.makeText(IdentityActivity.this,"达人身份激活",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(IdentityActivity.this, DarenActivity.class);
                    startActivity(intent);
                }else {
                    dialogs(darenState);
                }
            }
        });*/
        item_daren_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferencesUtils.getInstance().getUserInfo() != null && !TextUtils.isEmpty(SharedPreferencesUtils.getInstance().getUserInfo().getTelephone())){
                    Intent intent = new Intent(IdentityActivity.this, DarenActivity.class);
                    startActivity(intent);
                }else{
                    showToast("申请达人需绑定手机号");
                    BindingPhoneActivity.start(IdentityActivity.this);
                }


            }
        });


        guangao_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if(guanggaozhuState.equals("0")){

            guangao_clv.setFillColor(0xffa9a9a9); // 背景色
            guangao_clv.right();                  // 位置
            guangao_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            guangao_clv.setPaddingCenter(2);        // 填充标签的边距
            guangao_clv.setPaddingBottom(-4);        // 填充标签的边距
            guangao_clv.setText2("未激活");
            guangao_clv.setText2Color(0xffffffff);

        }else if(guanggaozhuState.equals("1")){
            guangao_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            guangao_clv.right();                  // 位置
            guangao_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            guangao_clv.setPaddingCenter(2);        // 填充标签的边距
            guangao_clv.setPaddingBottom(-4);        // 填充标签的边距
            guangao_clv.setText2("审核中");
            guangao_clv.setText2Color(0xffffffff);
        }else if(guanggaozhuState.equals("2")){
            guangao_clv.setFillColor(0xffff6347); // 背景色
            guangao_clv.right();                  // 位置
            guangao_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            guangao_clv.setPaddingCenter(2);        // 填充标签的边距
            guangao_clv.setPaddingBottom(-4);        // 填充标签的边距
            guangao_clv.setText2("已激活");
            guangao_clv.setText2Color(0xffffffff);

        }else if(guanggaozhuState.equals("3")){
            guangao_clv.setFillColor(0xffff4500); // 背景色
            guangao_clv.right();                  // 位置
            guangao_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            guangao_clv.setPaddingCenter(2);        // 填充标签的边距
            guangao_clv.setPaddingBottom(-4);        // 填充标签的边距
            guangao_clv.setText2("激活失败");
            guangao_clv.setText2Color(0xffffffff);
        }

        item_guangao_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guanggaozhuState.equals("0")||guanggaozhuState.equals("3")){
                    Intent intent = new Intent(IdentityActivity.this, GuanggaoActivity.class);
                    startActivity(intent);
                }else {
                    dialogs(guanggaozhuState);
                }
            }
        });

        /*item_guangao_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IdentityActivity.this, GuanggaoActivity.class);
                startActivity(intent);
            }
        });*/



        mingren_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if(mingrenState.equals("0")){

            mingren_clv.setFillColor(0xffa9a9a9); // 背景色
            mingren_clv.right();                  // 位置
            mingren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            mingren_clv.setPaddingCenter(2);        // 填充标签的边距
            mingren_clv.setPaddingBottom(-4);        // 填充标签的边距
            mingren_clv.setText2("未激活");
            mingren_clv.setText2Color(0xffffffff);

        }else if(mingrenState.equals("1")){
            mingren_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            mingren_clv.right();                  // 位置
            mingren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            mingren_clv.setPaddingCenter(2);        // 填充标签的边距
            mingren_clv.setPaddingBottom(-4);        // 填充标签的边距
            mingren_clv.setText2("审核中");
            mingren_clv.setText2Color(0xffffffff);
        }else if(mingrenState.equals("2")){
            mingren_clv.setFillColor(0xffff6347); // 背景色
            mingren_clv.right();                  // 位置
            mingren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            mingren_clv.setPaddingCenter(2);        // 填充标签的边距
            mingren_clv.setPaddingBottom(-4);        // 填充标签的边距
            mingren_clv.setText2("已激活");
            mingren_clv.setText2Color(0xffffffff);
        }else if(mingrenState.equals("3")){
            mingren_clv.setFillColor(0xffff4500); // 背景色
            mingren_clv.right();                  // 位置
            mingren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            mingren_clv.setPaddingCenter(2);        // 填充标签的边距
            mingren_clv.setPaddingBottom(-4);        // 填充标签的边距
            mingren_clv.setText2("激活失败");
            mingren_clv.setText2Color(0xffffffff);
        }

        item_mingren_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mingrenState.equals("0")||mingrenState.equals("3")){
                    if(meitiState.equals("1") || meitiState.equals("2")){
                        ToastUtil.show(IdentityActivity.this, "名人身份和媒体身份只能拥有一个");
                    }else{
                        Intent intent = new Intent(IdentityActivity.this, MingrenActivity.class);
                        startActivity(intent);
                    }

                }else {
                    dialogs(mingrenState);
                }
            }
        });
        /*item_mingren_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(IdentityActivity.this,"名人",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IdentityActivity.this, MingrenActivity.class);
                startActivity(intent);
            }
        });*/


        meiti_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if(meitiState.equals("0")){

            meiti_clv.setFillColor(0xffa9a9a9); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("未激活");
            meiti_clv.setText2Color(0xffffffff);

        }else if(meitiState.equals("1")){
            meiti_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("审核中");
            meiti_clv.setText2Color(0xffffffff);
        }else if(meitiState.equals("2")){
            meiti_clv.setFillColor(0xffff6347); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("已激活");
            meiti_clv.setText2Color(0xffffffff);
        }else if(meitiState.equals("3")){
            meiti_clv.setFillColor(0xffff4500); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("激活失败");
            meiti_clv.setText2Color(0xffffffff);
        }
    }

    @NonNull
    @Override
    public QiniuPresenter createPresenter() {
        return new QiniuPresenter(getApp());
    }

    @Override
    public void onGetQiniuBeanResult(QiniuBean qiniuBean) {

        if ("000".equals(qiniuBean.getCode())) {
            qiniuBeans = qiniuBean.getData();
            App.QiniuToken =qiniuBeans.getUploadToken();
        }
    }

    @Override
    public void onGetUserModelResult(UserModel userModelBean) {
        if (userModelBean != null && userModelBean.getCode().equals("000") && userModelBean.getData() != null) {
            UserInfo userInfo = userModelBean.getData();


            if (!StringUtils.isNullOrEmpty(userInfo.getLingState())) {
                darenState = userInfo.getLingState();
            }
            if (!StringUtils.isNullOrEmpty(userInfo.getFaState())) {
                guanggaozhuState = userInfo.getFaState();
            }
            if (!StringUtils.isNullOrEmpty(userInfo.getMrState())) {
                mingrenState = userInfo.getMrState();
            }
            if (!StringUtils.isNullOrEmpty(userInfo.getMtState())) {
                meitiState = userInfo.getMtState();
            }

            if (!StringUtils.isNullOrEmpty(userInfo.getDyState())) {
                dyState = userInfo.getDyState();
            }
            if (!StringUtils.isNullOrEmpty(userInfo.getPyqState())) {
                pyqState = userInfo.getPyqState();
            }
            if (!StringUtils.isNullOrEmpty(userInfo.getWbState())) {
                wbState = userInfo.getWbState();
            }
            if (!StringUtils.isNullOrEmpty(userInfo.getWsState())) {
                wsState = userInfo.getWsState();
            }

            initView();
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

    //媒体、广告主、名人
    private void dialogs(String state) {

        AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);


        //0：未激活  1：审核中  2：已激活 3：激活失败
        if(state.equals("0")){
            builder.setMessage("未激活");
        }else if(state.equals("1")){
            builder.setMessage("审核中");
        }else if(state.equals("2")){
            builder.setMessage("已激活");
        }else if(state.equals("3")){
            builder.setMessage("激活失败");
        }

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public static void start(Context context, UserInfo user){
        if(!CommonUtils.isLogin(context)){
            return;
        }

        Intent intent = new Intent(context, IdentityActivity.class);

        if(user.getLingState()==null){
            intent.putExtra("darenState", "0");
        }else {
            intent.putExtra("darenState", user.getLingState());
        }

        if(user.getFaState()==null){
            intent.putExtra("guanggaozhuState", "0");
        }else {
            intent.putExtra("guanggaozhuState", user.getFaState());
            Log.e("达人状态", "url===" + user.getId());
        }

        if(user.getMrState()==null){
            intent.putExtra("mingrenState", "0");
        }else {
            intent.putExtra("mingrenState", user.getMrState());
        }

        if(user.getMtState()==null){
            intent.putExtra("meitiState", "0");
        }else {
            intent.putExtra("meitiState", user.getMtState());
        }

        if(user.getDyState()==null){
            intent.putExtra("dyState", "0");
        }else {
            intent.putExtra("dyState", user.getDyState());
        }

        if(user.getPyqState()==null){
            intent.putExtra("pyqState", "0");
        }else {
            intent.putExtra("pyqState", user.getPyqState());
        }

        if(user.getWbState()==null){
            intent.putExtra("wbState", "0");
        }else {
            intent.putExtra("wbState", user.getWbState());
        }

        if(user.getWsState()==null){
            intent.putExtra("wsState", "0");
        }else {
            intent.putExtra("wsState", user.getWsState());
        }

        context.startActivity(intent);
    }

}
