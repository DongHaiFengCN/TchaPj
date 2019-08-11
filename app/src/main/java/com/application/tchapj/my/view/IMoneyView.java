package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;

/**
 * Created by Administrator on 2018\9\7 0007.
 */

public interface IMoneyView extends BaseMvpView {

    // 获取钱包信息
    void onGetMoneyInfoBeanResult(MoneyInfoBean moneyInfoBean);

    // 收益提现和退款--支付宝
    void onGetMoneyTransferBeanResult(MoneyTransferBean moneyTransferBean);

    // 我的任务列表--零钱模块
    void onGetMoneyInfoListBeanResult(MoneyInfoListBean moneyInfoListBean);

    //大额提现--人工转账
    void onGetArtificialTransferBeanResult(BaseBean baseBean);
}
