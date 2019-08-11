// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.poptabview.PopTabView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CircleActivity_ViewBinding implements Unbinder {
  private CircleActivity target;

  @UiThread
  public CircleActivity_ViewBinding(CircleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CircleActivity_ViewBinding(CircleActivity target, View source) {
    this.target = target;

    target.toolbar_menu_iv = Utils.findRequiredViewAsType(source, R.id.toolbar_menu_iv, "field 'toolbar_menu_iv'", ImageView.class);
    target.circle_popTabView = Utils.findRequiredViewAsType(source, R.id.circle_expandpop, "field 'circle_popTabView'", PopTabView.class);
    target.circle_popTabView2 = Utils.findRequiredViewAsType(source, R.id.circle_expandpop2, "field 'circle_popTabView2'", PopTabView.class);
    target.circle_recyclerview = Utils.findRequiredViewAsType(source, R.id.circle_recyclerview, "field 'circle_recyclerview'", RecyclerView.class);
    target.circle_refreshLayout = Utils.findRequiredViewAsType(source, R.id.circle_refreshLayout, "field 'circle_refreshLayout'", SmartRefreshLayout.class);
    target.mIvEmpty = Utils.findRequiredViewAsType(source, R.id.person_iv_empty, "field 'mIvEmpty'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CircleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar_menu_iv = null;
    target.circle_popTabView = null;
    target.circle_popTabView2 = null;
    target.circle_recyclerview = null;
    target.circle_refreshLayout = null;
    target.mIvEmpty = null;
  }
}
