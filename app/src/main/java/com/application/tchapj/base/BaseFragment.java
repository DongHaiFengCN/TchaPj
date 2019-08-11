package com.application.tchapj.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 * @param <T>
 */
public abstract class BaseFragment<T> extends Fragment {
    private static String TAG = "BaseMapFragment";
    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;

    public View getmRootView() {
        return mRootView;
    }

    /**
     * 根view
     */
    protected View mRootView;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;

    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(setLayoutResouceId(), container, false);
        mUnbinder = ButterKnife.bind(this,mRootView);
        initData(getArguments());
        initView();
        mIsPrepare = true;
//        onLazyLoad();
        setListener();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
    }

    /**
     * 初始化数据
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected void initData(Bundle arguments)
    {
    }

    /**初始化View*/
    protected void initView()
    {
    }

    /**设置监听事件*/
    protected void setListener()
    {
    }
    //在ViewPager中的Fragment
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG,"---------setUserVisibleHint-----"+isVisibleToUser);
        this.mIsVisible = isVisibleToUser;
        if (isVisibleToUser)
        {
            onVisibleToUser();
        }
    }
    /**使用show和hide来显隐的Fragment*/
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            Log.i(TAG,"---------onHiddenChanged-----"+hidden);
        }else{
            Log.i(TAG,"---------onHiddenChanged---111--"+hidden);
            onLazyLoad();
        }
    }

    /**用户可见时执行的操作*/
    protected void onVisibleToUser()
    {
        if (mIsPrepare && mIsVisible)
        {
            onLazyLoad();
        }
    }

    /**
     * 当用户可见时 加载数据
     */
    protected void onLazyLoad()
    {

    }

    @SuppressWarnings("unchecked")
    protected  T findViewById(int id)
    {
        if (mRootView == null)
        {
            return null;
        }

        return (T) mRootView.findViewById(id);
    }

    @Override // 销毁
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null)
            mUnbinder.unbind();
    }


    protected abstract int setLayoutResouceId();
}