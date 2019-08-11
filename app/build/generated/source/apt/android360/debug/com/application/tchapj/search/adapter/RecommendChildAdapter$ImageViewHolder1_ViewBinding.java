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

public class RecommendChildAdapter$ImageViewHolder1_ViewBinding implements Unbinder {
  private RecommendChildAdapter.ImageViewHolder1 target;

  @UiThread
  public RecommendChildAdapter$ImageViewHolder1_ViewBinding(RecommendChildAdapter.ImageViewHolder1 target, View source) {
    this.target = target;

    target.tv_title1 = Utils.findRequiredViewAsType(source, R.id.tv_title1, "field 'tv_title1'", TextView.class);
    target.iv = Utils.findRequiredViewAsType(source, R.id.iv, "field 'iv'", ImageView.class);
    target.tv_time1 = Utils.findRequiredViewAsType(source, R.id.tv_time1, "field 'tv_time1'", TextView.class);
    target.iv_hot1 = Utils.findRequiredViewAsType(source, R.id.iv_hot1, "field 'iv_hot1'", ImageView.class);
    target.tv_name1 = Utils.findRequiredViewAsType(source, R.id.tv_name1, "field 'tv_name1'", TextView.class);
    target.ll_item1 = Utils.findRequiredViewAsType(source, R.id.ll_item1, "field 'll_item1'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecommendChildAdapter.ImageViewHolder1 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title1 = null;
    target.iv = null;
    target.tv_time1 = null;
    target.iv_hot1 = null;
    target.tv_name1 = null;
    target.ll_item1 = null;
  }
}
