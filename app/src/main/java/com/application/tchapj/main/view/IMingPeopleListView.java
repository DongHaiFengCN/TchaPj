package com.application.tchapj.main.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.MingPeopleListBean;

/**
 * Created by Administrator on 2018/10/10.
 */

public interface IMingPeopleListView extends BaseMvpView {

    // 明星活跃周榜
    void onGetMingPeopleListResult(MingPeopleListBean mingPeopleListBean);

}
