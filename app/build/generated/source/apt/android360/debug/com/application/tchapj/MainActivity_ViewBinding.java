// Generated code from Butter Knife. Do not modify!
package com.application.tchapj;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131755827;

  private View view2131755828;

  private View view2131755829;

  private View view2131755830;

  private View view2131755831;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rbHome, "field 'rbHome' and method 'onClick'");
    target.rbHome = Utils.castView(view, R.id.rbHome, "field 'rbHome'", RadioButton.class);
    view2131755827 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbConsultation, "field 'rbConsultation' and method 'onClick'");
    target.rbConsultation = Utils.castView(view, R.id.rbConsultation, "field 'rbConsultation'", RadioButton.class);
    view2131755828 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbVideo, "field 'rbVideo' and method 'onClick'");
    target.rbVideo = Utils.castView(view, R.id.rbVideo, "field 'rbVideo'", RadioButton.class);
    view2131755829 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbTask, "field 'rbTask' and method 'onClick'");
    target.rbTask = Utils.castView(view, R.id.rbTask, "field 'rbTask'", RadioButton.class);
    view2131755830 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbMy, "field 'rbMy' and method 'onClick'");
    target.rbMy = Utils.castView(view, R.id.rbMy, "field 'rbMy'", RadioButton.class);
    view2131755831 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rbHome = null;
    target.rbConsultation = null;
    target.rbVideo = null;
    target.rbTask = null;
    target.rbMy = null;

    view2131755827.setOnClickListener(null);
    view2131755827 = null;
    view2131755828.setOnClickListener(null);
    view2131755828 = null;
    view2131755829.setOnClickListener(null);
    view2131755829 = null;
    view2131755830.setOnClickListener(null);
    view2131755830 = null;
    view2131755831.setOnClickListener(null);
    view2131755831 = null;
  }
}
