// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
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

public class WhbyMemberActivity_ViewBinding implements Unbinder {
  private WhbyMemberActivity target;

  @UiThread
  public WhbyMemberActivity_ViewBinding(WhbyMemberActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WhbyMemberActivity_ViewBinding(WhbyMemberActivity target, View source) {
    this.target = target;

    target.touxiang_rl = Utils.findRequiredViewAsType(source, R.id.touxiang_rl, "field 'touxiang_rl'", RelativeLayout.class);
    target.nc_rl = Utils.findRequiredViewAsType(source, R.id.nc_rl, "field 'nc_rl'", RelativeLayout.class);
    target.diqu_rl = Utils.findRequiredViewAsType(source, R.id.diqu_rl, "field 'diqu_rl'", RelativeLayout.class);
    target.touxiang_iv = Utils.findRequiredViewAsType(source, R.id.touxiang_iv, "field 'touxiang_iv'", ImageView.class);
    target.nickNameTv = Utils.findRequiredViewAsType(source, R.id.nick_name_tv, "field 'nickNameTv'", TextView.class);
    target.qianmingTv = Utils.findRequiredViewAsType(source, R.id.qianming_tv, "field 'qianmingTv'", TextView.class);
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarMenuTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_menu_title, "field 'toolbarMenuTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.qianmingRl = Utils.findRequiredViewAsType(source, R.id.qianming_rl, "field 'qianmingRl'", RelativeLayout.class);
    target.diqu_tv = Utils.findRequiredViewAsType(source, R.id.diqu_tv, "field 'diqu_tv'", TextView.class);
    target.realNameTv = Utils.findRequiredViewAsType(source, R.id.real_name_tv, "field 'realNameTv'", TextView.class);
    target.realNameRl = Utils.findRequiredViewAsType(source, R.id.real_name_rl, "field 'realNameRl'", RelativeLayout.class);
    target.idNumberTv = Utils.findRequiredViewAsType(source, R.id.id_number_tv, "field 'idNumberTv'", TextView.class);
    target.idNumberRl = Utils.findRequiredViewAsType(source, R.id.id_number_rl, "field 'idNumberRl'", RelativeLayout.class);
    target.telephoneTv = Utils.findRequiredViewAsType(source, R.id.telephone_tv, "field 'telephoneTv'", TextView.class);
    target.telephoneRl = Utils.findRequiredViewAsType(source, R.id.telephone_rl, "field 'telephoneRl'", RelativeLayout.class);
    target.loadingIv = Utils.findRequiredViewAsType(source, R.id.activity_whby_member_loading_iv, "field 'loadingIv'", ImageView.class);
    target.loadingFl = Utils.findRequiredViewAsType(source, R.id.activity_whby_member_loading_fl, "field 'loadingFl'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WhbyMemberActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.touxiang_rl = null;
    target.nc_rl = null;
    target.diqu_rl = null;
    target.touxiang_iv = null;
    target.nickNameTv = null;
    target.qianmingTv = null;
    target.toolbarTitle = null;
    target.toolbarMenuTitle = null;
    target.toolbar = null;
    target.qianmingRl = null;
    target.diqu_tv = null;
    target.realNameTv = null;
    target.realNameRl = null;
    target.idNumberTv = null;
    target.idNumberRl = null;
    target.telephoneTv = null;
    target.telephoneRl = null;
    target.loadingIv = null;
    target.loadingFl = null;
  }
}
