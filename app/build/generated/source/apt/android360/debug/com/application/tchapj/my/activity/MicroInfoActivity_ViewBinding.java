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
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MicroInfoActivity_ViewBinding implements Unbinder {
  private MicroInfoActivity target;

  @UiThread
  public MicroInfoActivity_ViewBinding(MicroInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MicroInfoActivity_ViewBinding(MicroInfoActivity target, View source) {
    this.target = target;

    target.micro_name_et = Utils.findRequiredViewAsType(source, R.id.micro_name_et, "field 'micro_name_et'", EditText.class);
    target.micro_mima_et = Utils.findRequiredViewAsType(source, R.id.micro_mima_et, "field 'micro_mima_et'", EditText.class);
    target.micro_shouji_et = Utils.findRequiredViewAsType(source, R.id.micro_shouji_et, "field 'micro_shouji_et'", EditText.class);
    target.micro_yz_et = Utils.findRequiredViewAsType(source, R.id.micro_yz_et, "field 'micro_yz_et'", EditText.class);
    target.micro_yz_bt = Utils.findRequiredViewAsType(source, R.id.micro_yz_bt, "field 'micro_yz_bt'", TextView.class);
    target.micro_infogh_rv = Utils.findRequiredViewAsType(source, R.id.micro_infogh_rv, "field 'micro_infogh_rv'", RecyclerView.class);
    target.micro_info_next = Utils.findRequiredViewAsType(source, R.id.micro_info_next, "field 'micro_info_next'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MicroInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.micro_name_et = null;
    target.micro_mima_et = null;
    target.micro_shouji_et = null;
    target.micro_yz_et = null;
    target.micro_yz_bt = null;
    target.micro_infogh_rv = null;
    target.micro_info_next = null;
  }
}
