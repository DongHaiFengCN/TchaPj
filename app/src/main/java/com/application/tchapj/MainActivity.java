package com.application.tchapj;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.consultation.fragment.ConsultationFragment;
import com.application.tchapj.main.fragment.HomeFragment;
import com.application.tchapj.my.activity.MingrenActivity;
import com.application.tchapj.my.fragment.MyFragment;
import com.application.tchapj.rxbus.ChangeAnswerEvent;
import com.application.tchapj.rxbus.RxBus;
import com.application.tchapj.task.fragment.TaskFragment;
import com.application.tchapj.utils2.videoplayer.NiceVideoPlayer;
import com.application.tchapj.video.fragment.VideoFragment;
import com.application.tchapj.widiget.ToolbarHelper;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends BaseActvity implements HomeFragment.HomeFragmentListener {


    // 获得控件
    @BindView(R.id.rbHome)
    RadioButton rbHome;       // 首页
    @BindView(R.id.rbConsultation)
    RadioButton rbConsultation;     // 咨询
    @BindView(R.id.rbVideo)
    RadioButton rbVideo;      // 视频
    @BindView(R.id.rbTask)
    RadioButton rbTask;     // 任务
    @BindView(R.id.rbMy)
    RadioButton rbMy;         // 我的

    // 得到Fragment对象
    private HomeFragment homeFragment;
    private ConsultationFragment consultationFragment;
    private VideoFragment videoFragment;
    private TaskFragment taskFragment;
    private MyFragment myFragment;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    // 退出状态
    private boolean isExit;

    private ChangeAnswerEvent changeAnswerEvent;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        //刷新配置
        DataManager.getDataManager()
                .disposeMember(new Up());

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 动态读取权限
        if (Build.VERSION.SDK_INT >= 23) {
            verifyStoragePermissions(MainActivity.this);
        }

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        App.PackageName = getPackageName();
        showHomeFragment();

    }

    // 点击事件
    @OnClick({R.id.rbHome, R.id.rbConsultation,R.id.rbVideo, R.id.rbTask, R.id.rbMy})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rbHome:
                showHomeFragment();
                // 消息机制
                changeAnswerEvent = new ChangeAnswerEvent();
                changeAnswerEvent.setAnswer("1");
                RxBus.getDefault().post(changeAnswerEvent);
                if(NiceVideoPlayer.mMediaPlayer != null){
                    NiceVideoPlayer.mMediaPlayer.pause();
                }
                break;
            case R.id.rbConsultation:
                showConsultationFragment();
                if(NiceVideoPlayer.mMediaPlayer != null){
                    NiceVideoPlayer.mMediaPlayer.pause();
                }

                // 消息机制
                changeAnswerEvent = new ChangeAnswerEvent();
                changeAnswerEvent.setAnswer("1");
                RxBus.getDefault().post(changeAnswerEvent);
                break;

            case R.id.rbVideo:
                showVideoFragment();
                // 消息机制
                changeAnswerEvent = new ChangeAnswerEvent();
                changeAnswerEvent.setAnswer("");
                RxBus.getDefault().post(changeAnswerEvent);
                break;

            case R.id.rbTask:
                showTaskTab();
                break;
            case R.id.rbMy:
                showMyFragment();
                if(NiceVideoPlayer.mMediaPlayer != null){
                    NiceVideoPlayer.mMediaPlayer.pause();
                }

                // 消息机制
                changeAnswerEvent = new ChangeAnswerEvent();
                changeAnswerEvent.setAnswer("1");
                RxBus.getDefault().post(changeAnswerEvent);
                break;
        }
    }

    // 显示首页HomeFragment
    public void showHomeFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if(homeFragment == null){
            homeFragment = HomeFragment.newInstance("HomeFragment");
            homeFragment.setHomeFragmentListener(this);
            fragmentTransaction.add(R.id.fragmentContent,homeFragment);
        }
        commitShowFragment(fragmentTransaction,homeFragment);
    }

    // 显示咨询Fragment
    public void showConsultationFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(consultationFragment == null){
            consultationFragment = ConsultationFragment.newInstance("ConsultationFragment");
            fragmentTransaction.add(R.id.fragmentContent,consultationFragment);
        }

        commitShowFragment(fragmentTransaction,consultationFragment);

    }

    // 显示视频Fragment
    public void showVideoFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(videoFragment == null){
            videoFragment = VideoFragment.newInstance("VideoFragment");
            fragmentTransaction.add(R.id.fragmentContent,videoFragment);
        }

        commitShowFragment(fragmentTransaction,videoFragment);

    }

    // 显示任务Fragment
    public void showTaskFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(taskFragment == null){
            taskFragment = TaskFragment.newInstance("TaskFragment");
            fragmentTransaction.add(R.id.fragmentContent,taskFragment);
        }

        commitShowFragment(fragmentTransaction,taskFragment);

    }

    // 显示MyFragment
    public void showMyFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(myFragment == null){
            myFragment = MyFragment.newInstance("MyFragment");
            fragmentTransaction.add(R.id.fragmentContent,myFragment);
        }

        commitShowFragment(fragmentTransaction,myFragment);

    }

    // 显示Fragment
    public void commitShowFragment(FragmentTransaction fragmentTransaction,Fragment fragment){
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    // 隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        hideFragment(fragmentTransaction,homeFragment);
        hideFragment(fragmentTransaction,consultationFragment);
        hideFragment(fragmentTransaction,videoFragment);
        hideFragment(fragmentTransaction,taskFragment);
        hideFragment(fragmentTransaction,myFragment);
    }

    // 隐藏Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment){
        if(fragment!=null){
            fragmentTransaction.hide(fragment);
        }
    }

    // 切换Fragment
    public void replaceFragment(@IdRes int id, Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    @Override // 退出
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }

        if(!isExit){
            ToastUtils.showToast(getApplicationContext(),R.string.press_again_to_exit);
            isExit = true;
            EventBus.getDefault().post(isExit);
        }else{
            super.onBackPressed();
        }
    }

    // 退出并设置是否退出状态
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventExit(Boolean isBool){
        SystemClock.sleep(1000);
        isExit = false;
    }

    @Override // 重启
    protected void onResume() {
        super.onResume();
        isExit = false;
    }

    @Override // 销毁
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    /**
     * 显示任务tab
     */
    private void showTaskTab() {
        rbTask.setChecked(true);
        showTaskFragment();
        if(NiceVideoPlayer.mMediaPlayer != null){
            NiceVideoPlayer.mMediaPlayer.pause();
        }
        // 消息机制
        changeAnswerEvent = new ChangeAnswerEvent();
        changeAnswerEvent.setAnswer("1");
        RxBus.getDefault().post(changeAnswerEvent);
    }


    /**
     * 首页Fragment 微任务 img的click点击事件
     */
    @Override
    public void tackImgClick() {
        showTaskTab();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    public static class Up implements DataManager.UpDataListener{
        @Override
        public void updata(boolean getDataSuccess) {

            if(getDataSuccess){

                Log.e("DOAING","更新成功");

            }else {
                Log.e("DOAING","更新失败");
            }

        }
    }
}
