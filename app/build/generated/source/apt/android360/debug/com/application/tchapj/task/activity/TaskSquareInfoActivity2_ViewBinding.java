// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskSquareInfoActivity2_ViewBinding implements Unbinder {
  private TaskSquareInfoActivity2 target;

  @UiThread
  public TaskSquareInfoActivity2_ViewBinding(TaskSquareInfoActivity2 target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskSquareInfoActivity2_ViewBinding(TaskSquareInfoActivity2 target, View source) {
    this.target = target;

    target.task_squareinfo_iv2 = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_iv2, "field 'task_squareinfo_iv2'", ImageView.class);
    target.task_squareinfo_tv2 = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_tv2, "field 'task_squareinfo_tv2'", TextView.class);
    target.task_squareinfo_vip2 = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_vip2, "field 'task_squareinfo_vip2'", ImageView.class);
    target.task_squareinfo_tvip2 = Utils.findRequiredViewAsType(source, R.id.task_squareinfo_tvip2, "field 'task_squareinfo_tvip2'", TextView.class);
    target.start_time_tv2 = Utils.findRequiredViewAsType(source, R.id.start_time_tv2, "field 'start_time_tv2'", TextView.class);
    target.copy_info_et = Utils.findRequiredViewAsType(source, R.id.copy_info_et, "field 'copy_info_et'", EditText.class);
    target.crowd_fl2 = Utils.findRequiredViewAsType(source, R.id.crowd_fl2, "field 'crowd_fl2'", FlowTagLayout.class);
    target.leading_task_bt2 = Utils.findRequiredViewAsType(source, R.id.leading_task_bt2, "field 'leading_task_bt2'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskSquareInfoActivity2 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.task_squareinfo_iv2 = null;
    target.task_squareinfo_tv2 = null;
    target.task_squareinfo_vip2 = null;
    target.task_squareinfo_tvip2 = null;
    target.start_time_tv2 = null;
    target.copy_info_et = null;
    target.crowd_fl2 = null;
    target.leading_task_bt2 = null;
  }
}
