package com.application.tchapj.my.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.DataManager;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;

import com.application.tchapj.login.presenter.BindingPhonePresenter;

import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.my.presenter.QiniuPresenter;
import com.application.tchapj.my.view.IQiniuView;
import com.application.tchapj.my.wigiter.CornerLabelView;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.application.tchapj.DataManager.getDataManager;

/**
 * @author 董海峰
 * 身份管理业务界面
 */
public class IdentityActivity extends AppCompatActivity {

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

    @BindView(R.id.smrz_clv)
    CornerLabelView smrz_clv;

    @BindView(R.id.item_smrz_rl)
    RelativeLayout smrz_rl;


    private String darenState = "0";
    private String guanggaozhuState = "0";
    private String mingrenState = "0";
    private String meitiState = "0";
    private String smState = "";

    private String dyState = "0";
    private String pyqState = "0";
    private String wbState = "0";
    private String wsState = "0";
    private int paddingTop = 16;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_identity);
        ButterKnife.bind(this);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        smState = getDataManager().quickGetMetaData(R.string.identity, String.class);
        smrz_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if ("".equals(smState)) {
            smrz_clv.setFillColor(0xffa9a9a9); // 背景色
            smrz_clv.right();                  // 位置
            smrz_clv.triangle(true);           // 是否设置填充角标
            smrz_clv.setPaddingCenter(2);        // 填充标签的边距
            smrz_clv.setPaddingBottom(-4);        // 填充标签的边距
            smrz_clv.setText2("未认证");
            smrz_clv.setText2Color(0xffffffff);
        } else {
            smrz_clv.setFillColor(0xffff6347); // 背景色
            smrz_clv.right();                  // 位置
            smrz_clv.triangle(true);           // 是否设置填充角标
            smrz_clv.setPaddingCenter(2);        // 填充标签的边距
            smrz_clv.setPaddingBottom(-4);        // 填充标签的边距
            smrz_clv.setText2("已认证");
            smrz_clv.setText2Color(0xffffffff);
        }

        DataManager.getDataManager().disposeMember(new DataManager.UpDataListener() {
            @Override
            public void upData(boolean getDataSuccess) {

                darenState = getDataManager().quickGetMetaData(R.string.lingState, String.class);
                guanggaozhuState = getDataManager().quickGetMetaData(R.string.faState, String.class);
                smState = getDataManager().quickGetMetaData(R.string.identity, String.class);
                meitiState = getDataManager().quickGetMetaData(R.string.mtState, String.class);
                mingrenState = getDataManager().quickGetMetaData(R.string.mrState, String.class);

                initView();
            }
        });

    }

    private void initView() {

        if (dyState.equals("1")) {
            daren_dy_iv.setImageResource(R.mipmap.ic_dy_select);
        } else {
            daren_dy_iv.setImageResource(R.mipmap.ic_dy_select_normal);
        }

        if (pyqState.equals("1")) {
            daren_py_iv.setImageResource(R.mipmap.ic_py_select);
        } else {
            daren_py_iv.setImageResource(R.mipmap.ic_py_select_normal);
        }

        if (wbState.equals("1")) {
            daren_wb_iv.setImageResource(R.mipmap.ic_wb_select);
        } else {
            daren_wb_iv.setImageResource(R.mipmap.ic_wb_select_normal);
        }

        if (wsState.equals("1")) {
            daren_ws_iv.setImageResource(R.mipmap.ic_ws_select);
        } else {
            daren_ws_iv.setImageResource(R.mipmap.ic_ws_select_normal);
        }

        if (wsState.equals("1")) {
            daren_other_iv.setImageResource(R.mipmap.ic_other_select);
        } else {
            daren_other_iv.setImageResource(R.mipmap.ic_other_select_normal);
        }


        //0-未申请   1-正在审核中  2-已通过  3-未通过 lingState 达人
        daren_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if (darenState.equals("0") || "".equals(darenState)) {

            daren_clv.setFillColor(0xffa9a9a9); // 背景色
            daren_clv.right();                  // 位置
            daren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            daren_clv.setPaddingCenter(2);        // 填充标签的边距
            daren_clv.setPaddingBottom(-4);        // 填充标签的边距
            daren_clv.setText2("未激活");
            daren_clv.setText2Color(0xffffffff);

        } else if (darenState.equals("1")) {
            daren_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            daren_clv.right();                  // 位置
            daren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            daren_clv.setPaddingCenter(2);        // 填充标签的边距
            daren_clv.setPaddingBottom(-4);        // 填充标签的边距
            daren_clv.setText2("审核中");
            daren_clv.setText2Color(0xffffffff);
        } else if (darenState.equals("2")) {
            daren_clv.setFillColor(0xffff6347); // 背景色
            daren_clv.right();                  // 位置
            daren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            daren_clv.setPaddingCenter(2);        // 填充标签的边距
            daren_clv.setPaddingBottom(-4);        // 填充标签的边距
            daren_clv.setText2("已激活");
            daren_clv.setText2Color(0xffffffff);
        } else if (darenState.equals("3")) {
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

        item_daren_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!"2".equals(darenState)) {
                    Intent intent = new Intent(IdentityActivity.this, DarenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(IdentityActivity.this, "达人已认证", Toast.LENGTH_SHORT).show();

                }

            }
        });


        guangao_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if (guanggaozhuState.equals("0") || "".equals(guanggaozhuState)) {

            guangao_clv.setFillColor(0xffa9a9a9); // 背景色
            guangao_clv.right();                  // 位置
            guangao_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            guangao_clv.setPaddingCenter(2);        // 填充标签的边距
            guangao_clv.setPaddingBottom(-4);        // 填充标签的边距
            guangao_clv.setText2("未激活");
            guangao_clv.setText2Color(0xffffffff);

        } else if (guanggaozhuState.equals("1")) {
            guangao_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            guangao_clv.right();                  // 位置
            guangao_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            guangao_clv.setPaddingCenter(2);        // 填充标签的边距
            guangao_clv.setPaddingBottom(-4);        // 填充标签的边距
            guangao_clv.setText2("审核中");
            guangao_clv.setText2Color(0xffffffff);
        } else if (guanggaozhuState.equals("2")) {
            guangao_clv.setFillColor(0xffff6347); // 背景色
            guangao_clv.right();                  // 位置
            guangao_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            guangao_clv.setPaddingCenter(2);        // 填充标签的边距
            guangao_clv.setPaddingBottom(-4);        // 填充标签的边距
            guangao_clv.setText2("已激活");
            guangao_clv.setText2Color(0xffffffff);

        } else if (guanggaozhuState.equals("3")) {
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
                if (guanggaozhuState.equals("0") || guanggaozhuState.equals("3")) {
                    Intent intent = new Intent(IdentityActivity.this, GuanggaoActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(IdentityActivity.this, "广告主已认证", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mingren_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if (mingrenState.equals("0") || "".equals(mingrenState)) {

            mingren_clv.setFillColor(0xffa9a9a9); // 背景色
            mingren_clv.right();                  // 位置
            mingren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            mingren_clv.setPaddingCenter(2);        // 填充标签的边距
            mingren_clv.setPaddingBottom(-4);        // 填充标签的边距
            mingren_clv.setText2("未激活");
            mingren_clv.setText2Color(0xffffffff);

        } else if (mingrenState.equals("1")) {
            mingren_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            mingren_clv.right();                  // 位置
            mingren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            mingren_clv.setPaddingCenter(2);        // 填充标签的边距
            mingren_clv.setPaddingBottom(-4);        // 填充标签的边距
            mingren_clv.setText2("审核中");
            mingren_clv.setText2Color(0xffffffff);
        } else if (mingrenState.equals("2")) {
            mingren_clv.setFillColor(0xffff6347); // 背景色
            mingren_clv.right();                  // 位置
            mingren_clv.triangle(true);           // 是否设置填充角标
            //daren_clv.setText1("未激活");       // 标签内容
            //daren_clv.setText1Height(12);       // 设置标签字体大小
            mingren_clv.setPaddingCenter(2);        // 填充标签的边距
            mingren_clv.setPaddingBottom(-4);        // 填充标签的边距
            mingren_clv.setText2("已激活");
            mingren_clv.setText2Color(0xffffffff);
        } else if (mingrenState.equals("3")) {
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

                if ("1".equals(meitiState) || "2".equals(meitiState)) {

                    ToastUtil.show(IdentityActivity.this, "名人身份和媒体身份只能拥有一个");

                } else {
                    Intent intent = new Intent(IdentityActivity.this, MingrenActivity.class);
                    startActivity(intent);
                }
                if (mingrenState.equals("0") || mingrenState.equals("3")) {
                    if (meitiState.equals("1") || meitiState.equals("2")) {
                        ToastUtil.show(IdentityActivity.this, "名人身份和媒体身份只能拥有一个");
                    } else {
                        Intent intent = new Intent(IdentityActivity.this, MingrenActivity.class);
                        startActivity(intent);
                    }

                } else {
                    dialogs(mingrenState);
                }
            }
        });

        meiti_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if (meitiState.equals("0") || "".equals(meitiState)) {

            meiti_clv.setFillColor(0xffa9a9a9); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("未激活");
            meiti_clv.setText2Color(0xffffffff);

        } else if (meitiState.equals("1")) {
            meiti_clv.setFillColor(Color.parseColor("#b7d63d")); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("审核中");
            meiti_clv.setText2Color(0xffffffff);
        } else if (meitiState.equals("2")) {
            meiti_clv.setFillColor(0xffff6347); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("已激活");
            meiti_clv.setText2Color(0xffffffff);
        } else if (meitiState.equals("3")) {
            meiti_clv.setFillColor(0xffff4500); // 背景色
            meiti_clv.right();                  // 位置
            meiti_clv.triangle(true);           // 是否设置填充角标
            meiti_clv.setPaddingCenter(2);        // 填充标签的边距
            meiti_clv.setPaddingBottom(-4);        // 填充标签的边距
            meiti_clv.setText2("激活失败");
            meiti_clv.setText2Color(0xffffffff);
        }

        item_meiti_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("2".equals(mingrenState) || "1".equals(mingrenState)) {

                    ToastUtil.show(IdentityActivity.this, "名人身份和媒体身份只能拥有一个");

                } else {

                    Intent intent = new Intent(IdentityActivity.this, MeitiActivity.class);
                    startActivity(intent);
                }
            }
        });

        smrz_clv.setPaddingTop(paddingTop);          // 距离角的边距
        if ("".equals(smState)) {
            smrz_clv.setFillColor(0xffa9a9a9); // 背景色
            smrz_clv.right();                  // 位置
            smrz_clv.triangle(true);           // 是否设置填充角标
            smrz_clv.setPaddingCenter(2);        // 填充标签的边距
            smrz_clv.setPaddingBottom(-4);        // 填充标签的边距
            smrz_clv.setText2("未认证");
            smrz_clv.setText2Color(0xffffffff);
        } else {
            smrz_clv.setFillColor(0xffff6347); // 背景色
            smrz_clv.right();                  // 位置
            smrz_clv.triangle(true);           // 是否设置填充角标
            smrz_clv.setPaddingCenter(2);        // 填充标签的边距
            smrz_clv.setPaddingBottom(-4);        // 填充标签的边距
            smrz_clv.setText2("已认证");
            smrz_clv.setText2Color(0xffffffff);
        }

        smrz_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ("".equals(smState)) {
                    startActivity(new Intent(IdentityActivity.this, AuthenticationActivity.class));

                } else {
                    Toast.makeText(IdentityActivity.this, "已经实名认证过了", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //媒体、广告主、名人
    private void dialogs(String state) {

        AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);


        //0：未激活  1：审核中  2：已激活 3：激活失败
        if (state.equals("0")) {
            builder.setMessage("未激活");
        } else if (state.equals("1")) {
            builder.setMessage("审核中");
        } else if (state.equals("2")) {
            builder.setMessage("已激活");
        } else if (state.equals("3")) {
            builder.setMessage("激活失败");
        }

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public static void start(Context context, UserInfo user) {
        if (!CommonUtils.isLogin(context)) {
            return;
        }

        Intent intent = new Intent(context, IdentityActivity.class);

        if (user.getLingState() == null) {
            intent.putExtra("darenState", "0");
        } else {
            intent.putExtra("darenState", user.getLingState());
        }

        if (user.getFaState() == null) {
            intent.putExtra("guanggaozhuState", "0");
        } else {
            intent.putExtra("guanggaozhuState", user.getFaState());
            Log.e("达人状态", "url===" + user.getId());
        }

        if (user.getMrState() == null) {
            intent.putExtra("mingrenState", "0");
        } else {
            intent.putExtra("mingrenState", user.getMrState());
        }

        if (user.getMtState() == null) {
            intent.putExtra("meitiState", "0");
        } else {
            intent.putExtra("meitiState", user.getMtState());
        }

        if (user.getDyState() == null) {
            intent.putExtra("dyState", "0");
        } else {
            intent.putExtra("dyState", user.getDyState());
        }

        if (user.getPyqState() == null) {
            intent.putExtra("pyqState", "0");
        } else {
            intent.putExtra("pyqState", user.getPyqState());
        }

        if (user.getWbState() == null) {
            intent.putExtra("wbState", "0");
        } else {
            intent.putExtra("wbState", user.getWbState());
        }

        if (user.getWsState() == null) {
            intent.putExtra("wsState", "0");
        } else {
            intent.putExtra("wsState", user.getWsState());
        }

        context.startActivity(intent);
    }

}
