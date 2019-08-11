// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.login.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginMainActivity_ViewBinding implements Unbinder {
  private LoginMainActivity target;

  private View view2131755620;

  private View view2131755621;

  private View view2131755612;

  @UiThread
  public LoginMainActivity_ViewBinding(LoginMainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginMainActivity_ViewBinding(final LoginMainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.my_shouji_logon, "field 'my_shouji_logon' and method 'onClick'");
    target.my_shouji_logon = Utils.castView(view, R.id.my_shouji_logon, "field 'my_shouji_logon'", RadioButton.class);
    view2131755620 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.my_mima_logon, "field 'my_mima_logon' and method 'onClick'");
    target.my_mima_logon = Utils.castView(view, R.id.my_mima_logon, "field 'my_mima_logon'", RadioButton.class);
    view2131755621 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_main_back_iv, "method 'onClick'");
    view2131755612 = view;
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
    LoginMainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.my_shouji_logon = null;
    target.my_mima_logon = null;

    view2131755620.setOnClickListener(null);
    view2131755620 = null;
    view2131755621.setOnClickListener(null);
    view2131755621 = null;
    view2131755612.setOnClickListener(null);
    view2131755612 = null;
  }
}
