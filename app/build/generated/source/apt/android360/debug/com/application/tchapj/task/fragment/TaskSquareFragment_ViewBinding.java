// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskSquareFragment_ViewBinding implements Unbinder {
  private TaskSquareFragment target;

  @UiThread
  public TaskSquareFragment_ViewBinding(TaskSquareFragment target, View source) {
    this.target = target;

    target.task_square_rl = Utils.findRequiredViewAsType(source, R.id.task_square_rl, "field 'task_square_rl'", SmartRefreshLayout.class);
    target.task_square_rv = Utils.findRequiredViewAsType(source, R.id.task_square_rv, "field 'task_square_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskSquareFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.task_square_rl = null;
    target.task_square_rv = null;
  }
}
