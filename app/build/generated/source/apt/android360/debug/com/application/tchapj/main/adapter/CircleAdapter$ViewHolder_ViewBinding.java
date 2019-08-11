// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CircleAdapter$ViewHolder_ViewBinding implements Unbinder {
  private CircleAdapter.ViewHolder target;

  @UiThread
  public CircleAdapter$ViewHolder_ViewBinding(CircleAdapter.ViewHolder target, View source) {
    this.target = target;

    target.item_circle = Utils.findRequiredViewAsType(source, R.id.item_circle, "field 'item_circle'", RelativeLayout.class);
    target.circle_iv_head = Utils.findRequiredViewAsType(source, R.id.circle_iv_head, "field 'circle_iv_head'", CircleImageView.class);
    target.circle_tv_name = Utils.findRequiredViewAsType(source, R.id.circle_tv_name, "field 'circle_tv_name'", TextView.class);
    target.circle_ic_type = Utils.findRequiredViewAsType(source, R.id.circle_ic_type, "field 'circle_ic_type'", ImageView.class);
    target.circle_ic_type2 = Utils.findRequiredViewAsType(source, R.id.circle_ic_type2, "field 'circle_ic_type2'", ImageView.class);
    target.circle_tv_explain = Utils.findRequiredViewAsType(source, R.id.circle_tv_explain, "field 'circle_tv_explain'", TextView.class);
    target.dy_iv = Utils.findRequiredViewAsType(source, R.id.dy_iv, "field 'dy_iv'", ImageView.class);
    target.py_iv = Utils.findRequiredViewAsType(source, R.id.py_iv, "field 'py_iv'", ImageView.class);
    target.wb_iv = Utils.findRequiredViewAsType(source, R.id.wb_iv, "field 'wb_iv'", ImageView.class);
    target.tencent_video = Utils.findRequiredViewAsType(source, R.id.tencent_video, "field 'tencent_video'", ImageView.class);
    target.other_iv = Utils.findRequiredViewAsType(source, R.id.other_iv, "field 'other_iv'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CircleAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.item_circle = null;
    target.circle_iv_head = null;
    target.circle_tv_name = null;
    target.circle_ic_type = null;
    target.circle_ic_type2 = null;
    target.circle_tv_explain = null;
    target.dy_iv = null;
    target.py_iv = null;
    target.wb_iv = null;
    target.tencent_video = null;
    target.other_iv = null;
  }
}
