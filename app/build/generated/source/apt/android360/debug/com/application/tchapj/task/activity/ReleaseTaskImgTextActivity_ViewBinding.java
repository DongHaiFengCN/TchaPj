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
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReleaseTaskImgTextActivity_ViewBinding implements Unbinder {
  private ReleaseTaskImgTextActivity target;

  private View view2131756487;

  private View view2131755728;

  @UiThread
  public ReleaseTaskImgTextActivity_ViewBinding(ReleaseTaskImgTextActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReleaseTaskImgTextActivity_ViewBinding(final ReleaseTaskImgTextActivity target, View source) {
    this.target = target;

    View view;
    target.fa_pyq_imagerv = Utils.findRequiredViewAsType(source, R.id.fa_pyq_imagerv, "field 'fa_pyq_imagerv'", RecyclerView.class);
    target.fa_pyq_titlet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_titlet, "field 'fa_pyq_titlet'", EditText.class);
    target.fa_pyq_start = Utils.findRequiredViewAsType(source, R.id.fa_pyq_start, "field 'fa_pyq_start'", TextView.class);
    target.fa_pyq_end = Utils.findRequiredViewAsType(source, R.id.fa_pyq_end, "field 'fa_pyq_end'", TextView.class);
    view = Utils.findRequiredView(source, R.id.toolbar_menu_title, "field 'toolbarRightTv' and method 'onViewClicked'");
    target.toolbarRightTv = Utils.castView(view, R.id.toolbar_menu_title, "field 'toolbarRightTv'", TextView.class);
    view2131756487 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.fa_pyq_ydet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_ydet, "field 'fa_pyq_ydet'", EditText.class);
    target.taskRequireTv = Utils.findRequiredViewAsType(source, R.id.release_task_img_text_task_require, "field 'taskRequireTv'", EditText.class);
    target.fa_pyq_waet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_waet, "field 'fa_pyq_waet'", EditText.class);
    target.selectChannelTagLayout = Utils.findRequiredViewAsType(source, R.id.release_task_img_text_select_channel, "field 'selectChannelTagLayout'", FlowTagLayout.class);
    target.fa_pyq_image_nanerv = Utils.findRequiredViewAsType(source, R.id.fa_pyq_image_nanerv, "field 'fa_pyq_image_nanerv'", RecyclerView.class);
    target.fa_pyq_djet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_djet, "field 'fa_pyq_djet'", EditText.class);
    target.fa_pyq_slet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_slet, "field 'fa_pyq_slet'", EditText.class);
    target.fa_pyq_price_tv = Utils.findRequiredViewAsType(source, R.id.fa_pyq_price_tv, "field 'fa_pyq_price_tv'", TextView.class);
    target.fa_pyq_bt = Utils.findRequiredViewAsType(source, R.id.fa_pyq_bt, "field 'fa_pyq_bt'", Button.class);
    target.originalPriceTv = Utils.findRequiredViewAsType(source, R.id.fa_pyqtask_original_price_tv, "field 'originalPriceTv'", TextView.class);
    target.discountTv = Utils.findRequiredViewAsType(source, R.id.fa_pyqtask_discount_tv, "field 'discountTv'", TextView.class);
    target.titleTv = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'titleTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.release_task_img_text_preview_task_tv, "method 'onViewClicked'");
    view2131755728 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ReleaseTaskImgTextActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fa_pyq_imagerv = null;
    target.fa_pyq_titlet = null;
    target.fa_pyq_start = null;
    target.fa_pyq_end = null;
    target.toolbarRightTv = null;
    target.fa_pyq_ydet = null;
    target.taskRequireTv = null;
    target.fa_pyq_waet = null;
    target.selectChannelTagLayout = null;
    target.fa_pyq_image_nanerv = null;
    target.fa_pyq_djet = null;
    target.fa_pyq_slet = null;
    target.fa_pyq_price_tv = null;
    target.fa_pyq_bt = null;
    target.originalPriceTv = null;
    target.discountTv = null;
    target.titleTv = null;

    view2131756487.setOnClickListener(null);
    view2131756487 = null;
    view2131755728.setOnClickListener(null);
    view2131755728 = null;
  }
}
