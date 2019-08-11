// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskSquareInfoActivity_ViewBinding implements Unbinder {
  private TaskSquareInfoActivity target;

  @UiThread
  public TaskSquareInfoActivity_ViewBinding(TaskSquareInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskSquareInfoActivity_ViewBinding(TaskSquareInfoActivity target, View source) {
    this.target = target;

    target.task_squareinfo_iv = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_iv, "field 'task_squareinfo_iv'", ImageView.class);
    target.task_squareinfo_tv = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_tv, "field 'task_squareinfo_tv'", TextView.class);
    target.task_squareinfo_vip = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_vip, "field 'task_squareinfo_vip'", ImageView.class);
    target.task_squareinfo_tvip = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_tvip, "field 'task_squareinfo_tvip'", TextView.class);
    target.start_time_tv = Utils.findRequiredViewAsType(source, R.id.start_time_tv, "field 'start_time_tv'", TextView.class);
    target.start_end_tv = Utils.findRequiredViewAsType(source, R.id.start_end_tv, "field 'start_end_tv'", TextView.class);
    target.crowd_fl = Utils.findRequiredViewAsType(source, R.id.crowd_fl, "field 'crowd_fl'", FlowTagLayout.class);
    target.channel_tv = Utils.findRequiredViewAsType(source, R.id.channel_tv, "field 'channel_tv'", TextView.class);
    target.guidance_tv = Utils.findRequiredViewAsType(source, R.id.guidance_tv, "field 'guidance_tv'", TextView.class);
    target.requirements_tv = Utils.findRequiredViewAsType(source, R.id.requirements_tv, "field 'requirements_tv'", TextView.class);
    target.money_iv = Utils.findRequiredViewAsType(source, R.id.money_iv, "field 'money_iv'", ImageView.class);
    target.money_tv = Utils.findRequiredViewAsType(source, R.id.money_tv, "field 'money_tv'", TextView.class);
    target.conditions_tv = Utils.findRequiredViewAsType(source, R.id.conditions_tv, "field 'conditions_tv'", TextView.class);
    target.leading_task_bt = Utils.findRequiredViewAsType(source, R.id.leading_task_bt, "field 'leading_task_bt'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskSquareInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.task_squareinfo_iv = null;
    target.task_squareinfo_tv = null;
    target.task_squareinfo_vip = null;
    target.task_squareinfo_tvip = null;
    target.start_time_tv = null;
    target.start_end_tv = null;
    target.crowd_fl = null;
    target.channel_tv = null;
    target.guidance_tv = null;
    target.requirements_tv = null;
    target.money_iv = null;
    target.money_tv = null;
    target.conditions_tv = null;
    target.leading_task_bt = null;
  }
}
