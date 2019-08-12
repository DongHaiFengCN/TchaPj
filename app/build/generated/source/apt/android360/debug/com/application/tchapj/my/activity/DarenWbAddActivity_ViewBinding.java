// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DarenWbAddActivity_ViewBinding implements Unbinder {
  private DarenWbAddActivity target;

  private View view2131755779;

  @UiThread
  public DarenWbAddActivity_ViewBinding(DarenWbAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DarenWbAddActivity_ViewBinding(final DarenWbAddActivity target, View source) {
    this.target = target;

    View view;
    target.wb_add_fset = Utils.findRequiredViewAsType(source, R.id.wb_add_fset, "field 'wb_add_fset'", EditText.class);
    target.wb_add_ncet = Utils.findRequiredViewAsType(source, R.id.wb_add_ncet, "field 'wb_add_ncet'", EditText.class);
    target.wb_add_bjet = Utils.findRequiredViewAsType(source, R.id.wb_add_bjet, "field 'wb_add_bjet'", EditText.class);
    target.wb_add_rv = Utils.findRequiredViewAsType(source, R.id.wb_add_rv, "field 'wb_add_rv'", RecyclerView.class);
    target.wb_add_bt = Utils.findRequiredViewAsType(source, R.id.wb_add_bt, "field 'wb_add_bt'", Button.class);
    view = Utils.findRequiredView(source, R.id.wb_add_example_tv, "method 'onViewClicked'");
    view2131755779 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DarenWbAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.wb_add_fset = null;
    target.wb_add_ncet = null;
    target.wb_add_bjet = null;
    target.wb_add_rv = null;
    target.wb_add_bt = null;

    view2131755779.setOnClickListener(null);
    view2131755779 = null;
  }
}
