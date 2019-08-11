// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DarenOtherAddActivity_ViewBinding implements Unbinder {
  private DarenOtherAddActivity target;

  @UiThread
  public DarenOtherAddActivity_ViewBinding(DarenOtherAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DarenOtherAddActivity_ViewBinding(DarenOtherAddActivity target, View source) {
    this.target = target;

    target.other_add_bjet = Utils.findRequiredViewAsType(source, R.id.other_add_bjet, "field 'other_add_bjet'", EditText.class);
    target.other_add_rv = Utils.findRequiredViewAsType(source, R.id.other_add_rv, "field 'other_add_rv'", RecyclerView.class);
    target.other_add_bt = Utils.findRequiredViewAsType(source, R.id.other_add_bt, "field 'other_add_bt'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DarenOtherAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.other_add_bjet = null;
    target.other_add_rv = null;
    target.other_add_bt = null;
  }
}
