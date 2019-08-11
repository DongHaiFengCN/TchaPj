// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FindDetailAdapter$NewsContentViewHolder_ViewBinding implements Unbinder {
  private FindDetailAdapter.NewsContentViewHolder target;

  @UiThread
  public FindDetailAdapter$NewsContentViewHolder_ViewBinding(FindDetailAdapter.NewsContentViewHolder target, View source) {
    this.target = target;

    target.findDetailContentAvatarCiv = Utils.findRequiredViewAsType(source, R.id.find_detail_content_avatar_civ, "field 'findDetailContentAvatarCiv'", CircleImageView.class);
    target.findDetailContentNameTv = Utils.findRequiredViewAsType(source, R.id.find_detail_content_name_tv, "field 'findDetailContentNameTv'", TextView.class);
    target.findDetailContentContentstrTv = Utils.findRequiredViewAsType(source, R.id.find_detail_content_contentstr_tv, "field 'findDetailContentContentstrTv'", TextView.class);
    target.find_detail_content_rv = Utils.findRequiredViewAsType(source, R.id.find_detail_content_rv, "field 'find_detail_content_rv'", RecyclerView.class);
    target.findDetailContentTimeTv = Utils.findRequiredViewAsType(source, R.id.find_detail_content_time_tv, "field 'findDetailContentTimeTv'", TextView.class);
    target.findDetailContentAttentionTv = Utils.findRequiredViewAsType(source, R.id.find_detail_content_attention, "field 'findDetailContentAttentionTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindDetailAdapter.NewsContentViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.findDetailContentAvatarCiv = null;
    target.findDetailContentNameTv = null;
    target.findDetailContentContentstrTv = null;
    target.find_detail_content_rv = null;
    target.findDetailContentTimeTv = null;
    target.findDetailContentAttentionTv = null;
  }
}
