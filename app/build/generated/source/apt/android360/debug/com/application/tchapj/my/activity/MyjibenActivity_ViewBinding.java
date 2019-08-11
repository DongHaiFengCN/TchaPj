// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyjibenActivity_ViewBinding implements Unbinder {
  private MyjibenActivity target;

  @UiThread
  public MyjibenActivity_ViewBinding(MyjibenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyjibenActivity_ViewBinding(MyjibenActivity target, View source) {
    this.target = target;

    target.touxiang_rl = Utils.findRequiredViewAsType(source, R.id.touxiang_rl, "field 'touxiang_rl'", RelativeLayout.class);
    target.nc_rl = Utils.findRequiredViewAsType(source, R.id.nc_rl, "field 'nc_rl'", RelativeLayout.class);
    target.xb_rl = Utils.findRequiredViewAsType(source, R.id.xb_rl, "field 'xb_rl'", RelativeLayout.class);
    target.diqu_rl = Utils.findRequiredViewAsType(source, R.id.diqu_rl, "field 'diqu_rl'", RelativeLayout.class);
    target.sr_rl = Utils.findRequiredViewAsType(source, R.id.sr_rl, "field 'sr_rl'", RelativeLayout.class);
    target.touxiang_iv = Utils.findRequiredViewAsType(source, R.id.touxiang_iv, "field 'touxiang_iv'", ImageView.class);
    target.nicheng_iv = Utils.findRequiredViewAsType(source, R.id.nicheng_iv, "field 'nicheng_iv'", TextView.class);
    target.xingbie_iv = Utils.findRequiredViewAsType(source, R.id.xingbie_iv, "field 'xingbie_iv'", TextView.class);
    target.diqu_iv = Utils.findRequiredViewAsType(source, R.id.diqu_iv, "field 'diqu_iv'", TextView.class);
    target.shengri_tv = Utils.findRequiredViewAsType(source, R.id.shengri_tv, "field 'shengri_tv'", TextView.class);
    target.alipay_rl = Utils.findRequiredViewAsType(source, R.id.alipay_rl, "field 'alipay_rl'", RelativeLayout.class);
    target.loadingFl = Utils.findRequiredViewAsType(source, R.id.layout_common_loading_framelayout, "field 'loadingFl'", FrameLayout.class);
    target.loadingIv = Utils.findRequiredViewAsType(source, R.id.layout_common_loading_iv, "field 'loadingIv'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyjibenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.touxiang_rl = null;
    target.nc_rl = null;
    target.xb_rl = null;
    target.diqu_rl = null;
    target.sr_rl = null;
    target.touxiang_iv = null;
    target.nicheng_iv = null;
    target.xingbie_iv = null;
    target.diqu_iv = null;
    target.shengri_tv = null;
    target.alipay_rl = null;
    target.loadingFl = null;
    target.loadingIv = null;
  }
}
