// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.fragment;

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

public class TaskFragment_ViewBinding implements Unbinder {
  private TaskFragment target;

  @UiThread
  public TaskFragment_ViewBinding(TaskFragment target, View source) {
    this.target = target;

    target.top_radiogroup = Utils.findRequiredViewAsType(source, R.id.top_radiogroup, "field 'top_radiogroup'", RadioGroup.class);
    target.top_square_task = Utils.findRequiredViewAsType(source, R.id.top_square_task, "field 'top_square_task'", RadioButton.class);
    target.top_my_task = Utils.findRequiredViewAsType(source, R.id.top_my_task, "field 'top_my_task'", RadioButton.class);
    target.top_my_pager = Utils.findRequiredViewAsType(source, R.id.top_my_pager, "field 'top_my_pager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.top_radiogroup = null;
    target.top_square_task = null;
    target.top_my_task = null;
    target.top_my_pager = null;
  }
}
