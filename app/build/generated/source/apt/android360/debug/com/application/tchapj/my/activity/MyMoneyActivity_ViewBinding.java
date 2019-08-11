// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyMoneyActivity_ViewBinding implements Unbinder {
  private MyMoneyActivity target;

  private View view2131755662;

  private View view2131755663;

  private View view2131755667;

  private View view2131756326;

  @UiThread
  public MyMoneyActivity_ViewBinding(MyMoneyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyMoneyActivity_ViewBinding(final MyMoneyActivity target, View source) {
    this.target = target;

    View view;
    target.detailedTv = Utils.findRequiredViewAsType(source, R.id.detailed_tv, "field 'detailedTv'", TextView.class);
    target.my_money_value = Utils.findRequiredViewAsType(source, R.id.my_money_value, "field 'my_money_value'", TextView.class);
    target.sy_tv = Utils.findRequiredViewAsType(source, R.id.sy_tv, "field 'sy_tv'", TextView.class);
    target.dj_tv = Utils.findRequiredViewAsType(source, R.id.dj_tv, "field 'dj_tv'", TextView.class);
    target.my_3_money = Utils.findRequiredViewAsType(source, R.id.my_3_money, "field 'my_3_money'", LinearLayout.class);
    target.my_10_money = Utils.findRequiredViewAsType(source, R.id.my_10_money, "field 'my_10_money'", LinearLayout.class);
    target.my_50_money = Utils.findRequiredViewAsType(source, R.id.my_50_money, "field 'my_50_money'", LinearLayout.class);
    target.my_100_money = Utils.findRequiredViewAsType(source, R.id.my_100_money, "field 'my_100_money'", LinearLayout.class);
    target.my_zdy_money = Utils.findRequiredViewAsType(source, R.id.my_zdy_money, "field 'my_zdy_money'", LinearLayout.class);
    target.my_30_money_tv = Utils.findRequiredViewAsType(source, R.id.my_30_money_tv, "field 'my_30_money_tv'", TextView.class);
    target.my_100_money_tv = Utils.findRequiredViewAsType(source, R.id.my_100_money_tv, "field 'my_100_money_tv'", TextView.class);
    target.my_500_money_tv = Utils.findRequiredViewAsType(source, R.id.my_500_money_tv, "field 'my_500_money_tv'", TextView.class);
    target.my_1000_money_tv = Utils.findRequiredViewAsType(source, R.id.my_1000_money_tv, "field 'my_1000_money_tv'", TextView.class);
    target.promotionFundTv = Utils.findRequiredViewAsType(source, R.id.promotion_fund_tv, "field 'promotionFundTv'", TextView.class);
    target.taskOccupyTv = Utils.findRequiredViewAsType(source, R.id.task_occupy_tv, "field 'taskOccupyTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.recharge_money_tv, "field 'rechargeMoneyTv' and method 'onViewClicked'");
    target.rechargeMoneyTv = Utils.castView(view, R.id.recharge_money_tv, "field 'rechargeMoneyTv'", TextView.class);
    view2131755662 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cash_withdrawal_tv, "field 'cashWithdrawalTv' and method 'onViewClicked'");
    target.cashWithdrawalTv = Utils.castView(view, R.id.cash_withdrawal_tv, "field 'cashWithdrawalTv'", TextView.class);
    view2131755663 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.icType = Utils.findRequiredViewAsType(source, R.id.ic_type, "field 'icType'", ImageView.class);
    target.myMoneyLl = Utils.findRequiredViewAsType(source, R.id.my_money_ll, "field 'myMoneyLl'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.my_money_frozen_ll, "field 'myMoneyFrozenLl' and method 'onViewClicked'");
    target.myMoneyFrozenLl = Utils.castView(view, R.id.my_money_frozen_ll, "field 'myMoneyFrozenLl'", LinearLayout.class);
    view2131755667 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back_framelayout, "method 'onViewClicked'");
    view2131756326 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MyMoneyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.detailedTv = null;
    target.my_money_value = null;
    target.sy_tv = null;
    target.dj_tv = null;
    target.my_3_money = null;
    target.my_10_money = null;
    target.my_50_money = null;
    target.my_100_money = null;
    target.my_zdy_money = null;
    target.my_30_money_tv = null;
    target.my_100_money_tv = null;
    target.my_500_money_tv = null;
    target.my_1000_money_tv = null;
    target.promotionFundTv = null;
    target.taskOccupyTv = null;
    target.rechargeMoneyTv = null;
    target.cashWithdrawalTv = null;
    target.icType = null;
    target.myMoneyLl = null;
    target.myMoneyFrozenLl = null;

    view2131755662.setOnClickListener(null);
    view2131755662 = null;
    view2131755663.setOnClickListener(null);
    view2131755663 = null;
    view2131755667.setOnClickListener(null);
    view2131755667 = null;
    view2131756326.setOnClickListener(null);
    view2131756326 = null;
  }
}
