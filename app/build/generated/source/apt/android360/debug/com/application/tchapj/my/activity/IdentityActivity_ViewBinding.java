// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.my.wigiter.CornerLabelView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IdentityActivity_ViewBinding implements Unbinder {
  private IdentityActivity target;

  @UiThread
  public IdentityActivity_ViewBinding(IdentityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public IdentityActivity_ViewBinding(IdentityActivity target, View source) {
    this.target = target;

    target.item_daren_rl = Utils.findRequiredViewAsType(source, R.id.item_daren_rl, "field 'item_daren_rl'", RelativeLayout.class);
    target.daren_clv = Utils.findRequiredViewAsType(source, R.id.daren_clv, "field 'daren_clv'", CornerLabelView.class);
    target.daren_dy_iv = Utils.findRequiredViewAsType(source, R.id.daren_dy_iv, "field 'daren_dy_iv'", ImageView.class);
    target.daren_py_iv = Utils.findRequiredViewAsType(source, R.id.daren_py_iv, "field 'daren_py_iv'", ImageView.class);
    target.daren_wb_iv = Utils.findRequiredViewAsType(source, R.id.daren_wb_iv, "field 'daren_wb_iv'", ImageView.class);
    target.daren_ws_iv = Utils.findRequiredViewAsType(source, R.id.daren_ws_iv, "field 'daren_ws_iv'", ImageView.class);
    target.daren_other_iv = Utils.findRequiredViewAsType(source, R.id.daren_other_iv, "field 'daren_other_iv'", ImageView.class);
    target.item_guangao_rl = Utils.findRequiredViewAsType(source, R.id.item_guangao_rl, "field 'item_guangao_rl'", RelativeLayout.class);
    target.guangao_clv = Utils.findRequiredViewAsType(source, R.id.guangao_clv, "field 'guangao_clv'", CornerLabelView.class);
    target.item_mingren_rl = Utils.findRequiredViewAsType(source, R.id.item_mingren_rl, "field 'item_mingren_rl'", RelativeLayout.class);
    target.mingren_clv = Utils.findRequiredViewAsType(source, R.id.mingren_clv, "field 'mingren_clv'", CornerLabelView.class);
    target.item_meiti_rl = Utils.findRequiredViewAsType(source, R.id.item_meiti_rl, "field 'item_meiti_rl'", RelativeLayout.class);
    target.meiti_clv = Utils.findRequiredViewAsType(source, R.id.meiti_clv, "field 'meiti_clv'", CornerLabelView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IdentityActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.item_daren_rl = null;
    target.daren_clv = null;
    target.daren_dy_iv = null;
    target.daren_py_iv = null;
    target.daren_wb_iv = null;
    target.daren_ws_iv = null;
    target.daren_other_iv = null;
    target.item_guangao_rl = null;
    target.guangao_clv = null;
    target.item_mingren_rl = null;
    target.mingren_clv = null;
    target.item_meiti_rl = null;
    target.meiti_clv = null;
  }
}
