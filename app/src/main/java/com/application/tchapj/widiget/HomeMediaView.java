package com.application.tchapj.widiget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.main.fragment.HomeMediaFragment;

import java.util.List;

/**
 * Created by tanger on 2018/3/15.
 */

public class HomeMediaView extends FrameLayout {

    private TextView tv_more;
    private ViewPager vpg;
    private LinearLayout ll_icon;
    private HomeMediaFragment mediaFragment;
    private List<Fragment> fragments;

    public HomeMediaView(Context context) {
        super(context);
        initView(context);
    }

    public HomeMediaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeMediaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_home_media,this);
    }

    //传递过来的数据给控件赋值
    public void setData(List<String> data, Activity activity){
    }
}
