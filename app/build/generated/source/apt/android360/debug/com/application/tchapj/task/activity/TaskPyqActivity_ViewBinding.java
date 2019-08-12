// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskPyqActivity_ViewBinding implements Unbinder {
  private TaskPyqActivity target;

  private View view2131756487;

  @UiThread
  public TaskPyqActivity_ViewBinding(TaskPyqActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskPyqActivity_ViewBinding(final TaskPyqActivity target, View source) {
    this.target = target;

    View view;
    target.fa_pyq_imagerv = Utils.findRequiredViewAsType(source, R.id.fa_pyq_imagerv, "field 'fa_pyq_imagerv'", RecyclerView.class);
    target.fa_pyq_titlet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_titlet, "field 'fa_pyq_titlet'", EditText.class);
    target.fa_pyq_start = Utils.findRequiredViewAsType(source, R.id.fa_pyq_start, "field 'fa_pyq_start'", TextView.class);
    target.fa_pyq_end = Utils.findRequiredViewAsType(source, R.id.fa_pyq_end, "field 'fa_pyq_end'", TextView.class);
    target.fa_pyq_fl = Utils.findRequiredViewAsType(source, R.id.fa_pyq_fl, "field 'fa_pyq_fl'", FlowTagLayout.class);
    target.top_pyqgroup = Utils.findRequiredViewAsType(source, R.id.top_pyqgroup, "field 'top_pyqgroup'", RadioGroup.class);
    target.top_pyqlj_task = Utils.findRequiredViewAsType(source, R.id.top_pyqlj_task, "field 'top_pyqlj_task'", RadioButton.class);
    target.top_pyqtw_task = Utils.findRequiredViewAsType(source, R.id.top_pyqtw_task, "field 'top_pyqtw_task'", RadioButton.class);
    target.fa_pyqlj_ll = Utils.findRequiredViewAsType(source, R.id.fa_pyqlj_ll, "field 'fa_pyqlj_ll'", LinearLayout.class);
    target.fa_pyqtw_ll = Utils.findRequiredViewAsType(source, R.id.fa_pyqtw_ll, "field 'fa_pyqtw_ll'", LinearLayout.class);
    target.fa_pyq_ydet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_ydet, "field 'fa_pyq_ydet'", EditText.class);
    target.fa_pyq_zlet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_zlet, "field 'fa_pyq_zlet'", EditText.class);
    target.fa_pyq_waet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_waet, "field 'fa_pyq_waet'", EditText.class);
    target.fa_pyqlj_image_nanerv = Utils.findRequiredViewAsType(source, R.id.fa_pyqlj_image_nanerv, "field 'fa_pyqlj_image_nanerv'", RecyclerView.class);
    target.fa_pyqlj_bt = Utils.findRequiredViewAsType(source, R.id.fa_pyqlj_bt, "field 'fa_pyqlj_bt'", EditText.class);
    target.fa_pyqlj_dz = Utils.findRequiredViewAsType(source, R.id.fa_pyqlj_dz, "field 'fa_pyqlj_dz'", EditText.class);
    target.fa_pyqlj_djet = Utils.findRequiredViewAsType(source, R.id.fa_pyqlj_djet, "field 'fa_pyqlj_djet'", EditText.class);
    target.fa_pyqlj_slet = Utils.findRequiredViewAsType(source, R.id.fa_pyqlj_slet, "field 'fa_pyqlj_slet'", EditText.class);
    target.fa_pyq_image_nanerv = Utils.findRequiredViewAsType(source, R.id.fa_pyq_image_nanerv, "field 'fa_pyq_image_nanerv'", RecyclerView.class);
    target.fa_pyq_djet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_djet, "field 'fa_pyq_djet'", EditText.class);
    target.fa_pyq_slet = Utils.findRequiredViewAsType(source, R.id.fa_pyq_slet, "field 'fa_pyq_slet'", EditText.class);
    target.fa_pyq_fanset = Utils.findRequiredViewAsType(source, R.id.fa_pyq_fanset, "field 'fa_pyq_fanset'", EditText.class);
    target.fa_pyq_price_tv = Utils.findRequiredViewAsType(source, R.id.fa_pyq_price_tv, "field 'fa_pyq_price_tv'", TextView.class);
    target.fa_pyq_bt = Utils.findRequiredViewAsType(source, R.id.fa_pyq_bt, "field 'fa_pyq_bt'", Button.class);
    target.fa_pyqlj_ydet = Utils.findRequiredViewAsType(source, R.id.fa_pyqlj_ydet, "field 'fa_pyqlj_ydet'", EditText.class);
    view = Utils.findRequiredView(source, R.id.toolbar_menu_title, "field 'toolbarRightTv' and method 'onViewClicked'");
    target.toolbarRightTv = Utils.castView(view, R.id.toolbar_menu_title, "field 'toolbarRightTv'", TextView.class);
    view2131756487 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.originalPriceTv = Utils.findRequiredViewAsType(source, R.id.fa_pyqtask_original_price_tv, "field 'originalPriceTv'", TextView.class);
    target.discountTv = Utils.findRequiredViewAsType(source, R.id.fa_pyqtask_discount_tv, "field 'discountTv'", TextView.class);
    target.discountTipTv = Utils.findRequiredViewAsType(source, R.id.fa_pyqtask_discount_tip_tv, "field 'discountTipTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskPyqActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fa_pyq_imagerv = null;
    target.fa_pyq_titlet = null;
    target.fa_pyq_start = null;
    target.fa_pyq_end = null;
    target.fa_pyq_fl = null;
    target.top_pyqgroup = null;
    target.top_pyqlj_task = null;
    target.top_pyqtw_task = null;
    target.fa_pyqlj_ll = null;
    target.fa_pyqtw_ll = null;
    target.fa_pyq_ydet = null;
    target.fa_pyq_zlet = null;
    target.fa_pyq_waet = null;
    target.fa_pyqlj_image_nanerv = null;
    target.fa_pyqlj_bt = null;
    target.fa_pyqlj_dz = null;
    target.fa_pyqlj_djet = null;
    target.fa_pyqlj_slet = null;
    target.fa_pyq_image_nanerv = null;
    target.fa_pyq_djet = null;
    target.fa_pyq_slet = null;
    target.fa_pyq_fanset = null;
    target.fa_pyq_price_tv = null;
    target.fa_pyq_bt = null;
    target.fa_pyqlj_ydet = null;
    target.toolbarRightTv = null;
    target.originalPriceTv = null;
    target.discountTv = null;
    target.discountTipTv = null;

    view2131756487.setOnClickListener(null);
    view2131756487 = null;
  }
}
