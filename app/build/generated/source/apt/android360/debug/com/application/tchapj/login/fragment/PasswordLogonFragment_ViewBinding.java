// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.login.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PasswordLogonFragment_ViewBinding implements Unbinder {
  private PasswordLogonFragment target;

  @UiThread
  public PasswordLogonFragment_ViewBinding(PasswordLogonFragment target, View source) {
    this.target = target;

    target.mEdtUserName = Utils.findRequiredViewAsType(source, R.id.login_edt_userName, "field 'mEdtUserName'", EditText.class);
    target.mEdtUserPass = Utils.findRequiredViewAsType(source, R.id.login_edt_userPass, "field 'mEdtUserPass'", EditText.class);
    target.mCbRemember = Utils.findRequiredViewAsType(source, R.id.login_cb_remember, "field 'mCbRemember'", CheckBox.class);
    target.mTvForget = Utils.findRequiredViewAsType(source, R.id.login_tv_forget, "field 'mTvForget'", TextView.class);
    target.mBtnSignIn = Utils.findRequiredViewAsType(source, R.id.login_btn_signIn, "field 'mBtnSignIn'", Button.class);
    target.mTvRegister = Utils.findRequiredViewAsType(source, R.id.login_tv_register, "field 'mTvRegister'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PasswordLogonFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEdtUserName = null;
    target.mEdtUserPass = null;
    target.mCbRemember = null;
    target.mTvForget = null;
    target.mBtnSignIn = null;
    target.mTvRegister = null;
  }
}
