// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.widiget;

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

public class HomeCircleView_ViewBinding implements Unbinder {
  private HomeCircleView target;

  @UiThread
  public HomeCircleView_ViewBinding(HomeCircleView target) {
    this(target, target);
  }

  @UiThread
  public HomeCircleView_ViewBinding(HomeCircleView target, View source) {
    this.target = target;

    target.tv_keyword = Utils.findRequiredViewAsType(source, R.id.tv_keyword, "field 'tv_keyword'", TextView.class);
    target.iv_head = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'iv_head'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.circle = Utils.findRequiredViewAsType(source, R.id.circle, "field 'circle'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeCircleView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_keyword = null;
    target.iv_head = null;
    target.name = null;
    target.circle = null;
  }
}
