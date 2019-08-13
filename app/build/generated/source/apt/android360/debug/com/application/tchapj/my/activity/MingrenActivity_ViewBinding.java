// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MingrenActivity_ViewBinding implements Unbinder {
  private MingrenActivity target;

  private View view2131756344;

  @UiThread
  public MingrenActivity_ViewBinding(MingrenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MingrenActivity_ViewBinding(final MingrenActivity target, View source) {
    this.target = target;

    View view;
    target.mingren_celebrity_fl = Utils.findRequiredViewAsType(source, R.id.mingren_celebrity_fl, "field 'mingren_celebrity_fl'", FlowTagLayout.class);
    target.mingren_name_et = Utils.findRequiredViewAsType(source, R.id.mingren_name_et, "field 'mingren_name_et'", EditText.class);
    target.mingren_mima_et = Utils.findRequiredViewAsType(source, R.id.mingren_mima_et, "field 'mingren_mima_et'", EditText.class);
    target.mingren_shouji_et = Utils.findRequiredViewAsType(source, R.id.mingren_shouji_et, "field 'mingren_shouji_et'", EditText.class);
    target.mingren_selfie_rv = Utils.findRequiredViewAsType(source, R.id.mingren_selfie_rv, "field 'mingren_selfie_rv'", RecyclerView.class);
    target.micro_info_next = Utils.findRequiredViewAsType(source, R.id.micro_info_next, "field 'micro_info_next'", Button.class);
    target.permanentCityNameTv = Utils.findRequiredViewAsType(source, R.id.permanent_address_city_name_tv, "field 'permanentCityNameTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.permanent_address_city_rl, "method 'onViewClicked'");
    view2131756344 = view;
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
    MingrenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mingren_celebrity_fl = null;
    target.mingren_name_et = null;
    target.mingren_mima_et = null;
    target.mingren_shouji_et = null;
    target.mingren_selfie_rv = null;
    target.micro_info_next = null;
    target.permanentCityNameTv = null;

    view2131756344.setOnClickListener(null);
    view2131756344 = null;
  }
}
