// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyMoneyMingXiActivity_ViewBinding implements Unbinder {
  private MyMoneyMingXiActivity target;

  @UiThread
  public MyMoneyMingXiActivity_ViewBinding(MyMoneyMingXiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyMoneyMingXiActivity_ViewBinding(MyMoneyMingXiActivity target, View source) {
    this.target = target;

    target.top_money_group = Utils.findRequiredViewAsType(source, R.id.top_money_group, "field 'top_money_group'", RadioGroup.class);
    target.top_money_sy = Utils.findRequiredViewAsType(source, R.id.top_money_sy, "field 'top_money_sy'", RadioButton.class);
    target.top_money_hf = Utils.findRequiredViewAsType(source, R.id.top_money_hf, "field 'top_money_hf'", RadioButton.class);
    target.top_money_pager = Utils.findRequiredViewAsType(source, R.id.top_money_pager, "field 'top_money_pager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyMoneyMingXiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.top_money_group = null;
    target.top_money_sy = null;
    target.top_money_hf = null;
    target.top_money_pager = null;
  }
}
