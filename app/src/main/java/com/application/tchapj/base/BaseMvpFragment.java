package com.application.tchapj.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.utils2.videoplayer.NiceVideoPlayerManager;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.king.base.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

// MVP中Fragment的基类
public abstract class BaseMvpFragment <V extends BaseMvpView, P extends BaseMvpPresenter<V>>  extends MvpFragment<V,P> {

    protected Context context;

    private View rootView;

    private Unbinder mUnbinder;

    private boolean pressedHome;
    private HomeKeyWatcher mHomeKeyWatcher;

    // 获得控件ID
    public <T extends View> T findView(@IdRes int id){
        return (T)rootView.findViewById(id);
    }


    @Nullable
    @Override // 创建View
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        rootView = inflater.inflate(getRootViewId(),container,false);
        mUnbinder = ButterKnife.bind(this,rootView);
        LogUtils.d("onCreateView");
        initUI();
        return rootView;
    }

    // 得到程序App信息
    public App getApp(){
        return (App)getActivity().getApplication();
    }

    // 得到View
    public View getRootView(){
        return rootView;
    }

    @Override // 创建Activity
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeKeyWatcher = new HomeKeyWatcher(getActivity());
        mHomeKeyWatcher.setOnHomePressedListener(new HomeKeyWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                pressedHome = true;
            }
        });
        pressedHome = false;
        mHomeKeyWatcher.startWatch();
    }

    @Override
    public void onStart() {
        mHomeKeyWatcher.startWatch();
        pressedHome = false;
        super.onStart();
        NiceVideoPlayerManager.instance().resumeNiceVideoPlayer();
    }

    @Override
    public void onStop() {
        // 在OnStop中是release还是suspend播放器，需要看是不是因为按了Home键
        if (pressedHome) {
            NiceVideoPlayerManager.instance().suspendNiceVideoPlayer();
        } else {
            NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
        }
        super.onStop();
        mHomeKeyWatcher.stopWatch();
    }

    @Override // 销毁
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null)
            mUnbinder.unbind();
    }

    // 替换Fragment
    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    // 替换ChildFragment
    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    // 设置数据
    public <T> void  toSetList(List<T> list, List<T> newList, boolean isMore){

        if(list!=null && newList!=null){
            synchronized (BaseFragment.class){
                if(!isMore){
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }

    protected void showToast(String content){
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    // 注册事件
    public static void registerEvent(Object obj){
        EventBus.getDefault().register(obj);
    }

    // 未注册事件
    public static void unregisterEvent(Object obj){
        EventBus.getDefault().unregister(obj);
    }

    // 发送事件
    public static void sendEvent(Object obj){
        EventBus.getDefault().post(obj);
    }

    // 线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj){

    }

    // 得到意图
    protected Intent getIntent(){
        return getActivity().getIntent();
    }


    // 得到开始Activity意图
    protected void startActivity(Class<?> cls){
        startActivity(new Intent(context,cls));
    }

    // 释放
    protected void finish(){
        getActivity().finish();
    }


    // 得到页面ID
    public abstract int getRootViewId();

    // 初始化UI
    public abstract void initUI();

    // 初始化数据
    public abstract void initData();


}
