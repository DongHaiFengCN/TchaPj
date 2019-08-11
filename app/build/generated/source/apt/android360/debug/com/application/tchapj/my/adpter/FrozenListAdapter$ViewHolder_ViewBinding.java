// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.adpter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FrozenListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private FrozenListAdapter.ViewHolder target;

  @UiThread
  public FrozenListAdapter$ViewHolder_ViewBinding(FrozenListAdapter.ViewHolder target, View source) {
    this.target = target;

    target.taskNameTv = Utils.findRequiredViewAsType(source, R.id.item_frozen_list_task_name, "field 'taskNameTv'", TextView.class);
    target.taskStateTv = Utils.findRequiredViewAsType(source, R.id.item_frozen_list_task_state, "field 'taskStateTv'", TextView.class);
    target.taskActionTv = Utils.findRequiredViewAsType(source, R.id.item_frozen_list_task_action, "field 'taskActionTv'", TextView.class);
    target.taskMoneyTv = Utils.findRequiredViewAsType(source, R.id.item_frozen_list_task_money, "field 'taskMoneyTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FrozenListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.taskNameTv = null;
    target.taskStateTv = null;
    target.taskActionTv = null;
    target.taskMoneyTv = null;
  }
}
