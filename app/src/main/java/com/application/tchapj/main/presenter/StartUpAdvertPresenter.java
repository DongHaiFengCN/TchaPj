package com.application.tchapj.main.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.view.ICircleView;
import com.application.tchapj.main.view.IStartUpAdvertView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 安卓开发 on 2018/8/9.
 */

public class StartUpAdvertPresenter extends BaseMvpPresenter<IStartUpAdvertView> {

    public StartUpAdvertPresenter(App app) {
        super(app);
    }


}
