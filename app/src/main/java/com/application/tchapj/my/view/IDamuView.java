package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.my.bean.DamuInfo;
import com.application.tchapj.my.bean.PostDamuInfo;

/**
 * Created by 安卓开发 on 2018/7/30.
 */

public interface IDamuView extends BaseMvpView {


    void onGetDamuInfoResult(DamuInfo damuInfo);


    void onGetPostDamuInfoResult(PostDamuInfo postDamuInfo);


}
