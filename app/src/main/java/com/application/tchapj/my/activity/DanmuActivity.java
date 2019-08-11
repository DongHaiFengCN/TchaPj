package com.application.tchapj.my.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.my.bean.DamuInfo;
import com.application.tchapj.my.bean.PostDamuInfo;
import com.application.tchapj.my.presenter.DamuPresenter;
import com.application.tchapj.my.view.IDamuView;
import com.application.tchapj.utils2.danmu.Danmu;
import com.application.tchapj.utils2.danmu.DanmuControl;
import com.application.tchapj.widiget.ToolbarHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import master.flame.danmaku.controller.IDanmakuView;

/**
 * Created by Sun on 2018/7/30.
 */

public class DanmuActivity extends BaseMvpActivity<IDamuView, DamuPresenter> implements IDamuView {

    private IDanmakuView mDanmakuView;
    private DanmuControl mDanmuControl;

    private EditText danmu_et;
    private ImageView btnAddDanmu;

    private String memberId;
    private String nickName;

    private List<Danmu> danmus;
    private PostDamuInfo.PostDamuInfoResult.PostDamuInfOpinions postDamuInfos;
    private List<DamuInfo.DamuInfoResult.DamuInfOpinions> damuInfoList = new ArrayList<>();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        Intent intent = getIntent();

        memberId = intent.getStringExtra("memberId");
        nickName = intent.getStringExtra("nickName");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_danmu;
    }

    @Override
    public void initUI() {

        mDanmuControl = new DanmuControl(this);

        danmu_et = (EditText) findViewById(R.id.danmu_et);
        mDanmakuView = (IDanmakuView) findViewById(R.id.danmakuView);
        btnAddDanmu = (ImageView) findViewById(R.id.btnAddDanmu);
        mDanmuControl.setDanmakuView(mDanmakuView);

        btnAddDanmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 发弹幕
                getPresenter().onGetPostDamuInfoResult(memberId, String.valueOf(danmu_et.getText()),nickName);
            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().onGetDamuInfoResult();
            }
        }, 2000);


    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public DamuPresenter createPresenter() {
        return new DamuPresenter(getApp());
    }

    @Override // 弹幕信息
    public void onGetDamuInfoResult(DamuInfo damuInfo) {

        if ("000".equals(damuInfo.getCode())) {
            damuInfoList = damuInfo.getData().getOpinions();
            danmus = new ArrayList<>();

            for(int i=0;i<damuInfoList.size();i++){

                if(damuInfoList.get(i).getOpinions().equals(null)){
                    Danmu danmu = new Danmu(0, i, "Like", R.mipmap.ic_default_header, damuInfoList.get(i).getOpinions());
                    danmus.add(danmu);
                   /* Danmu danmu1 = new Danmu(0, 1, "Like", R.mipmap.ic_default_header, "");
                    Danmu danmu2 = new Danmu(0, 2, "Comment", R.mipmap.ic_default_header, "这是一条弹幕啦啦啦");
                    Danmu danmu3 = new Danmu(0, 3, "Like", R.mipmap.ic_default_header, "");
                    Danmu danmu4 = new Danmu(0, 1, "Comment", R.mipmap.wat, "这又是一条弹幕啦啦啦");
                    Danmu danmu5 = new Danmu(0, 2, "Like", R.mipmap.wat, "");
                    Danmu danmu6 = new Danmu(0, 3, "Comment", R.mipmap.wat, "这还是一条弹幕啦啦啦");*/
                }else {
                    Danmu danmu = new Danmu(0, i, "Comment", R.mipmap.ic_default_header, damuInfoList.get(i).getOpinions());
                    danmus.add(danmu);
                }

            }

            Collections.shuffle(danmus);
            mDanmuControl.addDanmuList(danmus);

        }

    }

    @Override // 发弹幕
    public void onGetPostDamuInfoResult(PostDamuInfo postDamuInfo) {

        if ("000".equals(postDamuInfo.getCode())) {
            postDamuInfos = postDamuInfo.getData().getOpinions();

            danmu_et.setText("");
            Toast.makeText(DanmuActivity.this,"发表成功",Toast.LENGTH_LONG).show();
//            dialogs();
            getPresenter().onGetDamuInfoResult();
        }

    }

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(DanmuActivity.this);
        builder.setMessage("是否打开弹幕查看留言？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                /*BmobUpdateAgent.setDefault();
                mActivity.finish();
                System.exit(0);*/

                getPresenter().onGetDamuInfoResult();
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
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mDanmuControl.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDanmuControl.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDanmuControl.destroy();
    }

}
