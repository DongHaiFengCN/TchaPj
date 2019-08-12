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

public class DarenDyAddActivity_ViewBinding implements Unbinder {
  private DarenDyAddActivity target;

  private View view2131755393;

  @UiThread
  public DarenDyAddActivity_ViewBinding(DarenDyAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DarenDyAddActivity_ViewBinding(final DarenDyAddActivity target, View source) {
    this.target = target;

    View view;
    target.dy_add_fset = Utils.findRequiredViewAsType(source, R.id.dy_add_fset, "field 'dy_add_fset'", EditText.class);
    target.dy_add_ncet = Utils.findRequiredViewAsType(source, R.id.dy_add_ncet, "field 'dy_add_ncet'", EditText.class);
    target.dy_add_blet = Utils.findRequiredViewAsType(source, R.id.dy_add_blet, "field 'dy_add_blet'", EditText.class);
    target.dy_add_plet = Utils.findRequiredViewAsType(source, R.id.dy_add_plet, "field 'dy_add_plet'", EditText.class);
    target.dy_add_zlet = Utils.findRequiredViewAsType(source, R.id.dy_add_zlet, "field 'dy_add_zlet'", EditText.class);
    target.dy_add_et = Utils.findRequiredViewAsType(source, R.id.dy_add_et, "field 'dy_add_et'", EditText.class);
    target.dy_add_rv = Utils.findRequiredViewAsType(source, R.id.dy_add_rv, "field 'dy_add_rv'", RecyclerView.class);
    target.dy_add_tv = Utils.findRequiredViewAsType(source, R.id.dy_add_tv, "field 'dy_add_tv'", Button.class);
    view = Utils.findRequiredView(source, R.id.dy_add_example_tv, "method 'onViewClicked'");
    view2131755393 = view;
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
    DarenDyAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dy_add_fset = null;
    target.dy_add_ncet = null;
    target.dy_add_blet = null;
    target.dy_add_plet = null;
    target.dy_add_zlet = null;
    target.dy_add_et = null;
    target.dy_add_rv = null;
    target.dy_add_tv = null;

    view2131755393.setOnClickListener(null);
    view2131755393 = null;
  }
}
