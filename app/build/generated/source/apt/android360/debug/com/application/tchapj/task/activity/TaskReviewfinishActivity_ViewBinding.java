// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskReviewfinishActivity_ViewBinding implements Unbinder {
  private TaskReviewfinishActivity target;

  @UiThread
  public TaskReviewfinishActivity_ViewBinding(TaskReviewfinishActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskReviewfinishActivity_ViewBinding(TaskReviewfinishActivity target, View source) {
    this.target = target;

    target.iv = Utils.findRequiredViewAsType(source, R.id.uploadAudioImg2, "field 'iv'", ImageView.class);
    target.failTv = Utils.findRequiredViewAsType(source, R.id.fail_tv, "field 'failTv'", TextView.class);
    target.successTv = Utils.findRequiredViewAsType(source, R.id.success_tv, "field 'successTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskReviewfinishActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv = null;
    target.failTv = null;
    target.successTv = null;
  }
}
