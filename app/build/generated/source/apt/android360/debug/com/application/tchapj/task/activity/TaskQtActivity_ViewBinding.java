// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskQtActivity_ViewBinding implements Unbinder {
  private TaskQtActivity target;

  @UiThread
  public TaskQtActivity_ViewBinding(TaskQtActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskQtActivity_ViewBinding(TaskQtActivity target, View source) {
    this.target = target;

    target.fa_qt_et = Utils.findRequiredViewAsType(source, R.id.fa_qt_et, "field 'fa_qt_et'", EditText.class);
    target.fa_qt_bt = Utils.findRequiredViewAsType(source, R.id.fa_qt_bt, "field 'fa_qt_bt'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskQtActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fa_qt_et = null;
    target.fa_qt_bt = null;
  }
}
