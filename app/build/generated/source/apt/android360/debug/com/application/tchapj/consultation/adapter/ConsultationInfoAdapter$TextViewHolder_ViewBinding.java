// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConsultationInfoAdapter$TextViewHolder_ViewBinding implements Unbinder {
  private ConsultationInfoAdapter.TextViewHolder target;

  @UiThread
  public ConsultationInfoAdapter$TextViewHolder_ViewBinding(ConsultationInfoAdapter.TextViewHolder target, View source) {
    this.target = target;

    target.iv_image = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'iv_image'", ImageView.class);
    target.tv_content = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tv_content'", TextView.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.iv_hot = Utils.findRequiredViewAsType(source, R.id.iv_hot, "field 'iv_hot'", ImageView.class);
    target.rl_item = Utils.findRequiredViewAsType(source, R.id.rl_item, "field 'rl_item'", RelativeLayout.class);
    target.tv_delete = Utils.findRequiredViewAsType(source, R.id.tv_delete, "field 'tv_delete'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ConsultationInfoAdapter.TextViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_image = null;
    target.tv_content = null;
    target.tv_title = null;
    target.tv_time = null;
    target.iv_hot = null;
    target.rl_item = null;
    target.tv_delete = null;
  }
}
