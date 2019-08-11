// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DarenActivity_ViewBinding implements Unbinder {
  private DarenActivity target;

  @UiThread
  public DarenActivity_ViewBinding(DarenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DarenActivity_ViewBinding(DarenActivity target, View source) {
    this.target = target;

    target.daren_wanshan_tv = Utils.findRequiredViewAsType(source, R.id.daren_wanshan_tv, "field 'daren_wanshan_tv'", TextView.class);
    target.daren_dyadd_ll = Utils.findRequiredViewAsType(source, R.id.daren_dyadd_ll, "field 'daren_dyadd_ll'", LinearLayout.class);
    target.daren_pyadd_ll = Utils.findRequiredViewAsType(source, R.id.daren_pyadd_ll, "field 'daren_pyadd_ll'", LinearLayout.class);
    target.daren_wbadd_ll = Utils.findRequiredViewAsType(source, R.id.daren_wbadd_ll, "field 'daren_wbadd_ll'", LinearLayout.class);
    target.daren_wsadd_ll = Utils.findRequiredViewAsType(source, R.id.daren_wsadd_ll, "field 'daren_wsadd_ll'", LinearLayout.class);
    target.daren_otheradd_ll = Utils.findRequiredViewAsType(source, R.id.daren_otheradd_ll, "field 'daren_otheradd_ll'", LinearLayout.class);
    target.mediaResourcesWechatIv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_wechat_iv, "field 'mediaResourcesWechatIv'", ImageView.class);
    target.mediaResourcesWbIv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_wb_iv, "field 'mediaResourcesWbIv'", ImageView.class);
    target.mediaResourcesDyIv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_dy_iv, "field 'mediaResourcesDyIv'", ImageView.class);
    target.mediaResourcesWsIv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_ws_iv, "field 'mediaResourcesWsIv'", ImageView.class);
    target.mediaResourcesOtherIv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_other_iv, "field 'mediaResourcesOtherIv'", ImageView.class);
    target.mediaResourcesStateWechatTv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_state_wechat_tv, "field 'mediaResourcesStateWechatTv'", TextView.class);
    target.mediaResourcesStateWbTv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_state_wb_tv, "field 'mediaResourcesStateWbTv'", TextView.class);
    target.mediaResourcesStateDyTv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_state_dy_tv, "field 'mediaResourcesStateDyTv'", TextView.class);
    target.mediaResourcesStateWsTv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_state_ws_tv, "field 'mediaResourcesStateWsTv'", TextView.class);
    target.mediaResourcesStateOtherTv = Utils.findRequiredViewAsType(source, R.id.daren_media_resources_state_other_tv, "field 'mediaResourcesStateOtherTv'", TextView.class);
    target.add_tvdy = Utils.findRequiredViewAsType(source, R.id.add_tvdy, "field 'add_tvdy'", TextView.class);
    target.add_tvpyq = Utils.findRequiredViewAsType(source, R.id.add_tvpyq, "field 'add_tvpyq'", TextView.class);
    target.add_tvwb = Utils.findRequiredViewAsType(source, R.id.add_tvwb, "field 'add_tvwb'", TextView.class);
    target.add_tvws = Utils.findRequiredViewAsType(source, R.id.add_tvws, "field 'add_tvws'", TextView.class);
    target.add_tv_other = Utils.findRequiredViewAsType(source, R.id.add_tv_other, "field 'add_tv_other'", TextView.class);
    target.refuseTv = Utils.findRequiredViewAsType(source, R.id.daren_refuse_tv, "field 'refuseTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DarenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.daren_wanshan_tv = null;
    target.daren_dyadd_ll = null;
    target.daren_pyadd_ll = null;
    target.daren_wbadd_ll = null;
    target.daren_wsadd_ll = null;
    target.daren_otheradd_ll = null;
    target.mediaResourcesWechatIv = null;
    target.mediaResourcesWbIv = null;
    target.mediaResourcesDyIv = null;
    target.mediaResourcesWsIv = null;
    target.mediaResourcesOtherIv = null;
    target.mediaResourcesStateWechatTv = null;
    target.mediaResourcesStateWbTv = null;
    target.mediaResourcesStateDyTv = null;
    target.mediaResourcesStateWsTv = null;
    target.mediaResourcesStateOtherTv = null;
    target.add_tvdy = null;
    target.add_tvpyq = null;
    target.add_tvwb = null;
    target.add_tvws = null;
    target.add_tv_other = null;
    target.refuseTv = null;
  }
}
