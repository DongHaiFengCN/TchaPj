// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.login.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhoneLogonFragment_ViewBinding implements Unbinder {
  private PhoneLogonFragment target;

  @UiThread
  public PhoneLogonFragment_ViewBinding(PhoneLogonFragment target, View source) {
    this.target = target;

    target.register_edt_userName = Utils.findRequiredViewAsType(source, R.id.register_edt_userName, "field 'register_edt_userName'", EditText.class);
    target.register_edt_smsCode = Utils.findRequiredViewAsType(source, R.id.register_edt_smsCode, "field 'register_edt_smsCode'", EditText.class);
    target.register_btn_getSmsCode = Utils.findRequiredViewAsType(source, R.id.register_btn_getSmsCode, "field 'register_btn_getSmsCode'", TextView.class);
    target.login_cb_remember = Utils.findRequiredViewAsType(source, R.id.login_cb_remember, "field 'login_cb_remember'", CheckBox.class);
    target.register_btn_signUp = Utils.findRequiredViewAsType(source, R.id.register_btn_signUp, "field 'register_btn_signUp'", Button.class);
    target.ivWxLogin = Utils.findRequiredViewAsType(source, R.id.iv_wx_login, "field 'ivWxLogin'", ImageView.class);
    target.ivQqLogin = Utils.findRequiredViewAsType(source, R.id.iv_qq_login, "field 'ivQqLogin'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhoneLogonFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.register_edt_userName = null;
    target.register_edt_smsCode = null;
    target.register_btn_getSmsCode = null;
    target.login_cb_remember = null;
    target.register_btn_signUp = null;
    target.ivWxLogin = null;
    target.ivQqLogin = null;
  }
}
