// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FindDetailAdapter$NewsContentCommentViewHolder_ViewBinding implements Unbinder {
  private FindDetailAdapter.NewsContentCommentViewHolder target;

  @UiThread
  public FindDetailAdapter$NewsContentCommentViewHolder_ViewBinding(FindDetailAdapter.NewsContentCommentViewHolder target, View source) {
    this.target = target;

    target.findDetailContentCommentSizeTv = Utils.findRequiredViewAsType(source, R.id.find_detail_content_comment_size, "field 'findDetailContentCommentSizeTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindDetailAdapter.NewsContentCommentViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.findDetailContentCommentSizeTv = null;
  }
}
