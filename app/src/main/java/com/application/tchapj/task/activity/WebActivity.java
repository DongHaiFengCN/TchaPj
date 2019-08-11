package com.application.tchapj.task.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.application.tchapj.R;
import com.application.tchapj.task.common.FragmentKeyDown;
import com.application.tchapj.task.fragment.AgentWebFragment;
import com.application.tchapj.task.fragment.VasSonicFragment;

import static com.application.tchapj.task.sonic.SonicJavaScriptInterface.PARAM_CLICK_TIME;

/**
 * Created by Administrator on 2018/11/9.
 */

public class WebActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    public static final String TYPE_KEY = "type_key";
    public static final String TYPE_URl = "type_url";
    private FragmentManager mFragmentManager;

    private AgentWebFragment mAgentWebFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_common);

        mFrameLayout = (FrameLayout) this.findViewById(R.id.container_framelayout);
        int key = getIntent().getIntExtra(TYPE_KEY, -1);
        String url = getIntent().getStringExtra(TYPE_URl);
        mFragmentManager = this.getSupportFragmentManager();
        openFragment(key, url);
    }

    private void openFragment(int key,String url) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Bundle mBundle = null;


        switch (key) {

            // 腾讯使用的首屏秒开
            case 0x01:
                ft.add(R.id.container_framelayout, mAgentWebFragment = VasSonicFragment.create(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putLong(PARAM_CLICK_TIME, getIntent().getLongExtra(PARAM_CLICK_TIME, -1L));
                mBundle.putString(AgentWebFragment.URL_KEY, url);
                break;
            default:
                break;

        }
        ft.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //一定要保证 mAentWebFragemnt 回调
//		mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        AgentWebFragment mAgentWebFragment = this.mAgentWebFragment;
        if (mAgentWebFragment != null) {
            FragmentKeyDown mFragmentKeyDown = mAgentWebFragment;
            if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event)) {
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

