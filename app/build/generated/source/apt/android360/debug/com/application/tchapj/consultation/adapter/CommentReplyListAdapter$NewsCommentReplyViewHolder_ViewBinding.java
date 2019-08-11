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

public class CommentReplyListAdapter$NewsCommentReplyViewHolder_ViewBinding implements Unbinder {
  private CommentReplyListAdapter.NewsCommentReplyViewHolder target;

  @UiThread
  public CommentReplyListAdapter$NewsCommentReplyViewHolder_ViewBinding(CommentReplyListAdapter.NewsCommentReplyViewHolder target, View source) {
    this.target = target;

    target.replyLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_linear_layout, "field 'replyLl'", LinearLayout.class);
    target.replyCiv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_civ, "field 'replyCiv'", CircleImageView.class);
    target.replyNameTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_name_tv, "field 'replyNameTv'", TextView.class);
    target.replyContentTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_content_tv, "field 'replyContentTv'", TextView.class);
    target.replyAuthorTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_author_tag_tv, "field 'replyAuthorTv'", TextView.class);
    target.replyLikeLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_like_ll, "field 'replyLikeLl'", LinearLayout.class);
    target.replyLikeIv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_like_iv, "field 'replyLikeIv'", ImageView.class);
    target.replyLikeTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_reply_likes_tv, "field 'replyLikeTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CommentReplyListAdapter.NewsCommentReplyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.replyLl = null;
    target.replyCiv = null;
    target.replyNameTv = null;
    target.replyContentTv = null;
    target.replyAuthorTv = null;
    target.replyLikeLl = null;
    target.replyLikeIv = null;
    target.replyLikeTv = null;
  }
}
