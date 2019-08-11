// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.search.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.CommentListView;
import com.application.tchapj.widiget.PraiseListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecommendChildAdapter$SudokuViewHolder_ViewBinding implements Unbinder {
  private RecommendChildAdapter.SudokuViewHolder target;

  @UiThread
  public RecommendChildAdapter$SudokuViewHolder_ViewBinding(RecommendChildAdapter.SudokuViewHolder target, View source) {
    this.target = target;

    target.rl_head = Utils.findRequiredViewAsType(source, R.id.rl_head, "field 'rl_head'", RelativeLayout.class);
    target.iv_head = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'iv_head'", ImageView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_explain = Utils.findRequiredViewAsType(source, R.id.tv_explain, "field 'tv_explain'", TextView.class);
    target.item_nice9_imagerl = Utils.findRequiredViewAsType(source, R.id.item_nice9_imagerl, "field 'item_nice9_imagerl'", RecyclerView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.contentTv = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'contentTv'", TextView.class);
    target.expandOrFoldTv = Utils.findRequiredViewAsType(source, R.id.tv_expand_or_fold, "field 'expandOrFoldTv'", TextView.class);
    target.ll_item = Utils.findRequiredViewAsType(source, R.id.ll_item, "field 'll_item'", LinearLayout.class);
    target.album_praise_layout = Utils.findRequiredViewAsType(source, R.id.album_praise_layout, "field 'album_praise_layout'", LinearLayout.class);
    target.album_praise = Utils.findRequiredViewAsType(source, R.id.album_praise, "field 'album_praise'", ImageView.class);
    target.album_praise_number = Utils.findRequiredViewAsType(source, R.id.album_praise_number, "field 'album_praise_number'", TextView.class);
    target.linearlayout_comment = Utils.findRequiredViewAsType(source, R.id.linearlayout_comment, "field 'linearlayout_comment'", LinearLayout.class);
    target.album_comment = Utils.findRequiredViewAsType(source, R.id.album_comment, "field 'album_comment'", ImageView.class);
    target.album_comment_number = Utils.findRequiredViewAsType(source, R.id.album_comment_number, "field 'album_comment_number'", TextView.class);
    target.linearlayoutAll = Utils.findRequiredViewAsType(source, R.id.linearlayoutAll, "field 'linearlayoutAll'", LinearLayout.class);
    target.album_praise_list_layout = Utils.findRequiredViewAsType(source, R.id.album_praise_list_layout, "field 'album_praise_list_layout'", LinearLayout.class);
    target.parise_icon = Utils.findRequiredViewAsType(source, R.id.parise_icon, "field 'parise_icon'", ImageView.class);
    target.praiseListView = Utils.findRequiredViewAsType(source, R.id.praiseListView, "field 'praiseListView'", PraiseListView.class);
    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.commentList = Utils.findRequiredViewAsType(source, R.id.commentList, "field 'commentList'", CommentListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecommendChildAdapter.SudokuViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rl_head = null;
    target.iv_head = null;
    target.tv_name = null;
    target.tv_explain = null;
    target.item_nice9_imagerl = null;
    target.tv_time = null;
    target.contentTv = null;
    target.expandOrFoldTv = null;
    target.ll_item = null;
    target.album_praise_layout = null;
    target.album_praise = null;
    target.album_praise_number = null;
    target.linearlayout_comment = null;
    target.album_comment = null;
    target.album_comment_number = null;
    target.linearlayoutAll = null;
    target.album_praise_list_layout = null;
    target.parise_icon = null;
    target.praiseListView = null;
    target.line = null;
    target.commentList = null;
  }
}
