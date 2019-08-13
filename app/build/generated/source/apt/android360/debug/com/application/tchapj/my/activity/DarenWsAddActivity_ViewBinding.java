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

public class DarenWsAddActivity_ViewBinding implements Unbinder {
  private DarenWsAddActivity target;

  private View view2131755810;

  @UiThread
  public DarenWsAddActivity_ViewBinding(DarenWsAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DarenWsAddActivity_ViewBinding(final DarenWsAddActivity target, View source) {
    this.target = target;

    View view;
    target.ws_add_bjet = Utils.findRequiredViewAsType(source, R.id.ws_add_bjet, "field 'ws_add_bjet'", EditText.class);
    target.ws_add_rv = Utils.findRequiredViewAsType(source, R.id.ws_add_rv, "field 'ws_add_rv'", RecyclerView.class);
    target.ws_add_bt = Utils.findRequiredViewAsType(source, R.id.ws_add_bt, "field 'ws_add_bt'", Button.class);
    view = Utils.findRequiredView(source, R.id.ws_add_example_tv, "method 'onViewClicked'");
    view2131755810 = view;
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
    DarenWsAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ws_add_bjet = null;
    target.ws_add_rv = null;
    target.ws_add_bt = null;

    view2131755810.setOnClickListener(null);
    view2131755810 = null;
  }
}
