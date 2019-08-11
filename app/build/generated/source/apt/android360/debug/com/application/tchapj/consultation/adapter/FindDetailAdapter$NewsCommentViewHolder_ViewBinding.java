// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FindDetailAdapter$NewsCommentViewHolder_ViewBinding implements Unbinder {
  private FindDetailAdapter.NewsCommentViewHolder target;

  @UiThread
  public FindDetailAdapter$NewsCommentViewHolder_ViewBinding(FindDetailAdapter.NewsCommentViewHolder target, View source) {
    this.target = target;

    target.findDetailCommentCiv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_civ, "field 'findDetailCommentCiv'", CircleImageView.class);
    target.findDetailCommentNameTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_name_tv, "field 'findDetailCommentNameTv'", TextView.class);
    target.findDetailCommentNameTagTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_name_tag_tv, "field 'findDetailCommentNameTagTv'", TextView.class);
    target.findDetailCommentLikeIv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_like_iv, "field 'findDetailCommentLikeIv'", ImageView.class);
    target.findDetailCommentLikesTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_likes_tv, "field 'findDetailCommentLikesTv'", TextView.class);
    target.findDetailCommentContentTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_content_tv, "field 'findDetailCommentContentTv'", TextView.class);
    target.findDetailCommentReplyLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_ll, "field 'findDetailCommentReplyLl'", LinearLayout.class);
    target.findDetailCommentLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_ll, "field 'findDetailCommentLl'", LinearLayout.class);
    target.findDetailCommentTimetv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_time_tv, "field 'findDetailCommentTimetv'", TextView.class);
    target.findDetailCommentCommentCountTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_comment_count_tv, "field 'findDetailCommentCommentCountTv'", TextView.class);
    target.findDetailCommentLikeLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_like_ll, "field 'findDetailCommentLikeLl'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindDetailAdapter.NewsCommentViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.findDetailCommentCiv = null;
    target.findDetailCommentNameTv = null;
    target.findDetailCommentNameTagTv = null;
    target.findDetailCommentLikeIv = null;
    target.findDetailCommentLikesTv = null;
    target.findDetailCommentContentTv = null;
    target.findDetailCommentReplyLl = null;
    target.findDetailCommentLl = null;
    target.findDetailCommentTimetv = null;
    target.findDetailCommentCommentCountTv = null;
    target.findDetailCommentLikeLl = null;
  }
}
