// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.adapter;

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
import net.wujingchao.android.view.SimpleTagImageView;

public class HomeFollowAdapter$ViewHolder_ViewBinding implements Unbinder {
  private HomeFollowAdapter.ViewHolder target;

  @UiThread
  public HomeFollowAdapter$ViewHolder_ViewBinding(HomeFollowAdapter.ViewHolder target, View source) {
    this.target = target;

    target.follow_item = Utils.findRequiredViewAsType(source, R.id.follow_item, "field 'follow_item'", LinearLayout.class);
    target.follow_iv_head = Utils.findRequiredViewAsType(source, R.id.follow_iv_head, "field 'follow_iv_head'", SimpleTagImageView.class);
    target.follow_tv_title = Utils.findRequiredViewAsType(source, R.id.follow_tv_title, "field 'follow_tv_title'", TextView.class);
    target.follow_imageView = Utils.findRequiredViewAsType(source, R.id.follow_imageView, "field 'follow_imageView'", ImageView.class);
    target.follow_title_1 = Utils.findRequiredViewAsType(source, R.id.follow_title_1, "field 'follow_title_1'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFollowAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.follow_item = null;
    target.follow_iv_head = null;
    target.follow_tv_title = null;
    target.follow_imageView = null;
    target.follow_title_1 = null;
  }
}
