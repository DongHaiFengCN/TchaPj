// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AttentionListDataFragment_ViewBinding implements Unbinder {
  private AttentionListDataFragment target;

  @UiThread
  public AttentionListDataFragment_ViewBinding(AttentionListDataFragment target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.activity_attention_celebrity_list_rv, "field 'recyclerView'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.activity_attention_celebrity_list_refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.activity_attention_celebrity_list_rl = Utils.findRequiredViewAsType(source, R.id.activity_attention_celebrity_list_rl, "field 'activity_attention_celebrity_list_rl'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AttentionListDataFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.refreshLayout = null;
    target.activity_attention_celebrity_list_rl = null;
  }
}
