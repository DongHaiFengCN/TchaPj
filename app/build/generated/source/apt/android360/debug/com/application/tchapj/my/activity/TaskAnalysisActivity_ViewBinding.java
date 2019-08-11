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

public class TaskAnalysisActivity_ViewBinding implements Unbinder {
  private TaskAnalysisActivity target;

  @UiThread
  public TaskAnalysisActivity_ViewBinding(TaskAnalysisActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskAnalysisActivity_ViewBinding(TaskAnalysisActivity target, View source) {
    this.target = target;

    target.tabs = Utils.findRequiredViewAsType(source, R.id.content_manager_tabs, "field 'tabs'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.content_manager_viewpager, "field 'viewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskAnalysisActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabs = null;
    target.viewpager = null;
  }
}
