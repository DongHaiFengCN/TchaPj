// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

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

public class TaskWbActivity_ViewBinding implements Unbinder {
  private TaskWbActivity target;

  @UiThread
  public TaskWbActivity_ViewBinding(TaskWbActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskWbActivity_ViewBinding(TaskWbActivity target, View source) {
    this.target = target;

    target.fa_wb_imagerv = Utils.findRequiredViewAsType(source, R.id.fa_wb_imagerv, "field 'fa_wb_imagerv'", RecyclerView.class);
    target.fa_wb_titlet = Utils.findRequiredViewAsType(source, R.id.fa_wb_titlet, "field 'fa_wb_titlet'", EditText.class);
    target.fa_wb_start = Utils.findRequiredViewAsType(source, R.id.fa_wb_start, "field 'fa_wb_start'", TextView.class);
    target.fa_wb_end = Utils.findRequiredViewAsType(source, R.id.fa_wb_end, "field 'fa_wb_end'", TextView.class);
    target.fa_wb_ydet = Utils.findRequiredViewAsType(source, R.id.fa_wb_ydet, "field 'fa_wb_ydet'", EditText.class);
    target.fa_wb_zlet = Utils.findRequiredViewAsType(source, R.id.fa_wb_zlet, "field 'fa_wb_zlet'", EditText.class);
    target.fa_wb_plet = Utils.findRequiredViewAsType(source, R.id.fa_wb_plet, "field 'fa_wb_plet'", EditText.class);
    target.fa_wb_zfet = Utils.findRequiredViewAsType(source, R.id.fa_wb_zfet, "field 'fa_wb_zfet'", EditText.class);
    target.fa_wb_waet = Utils.findRequiredViewAsType(source, R.id.fa_wb_waet, "field 'fa_wb_waet'", EditText.class);
    target.fa_wb_image_nanerv = Utils.findRequiredViewAsType(source, R.id.fa_wb_image_nanerv, "field 'fa_wb_image_nanerv'", RecyclerView.class);
    target.fa_wb_djet = Utils.findRequiredViewAsType(source, R.id.fa_wb_djet, "field 'fa_wb_djet'", EditText.class);
    target.fa_wb_slet = Utils.findRequiredViewAsType(source, R.id.fa_wb_slet, "field 'fa_wb_slet'", EditText.class);
    target.fa_wb_fanset = Utils.findRequiredViewAsType(source, R.id.fa_wb_fanset, "field 'fa_wb_fanset'", EditText.class);
    target.fa_wb_zjtv = Utils.findRequiredViewAsType(source, R.id.fa_wb_zjtv, "field 'fa_wb_zjtv'", TextView.class);
    target.fa_wb_bt = Utils.findRequiredViewAsType(source, R.id.fa_wb_bt, "field 'fa_wb_bt'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskWbActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fa_wb_imagerv = null;
    target.fa_wb_titlet = null;
    target.fa_wb_start = null;
    target.fa_wb_end = null;
    target.fa_wb_ydet = null;
    target.fa_wb_zlet = null;
    target.fa_wb_plet = null;
    target.fa_wb_zfet = null;
    target.fa_wb_waet = null;
    target.fa_wb_image_nanerv = null;
    target.fa_wb_djet = null;
    target.fa_wb_slet = null;
    target.fa_wb_fanset = null;
    target.fa_wb_zjtv = null;
    target.fa_wb_bt = null;
  }
}
