// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskWsMainActivity_ViewBinding implements Unbinder {
  private TaskWsMainActivity target;

  @UiThread
  public TaskWsMainActivity_ViewBinding(TaskWsMainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskWsMainActivity_ViewBinding(TaskWsMainActivity target, View source) {
    this.target = target;

    target.top_dygroup = Utils.findRequiredViewAsType(source, R.id.top_dygroup, "field 'top_dygroup'", RadioGroup.class);
    target.top_dygp_task = Utils.findRequiredViewAsType(source, R.id.top_dygp_task, "field 'top_dygp_task'", RadioButton.class);
    target.top_dyyc_task = Utils.findRequiredViewAsType(source, R.id.top_dyyc_task, "field 'top_dyyc_task'", RadioButton.class);
    target.top_dy_pager = Utils.findRequiredViewAsType(source, R.id.top_dy_pager, "field 'top_dy_pager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskWsMainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.top_dygroup = null;
    target.top_dygp_task = null;
    target.top_dyyc_task = null;
    target.top_dy_pager = null;
  }
}
