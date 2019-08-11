// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.poptabview.PopTabView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PersonActivity_ViewBinding implements Unbinder {
  private PersonActivity target;

  @UiThread
  public PersonActivity_ViewBinding(PersonActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PersonActivity_ViewBinding(PersonActivity target, View source) {
    this.target = target;

    target.toolbar_menu_iv = Utils.findRequiredViewAsType(source, R.id.toolbar_menu_iv, "field 'toolbar_menu_iv'", ImageView.class);
    target.popTabView = Utils.findRequiredViewAsType(source, R.id.expandpop, "field 'popTabView'", PopTabView.class);
    target.popTabView2 = Utils.findRequiredViewAsType(source, R.id.expandpop2, "field 'popTabView2'", PopTabView.class);
    target.ph_ll = Utils.findRequiredViewAsType(source, R.id.ph_ll, "field 'ph_ll'", LinearLayout.class);
    target.ll_hot_more = Utils.findRequiredViewAsType(source, R.id.ll_hot_more, "field 'll_hot_more'", LinearLayout.class);
    target.iv_hot1 = Utils.findRequiredViewAsType(source, R.id.iv_hot1, "field 'iv_hot1'", CircleImageView.class);
    target.tv_hot1 = Utils.findRequiredViewAsType(source, R.id.tv_hot1, "field 'tv_hot1'", TextView.class);
    target.tv_hot1_value = Utils.findRequiredViewAsType(source, R.id.tv_hot1_value, "field 'tv_hot1_value'", TextView.class);
    target.iv_hot2 = Utils.findRequiredViewAsType(source, R.id.iv_hot2, "field 'iv_hot2'", CircleImageView.class);
    target.tv_hot2 = Utils.findRequiredViewAsType(source, R.id.tv_hot2, "field 'tv_hot2'", TextView.class);
    target.tv_hot2_value = Utils.findRequiredViewAsType(source, R.id.tv_hot2_value, "field 'tv_hot2_value'", TextView.class);
    target.iv_hot3 = Utils.findRequiredViewAsType(source, R.id.iv_hot3, "field 'iv_hot3'", CircleImageView.class);
    target.tv_hot3 = Utils.findRequiredViewAsType(source, R.id.tv_hot3, "field 'tv_hot3'", TextView.class);
    target.tv_hot3_value = Utils.findRequiredViewAsType(source, R.id.tv_hot3_value, "field 'tv_hot3_value'", TextView.class);
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.mIvEmpty = Utils.findRequiredViewAsType(source, R.id.person_iv_empty, "field 'mIvEmpty'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PersonActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar_menu_iv = null;
    target.popTabView = null;
    target.popTabView2 = null;
    target.ph_ll = null;
    target.ll_hot_more = null;
    target.iv_hot1 = null;
    target.tv_hot1 = null;
    target.tv_hot1_value = null;
    target.iv_hot2 = null;
    target.tv_hot2 = null;
    target.tv_hot2_value = null;
    target.iv_hot3 = null;
    target.tv_hot3 = null;
    target.tv_hot3_value = null;
    target.recyclerview = null;
    target.refreshLayout = null;
    target.mIvEmpty = null;
  }
}
