// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.adpter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FansListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private FansListAdapter.ViewHolder target;

  @UiThread
  public FansListAdapter$ViewHolder_ViewBinding(FansListAdapter.ViewHolder target, View source) {
    this.target = target;

    target.headIv = Utils.findRequiredViewAsType(source, R.id.layout_item_fans_list_head_iv, "field 'headIv'", CircleImageView.class);
    target.nameTv = Utils.findRequiredViewAsType(source, R.id.layout_item_fans_list_head_nick_name_tv, "field 'nameTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FansListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.headIv = null;
    target.nameTv = null;
  }
}
