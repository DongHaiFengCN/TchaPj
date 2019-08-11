package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.my.bean.FrozenInfoBean;

import java.util.List;

public interface IFrozenListView extends BaseMvpView {

    //冻结资金列表
    void getMoneyFrozenListModelResult(List<FrozenInfoBean> frozenlist);

    //冻结资金转到余额
    void getFrozenToBalanceModelResult(BaseBean baseBean);
}
