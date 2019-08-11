// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.search.adapter;

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

public class RecommendChildAdapter$ImageViewHolder_ViewBinding implements Unbinder {
  private RecommendChildAdapter.ImageViewHolder target;

  @UiThread
  public RecommendChildAdapter$ImageViewHolder_ViewBinding(RecommendChildAdapter.ImageViewHolder target, View source) {
    this.target = target;

    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.iv01 = Utils.findRequiredViewAsType(source, R.id.iv01, "field 'iv01'", ImageView.class);
    target.iv02 = Utils.findRequiredViewAsType(source, R.id.iv02, "field 'iv02'", ImageView.class);
    target.iv03 = Utils.findRequiredViewAsType(source, R.id.iv03, "field 'iv03'", ImageView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.iv_hot = Utils.findRequiredViewAsType(source, R.id.iv_hot, "field 'iv_hot'", ImageView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.ll_item = Utils.findRequiredViewAsType(source, R.id.ll_item, "field 'll_item'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecommendChildAdapter.ImageViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.iv01 = null;
    target.iv02 = null;
    target.iv03 = null;
    target.tv_time = null;
    target.iv_hot = null;
    target.tv_name = null;
    target.ll_item = null;
  }
}
