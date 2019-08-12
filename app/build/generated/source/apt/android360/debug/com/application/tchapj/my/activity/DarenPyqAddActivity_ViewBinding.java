// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

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
import java.lang.IllegalStateException;
import java.lang.Override;

public class DarenPyqAddActivity_ViewBinding implements Unbinder {
  private DarenPyqAddActivity target;

  private View view2131755726;

  @UiThread
  public DarenPyqAddActivity_ViewBinding(DarenPyqAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DarenPyqAddActivity_ViewBinding(final DarenPyqAddActivity target, View source) {
    this.target = target;

    View view;
    target.pyq_add_ncet = Utils.findRequiredViewAsType(source, R.id.pyq_add_ncet, "field 'pyq_add_ncet'", EditText.class);
    target.pyq_add_hyet = Utils.findRequiredViewAsType(source, R.id.pyq_add_hyet, "field 'pyq_add_hyet'", EditText.class);
    target.pyq_add_dqtv = Utils.findRequiredViewAsType(source, R.id.pyq_add_dqtv, "field 'pyq_add_dqtv'", TextView.class);
    target.pyq_add_bj = Utils.findRequiredViewAsType(source, R.id.pyq_add_bj, "field 'pyq_add_bj'", EditText.class);
    target.pyq_add_image_rl = Utils.findRequiredViewAsType(source, R.id.pyq_add_image_rl, "field 'pyq_add_image_rl'", RecyclerView.class);
    target.pyq_add_vieo_rl = Utils.findRequiredViewAsType(source, R.id.pyq_add_vieo_rl, "field 'pyq_add_vieo_rl'", RecyclerView.class);
    target.pyq_add_bt = Utils.findRequiredViewAsType(source, R.id.pyq_add_bt, "field 'pyq_add_bt'", Button.class);
    view = Utils.findRequiredView(source, R.id.pyq_add_example_tv, "method 'onViewClicked'");
    view2131755726 = view;
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
    DarenPyqAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pyq_add_ncet = null;
    target.pyq_add_hyet = null;
    target.pyq_add_dqtv = null;
    target.pyq_add_bj = null;
    target.pyq_add_image_rl = null;
    target.pyq_add_vieo_rl = null;
    target.pyq_add_bt = null;

    view2131755726.setOnClickListener(null);
    view2131755726 = null;
  }
}
