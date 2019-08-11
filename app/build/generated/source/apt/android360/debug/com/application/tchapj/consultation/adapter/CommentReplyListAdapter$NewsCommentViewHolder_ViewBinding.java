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

public class CommentReplyListAdapter$NewsCommentViewHolder_ViewBinding implements Unbinder {
  private CommentReplyListAdapter.NewsCommentViewHolder target;

  @UiThread
  public CommentReplyListAdapter$NewsCommentViewHolder_ViewBinding(CommentReplyListAdapter.NewsCommentViewHolder target, View source) {
    this.target = target;

    target.findDetailCommentCiv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_civ, "field 'findDetailCommentCiv'", CircleImageView.class);
    target.findDetailCommentNameTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_name_tv, "field 'findDetailCommentNameTv'", TextView.class);
    target.findDetailCommentNameTagTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_name_tag_tv, "field 'findDetailCommentNameTagTv'", TextView.class);
    target.findDetailCommentLikeIv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_like_iv, "field 'findDetailCommentLikeIv'", ImageView.class);
    target.findDetailCommentLikesTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_likes_tv, "field 'findDetailCommentLikesTv'", TextView.class);
    target.findDetailCommentContentTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_content_tv, "field 'findDetailCommentContentTv'", TextView.class);
    target.findDetailCommentLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_ll, "field 'findDetailCommentLl'", LinearLayout.class);
    target.findDetailCommentTimetv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_time_tv, "field 'findDetailCommentTimetv'", TextView.class);
    target.findDetailCommentCommentCountTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_comment_count_tv, "field 'findDetailCommentCommentCountTv'", TextView.class);
    target.findDetailCommentView = Utils.findRequiredView(source, R.id.find_detail_comment_view, "field 'findDetailCommentView'");
    target.findDetailCommentLikeLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_like_ll, "field 'findDetailCommentLikeLl'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CommentReplyListAdapter.NewsCommentViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.findDetailCommentCiv = null;
    target.findDetailCommentNameTv = null;
    target.findDetailCommentNameTagTv = null;
    target.findDetailCommentLikeIv = null;
    target.findDetailCommentLikesTv = null;
    target.findDetailCommentContentTv = null;
    target.findDetailCommentLl = null;
    target.findDetailCommentTimetv = null;
    target.findDetailCommentCommentCountTv = null;
    target.findDetailCommentView = null;
    target.findDetailCommentLikeLl = null;
  }
}
