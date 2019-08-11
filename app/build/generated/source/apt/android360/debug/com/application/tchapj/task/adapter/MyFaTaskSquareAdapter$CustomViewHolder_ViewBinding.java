// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyFaTaskSquareAdapter$CustomViewHolder_ViewBinding implements Unbinder {
  private MyFaTaskSquareAdapter.CustomViewHolder target;

  @UiThread
  public MyFaTaskSquareAdapter$CustomViewHolder_ViewBinding(MyFaTaskSquareAdapter.CustomViewHolder target, View source) {
    this.target = target;

    target.task_ll = Utils.findRequiredViewAsType(source, R.id.task_ll_my, "field 'task_ll'", LinearLayout.class);
    target.task_tob_iv = Utils.findRequiredViewAsType(source, R.id.task_tob_iv, "field 'task_tob_iv'", ImageView.class);
    target.task_tob_tv = Utils.findRequiredViewAsType(source, R.id.task_tob_tv, "field 'task_tob_tv'", TextView.class);
    target.task_tob_time = Utils.findRequiredViewAsType(source, R.id.task_tob_time, "field 'task_tob_time'", TextView.class);
    target.task_content_iv = Utils.findRequiredViewAsType(source, R.id.task_content_iv, "field 'task_content_iv'", ImageView.class);
    target.task_content_tv = Utils.findRequiredViewAsType(source, R.id.task_content_tv, "field 'task_content_tv'", TextView.class);
    target.task_content_vip = Utils.findRequiredViewAsType(source, R.id.task_content_vip, "field 'task_content_vip'", CircleImageView.class);
    target.task_content_tab1 = Utils.findRequiredViewAsType(source, R.id.task_content_tab1, "field 'task_content_tab1'", TextView.class);
    target.task_content_tab2 = Utils.findRequiredViewAsType(source, R.id.task_content_tab2, "field 'task_content_tab2'", TextView.class);
    target.task_content_frequency = Utils.findRequiredViewAsType(source, R.id.task_content_frequency, "field 'task_content_frequency'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyFaTaskSquareAdapter.CustomViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.task_ll = null;
    target.task_tob_iv = null;
    target.task_tob_tv = null;
    target.task_tob_time = null;
    target.task_content_iv = null;
    target.task_content_tv = null;
    target.task_content_vip = null;
    target.task_content_tab1 = null;
    target.task_content_tab2 = null;
    target.task_content_frequency = null;
  }
}
