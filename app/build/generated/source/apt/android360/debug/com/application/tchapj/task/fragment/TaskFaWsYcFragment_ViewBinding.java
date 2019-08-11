// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskFaWsYcFragment_ViewBinding implements Unbinder {
  private TaskFaWsYcFragment target;

  @UiThread
  public TaskFaWsYcFragment_ViewBinding(TaskFaWsYcFragment target, View source) {
    this.target = target;

    target.fa_dyyc_imagerv = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_imagerv, "field 'fa_dyyc_imagerv'", RecyclerView.class);
    target.fa_dyyc_titilet = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_titilet, "field 'fa_dyyc_titilet'", EditText.class);
    target.fa_dyyc_start = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_start, "field 'fa_dyyc_start'", TextView.class);
    target.fa_dyyc_end = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_end, "field 'fa_dyyc_end'", TextView.class);
    target.fa_dyyc_vieoet = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_vieoet, "field 'fa_dyyc_vieoet'", EditText.class);
    target.fa_dyyc_shouji = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_shouji, "field 'fa_dyyc_shouji'", EditText.class);
    target.fa_dyyc_jiagetv = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_jiagetv, "field 'fa_dyyc_jiagetv'", EditText.class);
    target.fa_dyyc_bt = Utils.findRequiredViewAsType(source, R.id.fa_dyyc_bt, "field 'fa_dyyc_bt'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskFaWsYcFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fa_dyyc_imagerv = null;
    target.fa_dyyc_titilet = null;
    target.fa_dyyc_start = null;
    target.fa_dyyc_end = null;
    target.fa_dyyc_vieoet = null;
    target.fa_dyyc_shouji = null;
    target.fa_dyyc_jiagetv = null;
    target.fa_dyyc_bt = null;
  }
}
