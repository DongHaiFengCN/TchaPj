// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MoneyHfFragment_ViewBinding implements Unbinder {
  private MoneyHfFragment target;

  @UiThread
  public MoneyHfFragment_ViewBinding(MoneyHfFragment target, View source) {
    this.target = target;

    target.money_hf_srl = Utils.findRequiredViewAsType(source, R.id.money_hf_srl, "field 'money_hf_srl'", SmartRefreshLayout.class);
    target.money_hf_rv = Utils.findRequiredViewAsType(source, R.id.money_hf_rv, "field 'money_hf_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MoneyHfFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.money_hf_srl = null;
    target.money_hf_rv = null;
  }
}
