// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeCircleAdapter$ViewHolder_ViewBinding implements Unbinder {
  private HomeCircleAdapter.ViewHolder target;

  @UiThread
  public HomeCircleAdapter$ViewHolder_ViewBinding(HomeCircleAdapter.ViewHolder target, View source) {
    this.target = target;

    target.ll_circle = Utils.findRequiredViewAsType(source, R.id.ll_circle, "field 'll_circle'", LinearLayout.class);
    target.iv_circle_head = Utils.findRequiredViewAsType(source, R.id.iv_circle_head, "field 'iv_circle_head'", CircleImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeCircleAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ll_circle = null;
    target.iv_circle_head = null;
  }
}
