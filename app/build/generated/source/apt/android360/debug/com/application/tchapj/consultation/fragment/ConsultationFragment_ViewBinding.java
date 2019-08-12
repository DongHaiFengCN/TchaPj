// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConsultationFragment_ViewBinding implements Unbinder {
  private ConsultationFragment target;

  private View view2131755903;

  @UiThread
  public ConsultationFragment_ViewBinding(final ConsultationFragment target, View source) {
    this.target = target;

    View view;
    target.iv_operation = Utils.findRequiredViewAsType(source, R.id.iv_operation, "field 'iv_operation'", ImageView.class);
    target.tabs = Utils.findRequiredViewAsType(source, R.id.tabs, "field 'tabs'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.iv_edit_consultation, "method 'onViewClicked'");
    view2131755903 = view;
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
    ConsultationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_operation = null;
    target.tabs = null;
    target.viewpager = null;

    view2131755903.setOnClickListener(null);
    view2131755903 = null;
  }
}
