package com.application.tchapj.task.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.bean.PromotionPayResultBean;
import com.application.tchapj.bean.PromotionResultBean;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.task.bean.FaTaskBean;
import com.application.tchapj.task.bean.FaTaskSuccessBean;
import com.application.tchapj.task.bean.FaTaskSuccessafterBean;

/**
 * Created by Administrator on 2018\8\25 0025.
 */

public interface IFaTaskView extends BaseMvpView {

    // 发任务
    void onGetFaTaskBeanModels(FaTaskBean faTaskBean);

    // 预付订单
    void onGetAlipayOrderInfoSuccessBeanModels(FaTaskSuccessBean faTaskSuccessBean);

    // 任务付款
    void onGetFaTaskSuccessafterBeanModels(FaTaskSuccessafterBean faTaskSuccessafterBean);

    void onGetBrokerage(MoneyInfoBean moneyInfoBean);

    /**
     * 返回预付订单的支付
     */
    void onGetPromotionPaySuccess(PromotionPayResultBean baseBean);

}
