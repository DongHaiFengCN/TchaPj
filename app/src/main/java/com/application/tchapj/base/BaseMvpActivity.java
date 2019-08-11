package com.application.tchapj.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.utils2.videoplayer.NiceVideoPlayerManager;
import com.application.tchapj.widiget.ToolbarHelper;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import rx.subscriptions.CompositeSubscription;

// MVP中Activity的基类
public abstract class BaseMvpActivity <V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V,P> implements BGASwipeBackHelper.Delegate{

    private boolean pressedHome;
    private HomeKeyWatcher mHomeKeyWatcher;

    private CompositeSubscription mCompositeSubscription;
//    protected BGASwipeBackHelper mSwipeBackHelper;


    private AlertDialog alertDialog;
    private final String TAG = BaseMvpActivity.class.getSimpleName();

    @Override // 获得布局
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        setContentView(getRootViewId());

        // 设置状态栏
        setStatusBar(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // 默认不显示原生标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initToolbar(new ToolbarHelper(toolbar));
        }

        mHomeKeyWatcher = new HomeKeyWatcher(this);
        mHomeKeyWatcher.setOnHomePressedListener(new HomeKeyWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                pressedHome = true;
            }
        });
        pressedHome = false;
        mHomeKeyWatcher.startWatch();

        ButterKnife.bind(this);

        initUI(); // 初始化UI
        initData();

    }

    @Override // 获得控件
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
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

    /**
     * // 修改当前 Activity 的显示模式，hideStatusBarBackground :true 全屏模式，false 着色模式
     * @param hideStatusBarBackground
     */
    private void setStatusBar(boolean hideStatusBarBackground) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (hideStatusBarBackground) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }

            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                if (hideStatusBarBackground) {
                    mChildView.setPadding(
                            mChildView.getPaddingLeft(),
                            0,
                            mChildView.getPaddingRight(),
                            mChildView.getPaddingBottom()
                    );
                } else {
                    int statusHeight = getStatusBarHeight(BaseMvpActivity.this);
                    mChildView.setPadding(
                            mChildView.getPaddingLeft(),
                            statusHeight,
                            mChildView.getPaddingRight(),
                            mChildView.getPaddingBottom()
                    );
                }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (hideStatusBarBackground) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.primaryDark));
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

    // 得到状态栏的高度
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    // 初始化状态栏
    protected abstract void initToolbar(ToolbarHelper toolbarHelper);

    @Override // 目录
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm != null && fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
//    private void initSwipeBackFinish() {
//        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
//
//        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
//        // 下面几项可以不配置，这里只是为了讲述接口用法。
//
//        // 设置滑动返回是否可用。默认值为 true
//        mSwipeBackHelper.setSwipeBackEnable(true);
//        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
//        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
//        // 设置是否是微信滑动返回样式。默认值为 true
//        mSwipeBackHelper.setIsWeChatStyle(true);
//        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
//        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
//        // 设置是否显示滑动返回的阴影效果。默认值为 true
//        mSwipeBackHelper.setIsNeedShowShadow(true);
//        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
//        mSwipeBackHelper.setIsShadowAlphaGradient(true);
//        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
//        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
//    }

    // 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，
    // 如果某个界面不想支持滑动返回则重写该方法返回 false 即可
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    // 正在滑动返回 @param slideOffset 从 0 到 1
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    // 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    // 滑动返回执行完毕，销毁当前 Activity
    @Override
    public void onSwipeBackLayoutExecuted() {
//        mSwipeBackHelper.swipeBackward();
    }

    @Override
    protected void onStop() {
        // 在OnStop中是release还是suspend播放器，需要看是不是因为按了Home键
        if (pressedHome) {
            NiceVideoPlayerManager.instance().suspendNiceVideoPlayer();
        } else {
            NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
        }
        super.onStop();
        mHomeKeyWatcher.stopWatch();
    }

    @Override
    protected void onRestart() {
        mHomeKeyWatcher.startWatch();
        pressedHome = false;
        super.onRestart();
        NiceVideoPlayerManager.instance().resumeNiceVideoPlayer();
    }

    // 正在滑动返回的时候取消返回按钮事件
    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
//        if (mSwipeBackHelper.isSliding()) {
//            return;
//        }
//        mSwipeBackHelper.backward();

        if (NiceVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
        super.onBackPressed();

    }

    public void showLoadingDialog() {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.layout_common_loading2);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    protected void showToast(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }



    // 得到程序App状态
    public App getApp(){
        return (App)getApplication();
    }

    // 获得控件ID
    public abstract int getRootViewId();

    // 初始化UI
    public abstract void initUI();

    // 初始化数据
    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null && !this.mCompositeSubscription.isUnsubscribed()){
            this.mCompositeSubscription.unsubscribe();
        }
    }

    public void upload(String fileUrl, final UpCompletionHandler upCompletionHandler) {
        Log.e(TAG, "upload()");
        showLoadingDialog();
        //上传配置
        Configuration config = new Configuration.Builder().chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(10) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
                .zone(FixedZone.zone2) // 设置区域，指默认 Zone.zone0 注：这步是最关键的 当初错的主要原因也是他 根据自己的地方选
                .build();
        UploadManager uploadManager = new UploadManager(config);

        uploadManager.put(fileUrl, null, App.QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                upCompletionHandler.complete(key, info, response);
                dismissLoadingDialog();
            }
        }, null);
    }


}
