// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskFaWsGpFragment_ViewBinding implements Unbinder {
  private TaskFaWsGpFragment target;

  @UiThread
  public TaskFaWsGpFragment_ViewBinding(TaskFaWsGpFragment target, View source) {
    this.target = target;

    target.fa_dygp_imagerv = Utils.findRequiredViewAsType(source, R.id.fa_dygp_imagerv, "field 'fa_dygp_imagerv'", RecyclerView.class);
    target.fa_dygp_titilet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_titilet, "field 'fa_dygp_titilet'", EditText.class);
    target.fa_dygp_start = Utils.findRequiredViewAsType(source, R.id.fa_dygp_start, "field 'fa_dygp_start'", TextView.class);
    target.fa_dygp_end = Utils.findRequiredViewAsType(source, R.id.fa_dygp_end, "field 'fa_dygp_end'", TextView.class);
    target.fa_dygp_ydet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_ydet, "field 'fa_dygp_ydet'", EditText.class);
    target.fa_dygp_dzet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_dzet, "field 'fa_dygp_dzet'", EditText.class);
    target.fa_dygp_plet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_plet, "field 'fa_dygp_plet'", EditText.class);
    target.fa_dygp_zfet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_zfet, "field 'fa_dygp_zfet'", EditText.class);
    target.fa_dygp_llet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_llet, "field 'fa_dygp_llet'", EditText.class);
    target.fa_dygp_vieorv = Utils.findRequiredViewAsType(source, R.id.fa_dygp_vieorv, "field 'fa_dygp_vieorv'", RecyclerView.class);
    target.fa_dygp_djet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_djet, "field 'fa_dygp_djet'", EditText.class);
    target.fa_dygp_slet = Utils.findRequiredViewAsType(source, R.id.fa_dygp_slet, "field 'fa_dygp_slet'", EditText.class);
    target.fa_dygp_fanset = Utils.findRequiredViewAsType(source, R.id.fa_dygp_fanset, "field 'fa_dygp_fanset'", EditText.class);
    target.fa_dygp_zjtv = Utils.findRequiredViewAsType(source, R.id.fa_dygp_zjtv, "field 'fa_dygp_zjtv'", TextView.class);
    target.fa_dygp_bt = Utils.findRequiredViewAsType(source, R.id.fa_dygp_bt, "field 'fa_dygp_bt'", Button.class);
    target.loadingFl = Utils.findRequiredViewAsType(source, R.id.layout_common_loading_framelayout, "field 'loadingFl'", FrameLayout.class);
    target.loadingIv = Utils.findRequiredViewAsType(source, R.id.layout_common_loading_iv, "field 'loadingIv'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskFaWsGpFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fa_dygp_imagerv = null;
    target.fa_dygp_titilet = null;
    target.fa_dygp_start = null;
    target.fa_dygp_end = null;
    target.fa_dygp_ydet = null;
    target.fa_dygp_dzet = null;
    target.fa_dygp_plet = null;
    target.fa_dygp_zfet = null;
    target.fa_dygp_llet = null;
    target.fa_dygp_vieorv = null;
    target.fa_dygp_djet = null;
    target.fa_dygp_slet = null;
    target.fa_dygp_fanset = null;
    target.fa_dygp_zjtv = null;
    target.fa_dygp_bt = null;
    target.loadingFl = null;
    target.loadingIv = null;
  }
}
