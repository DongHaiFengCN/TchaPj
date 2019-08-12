// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskMyFragment_ViewBinding implements Unbinder {
  private TaskMyFragment target;

  private View view2131756013;

  @UiThread
  public TaskMyFragment_ViewBinding(final TaskMyFragment target, View source) {
    this.target = target;

    View view;
    target.top_rg_a = Utils.findRequiredViewAsType(source, R.id.top_rg_a, "field 'top_rg_a'", RadioButton.class);
    target.top_rg_b = Utils.findRequiredViewAsType(source, R.id.top_rg_b, "field 'top_rg_b'", RadioButton.class);
    target.task_square_ll_myfa = Utils.findRequiredViewAsType(source, R.id.task_square_ll_myfa, "field 'task_square_ll_myfa'", LinearLayout.class);
    target.task_square_ll_my = Utils.findRequiredViewAsType(source, R.id.task_square_ll_my, "field 'task_square_ll_my'", LinearLayout.class);
    target.taskSquareRvMyfa = Utils.findRequiredViewAsType(source, R.id.task_square_rv_myfa, "field 'taskSquareRvMyfa'", RecyclerView.class);
    target.taskSquareRlMyfa = Utils.findRequiredViewAsType(source, R.id.task_square_rl_myfa, "field 'taskSquareRlMyfa'", SmartRefreshLayout.class);
    target.taskSquareRvMy = Utils.findRequiredViewAsType(source, R.id.task_square_rv_my, "field 'taskSquareRvMy'", RecyclerView.class);
    target.taskSquareRlMy = Utils.findRequiredViewAsType(source, R.id.task_square_rl_my, "field 'taskSquareRlMy'", SmartRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.add_task_button, "method 'onViewClicked'");
    view2131756013 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskMyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.top_rg_a = null;
    target.top_rg_b = null;
    target.task_square_ll_myfa = null;
    target.task_square_ll_my = null;
    target.taskSquareRvMyfa = null;
    target.taskSquareRlMyfa = null;
    target.taskSquareRvMy = null;
    target.taskSquareRlMy = null;

    view2131756013.setOnClickListener(null);
    view2131756013 = null;
  }
}
