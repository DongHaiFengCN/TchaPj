// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AttentionListActivity_ViewBinding implements Unbinder {
  private AttentionListActivity target;

  @UiThread
  public AttentionListActivity_ViewBinding(AttentionListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AttentionListActivity_ViewBinding(AttentionListActivity target, View source) {
    this.target = target;

    target.tabs = Utils.findRequiredViewAsType(source, R.id.activity_attention_fan_tabs, "field 'tabs'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.activity_attention_fan_viewpager, "field 'viewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AttentionListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabs = null;
    target.viewpager = null;
  }
}
