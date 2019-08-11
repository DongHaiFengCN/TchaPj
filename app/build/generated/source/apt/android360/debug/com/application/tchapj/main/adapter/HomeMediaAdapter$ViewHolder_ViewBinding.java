// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeMediaAdapter$ViewHolder_ViewBinding implements Unbinder {
  private HomeMediaAdapter.ViewHolder target;

  @UiThread
  public HomeMediaAdapter$ViewHolder_ViewBinding(HomeMediaAdapter.ViewHolder target, View source) {
    this.target = target;

    target.item = Utils.findRequiredViewAsType(source, R.id.item, "field 'item'", LinearLayout.class);
    target.iv_head = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'iv_head'", CircleImageView.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.title_1 = Utils.findRequiredViewAsType(source, R.id.title_1, "field 'title_1'", TextView.class);
    target.title_2 = Utils.findRequiredViewAsType(source, R.id.title_2, "field 'title_2'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeMediaAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.item = null;
    target.iv_head = null;
    target.tv_title = null;
    target.title_1 = null;
    target.title_2 = null;
  }
}
