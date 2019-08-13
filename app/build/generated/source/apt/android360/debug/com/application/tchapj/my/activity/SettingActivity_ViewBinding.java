// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    this.target = target;

    target.setting_mima_tv = Utils.findRequiredViewAsType(source, R.id.setting_mima_tv, "field 'setting_mima_tv'", TextView.class);
    target.mTvExit = Utils.findRequiredViewAsType(source, R.id.setting_tv_exit, "field 'mTvExit'", Button.class);
    target.versionNameTv = Utils.findRequiredViewAsType(source, R.id.versionName_tv, "field 'versionNameTv'", TextView.class);
    target.qqTv = Utils.findRequiredViewAsType(source, R.id.qq_tv, "field 'qqTv'", TextView.class);
    target.weTv = Utils.findRequiredViewAsType(source, R.id.wechat_tv, "field 'weTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.setting_mima_tv = null;
    target.mTvExit = null;
    target.versionNameTv = null;
    target.qqTv = null;
    target.weTv = null;
  }
}
